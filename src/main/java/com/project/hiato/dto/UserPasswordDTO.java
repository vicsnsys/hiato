package com.project.hiato.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordDTO {
    private Long id;
    private String previousPassword;
    private String newPassword;
}
