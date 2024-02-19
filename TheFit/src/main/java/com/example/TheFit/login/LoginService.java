package com.example.TheFit.login;

import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LoginService {
    @Autowired
    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member login(LoginRequestDto loginRequestDto) throws EntityNotFoundException,IllegalArgumentException{
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new EntityNotFoundException("해당 이메일이 없습니다"));
        if(!member.getPassword().equals(loginRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다");
        }
        return member;
    }
}
