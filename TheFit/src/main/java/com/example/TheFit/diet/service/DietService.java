package com.example.TheFit.diet.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.diet.mapper.DietMapper;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DietService {

    private final DietRepository dietRepository;
    private final MemberRepository memberRepository;
    private final DietMapper dietMapper;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public DietService(DietRepository dietRepository, MemberRepository memberRepository, DietMapper dietMapper, AmazonS3Client amazonS3Client) {
        this.dietRepository = dietRepository;
        this.memberRepository = memberRepository;
        this.dietMapper = dietMapper;
        this.amazonS3Client = amazonS3Client;
    }

    public Diet create(DietReqDto dietReqDto)throws TheFitBizException {
        String fileName =dietReqDto.getImage().getOriginalFilename();
        String fileUrl = null;
        try {
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(dietReqDto.getImage().getContentType());
            metadata.setContentLength(dietReqDto.getImage().getSize());
            amazonS3Client.putObject(bucket,fileName,dietReqDto.getImage().getInputStream(),metadata);
            fileUrl = amazonS3Client.getUrl(bucket,fileName).toString();
        } catch (IOException e) {
            throw new TheFitBizException(ErrorCode.S3_SERVER_ERROR);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        Member member = memberRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER));
        Diet diet = dietMapper.toEntity(member,fileUrl,dietReqDto);
        return dietRepository.save(diet);
    }

    public DietResDto findById(Long id)throws TheFitBizException {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_DIET));
        Member member = memberRepository.findById(diet.getMember().getId()).orElseThrow();
        return dietMapper.toDto(member, diet);
    }

    public Diet update(Long id, DietReqDto dietReqDto)throws TheFitBizException {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_DIET));
        diet.update(dietReqDto);
        return diet;
    }

    public void delete(Long id) {
        dietRepository.deleteById(id);
    }

    public List<DietResDto> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = memberRepository.findByEmail(authentication.getName()).orElseThrow(
                ()-> new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER)
        ).getId();
        List<Diet> diets = dietRepository.findByMemberId(memberId);
        List<DietResDto> dietResDtos = new ArrayList<>();
        for(Diet diet :diets){
            dietResDtos.add(dietMapper.toDto(diet.getMember(), diet));
        }
        return dietResDtos;
    }
}