package com.example.TheFit.user.dto;

import com.example.TheFit.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserIdPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Role role;

    public UserIdPassword(String email, String password, Role role) {
        this.email = email;
        this.password =password;
        this.role = role;
    }
}
