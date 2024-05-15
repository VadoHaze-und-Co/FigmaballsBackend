package com.figmaballs.figmaballs_backend.dtos.create;

import lombok.Data;

@Data
public class CreateLogDTO {

    private String user;
    private String object;
    private String action;
    private String message;
}
