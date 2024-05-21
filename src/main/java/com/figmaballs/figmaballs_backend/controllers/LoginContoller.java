package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.config.PassEncryptionConfig;
import com.figmaballs.figmaballs_backend.dtos.AccountDTO;
import com.figmaballs.figmaballs_backend.dtos.LoginDTO;
import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetUserDTO;
import com.figmaballs.figmaballs_backend.entities.LoginEntity;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.mappers.LoginMapper;
import com.figmaballs.figmaballs_backend.mappers.TicketMapper;
import com.figmaballs.figmaballs_backend.services.LoginService;
import com.figmaballs.figmaballs_backend.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginContoller {

    private final LoginService service;
    private final LoginMapper mapper;
    private final PassEncryptionConfig encryptionConfig = new PassEncryptionConfig();

    public LoginContoller(LoginService service, LoginMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user logs in",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found")})
    @PostMapping("")
    public ResponseEntity<AccountDTO> login(@RequestBody @Valid LoginDTO dto) {
        var entity = this.service.readAll().stream().filter(e -> e.user.getUserName().equals(dto.getUsername())).findAny().orElse(null);
        if (entity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (encryptionConfig.verifyUserPassword(dto.getPassword(),entity.getPassword(), entity.getSaltValue())) {
            return new ResponseEntity<>(this.mapper.loginEntityToAccountDTO(entity), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Set new password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized")})
    @PostMapping("/set/password")
    public ResponseEntity<AccountDTO> setNewPassword(@RequestBody @Valid LoginDTO dto) {
        var entity = this.service.readAll().stream().filter(e -> e.user.getUserName().equals(dto.getUsername())).findAny().orElse(null);
        if (entity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        entity.setSecuredPassword(true);
        String saltValue = encryptionConfig.getSaltvalue(30);
        entity.setSaltValue(saltValue);
        entity.setPassword(encryptionConfig.generateSecurePassword(dto.getPassword(), saltValue));
        entity = this.service.update(entity);
        return new ResponseEntity<>(this.mapper.loginEntityToAccountDTO(entity), HttpStatus.OK);
    }

}
