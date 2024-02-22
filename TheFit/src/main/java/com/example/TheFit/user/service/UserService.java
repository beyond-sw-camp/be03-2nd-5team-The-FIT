package com.example.TheFit.user.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.user.dto.UserIdPassword;
import com.example.TheFit.user.dto.UserLoginRequestDto;
import com.example.TheFit.user.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserIdPassword login(UserLoginRequestDto userLoginRequestDto) {
        UserIdPassword userIdPassword = userRepository.findByEmail(userLoginRequestDto.getEmail()).orElseThrow(()->new TheFitBizException(ErrorCode.INCORRECT_ID));
        if(userIdPassword.delYn.equals("Y")){
            throw new TheFitBizException(ErrorCode.LEAVE_MEMBER);
        }
        if(!userIdPassword.getPassword().equals(userLoginRequestDto.getPassword())){
            throw new TheFitBizException(ErrorCode.INCORRECT_PASSWORD);
        }
        return userIdPassword;
    }
}
