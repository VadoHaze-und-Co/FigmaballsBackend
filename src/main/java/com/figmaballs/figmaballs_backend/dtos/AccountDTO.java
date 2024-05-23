package com.figmaballs.figmaballs_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private Long id;
    private Long userId;
    //private Integer role;
    private Long lastLogin;
    private Boolean sP;
    private Boolean password;
}
