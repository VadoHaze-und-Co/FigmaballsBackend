package com.figmaballs.figmaballs_backend.dtos.get;

import lombok.Data;

@Data
public class GetLogDTO {

    private String timestamp;
    private String user;
    private String object;
    private String action;
    private String message;
}
