package com.example.TheFit.user.service;

import com.example.TheFit.user.dto.UserIdPassword;
import com.example.TheFit.user.dto.UserLoginRequestDto;
import com.example.TheFit.user.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserIdPassword login(UserLoginRequestDto userLoginRequestDto) {
        UserIdPassword userIdPassword = userRepository.findByEmail(userLoginRequestDto.getEmail()).orElseThrow(()-> new EntityNotFoundException("해당 이메일이 없습니다"));
        if(!userIdPassword.getPassword().equals(userLoginRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다");
        }
        return userIdPassword;
    }
}
