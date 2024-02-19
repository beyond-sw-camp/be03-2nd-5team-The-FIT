package com.example.TheFit.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GoogleUser {
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String picture;
}
