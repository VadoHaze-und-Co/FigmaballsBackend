package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketDTO;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.mappers.TicketMapper;
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
@RequestMapping("/tickets")
public class LoginContoller {


    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized")})
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Parameter(description = "username", required = true) @PathVariable String username,
            @Parameter(description = "password", required = true) @PathVariable Long password) {
        return new ResponseEntity<>("Login richtig", HttpStatus.CREATED);

    }

}
