package com.figmaballs.figmaballs_backend.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAppendDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private String content;
}
