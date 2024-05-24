package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.ErrorDTO;
import com.figmaballs.figmaballs_backend.dtos.create.CreateUserDTO;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.mappers.UserMapper;
import com.figmaballs.figmaballs_backend.services.CategoryService;
import com.figmaballs.figmaballs_backend.services.UserGroupService;
import com.figmaballs.figmaballs_backend.services.UserService;
import com.figmaballs.figmaballs_backend.dtos.get.GetUserDTO;
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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final CategoryService categoryService;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Operation(summary = "creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "no user group exists")})
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateUserDTO dto) {
        var categoryEntities = this.categoryService.readAll();
        if (categoryEntities.stream().findAny().isEmpty()) {
            return new ResponseEntity<>(new ErrorDTO("ERROR " + HttpStatus.UNPROCESSABLE_ENTITY.value(),"there is no user groups that exist, please create it first"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        UserEntity userEntity = this.mapper.userCreateDtoToEntity(dto);
        userEntity = this.service.create(userEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(userEntity), HttpStatus.CREATED);
    }

    @Operation(summary = "update an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetUserDTO> update(
            @Parameter(description = "user id", required = true) @PathVariable long id,
            @RequestBody @Valid CreateUserDTO createUserDTO) {
        UserEntity updateEntity = mapper.userCreateDtoToEntity(createUserDTO);
        updateEntity.setId(id);
        updateEntity = this.service.update(updateEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(updateEntity), HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<List<GetUserDTO>> getAll() {
        return new ResponseEntity<>(this.service
                .readAll()
                .stream()
                .map(e -> this.mapper.entityToGetDto(e))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "delivers a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getById(
            @Parameter(description = "ticket id", required = true) @PathVariable long id) {
        return new ResponseEntity<>(this.mapper.entityToGetDto(this.service.readById(id)), HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of users as admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "users as Admins",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/admins")
    public ResponseEntity<List<GetUserDTO>> getAllAsAdmins() {
        return new ResponseEntity<>(this.service
                .readAllAsAdmins()
                .stream()
                .map(e -> this.mapper.entityToGetDto(e))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "deletes a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<GetUserDTO> deleteById(@PathVariable long id) {
        var entity = this.service.readById(id);
        this.service.delete(entity);
        GetUserDTO getProjectDTO = mapper.entityToGetDto(entity);
        return new ResponseEntity<>(getProjectDTO, HttpStatus.OK);
    }
}
