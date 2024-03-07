package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateUserGroupDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetUserDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetUserGroupDTO;
import com.figmaballs.figmaballs_backend.entities.UserGroupEntity;
import com.figmaballs.figmaballs_backend.mappers.UserGroupMapper;
import com.figmaballs.figmaballs_backend.services.UserGroupService;
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
@RequestMapping("v3/api/figmaballs/usersGroups")
public class UserGroupController {


    private final UserGroupService service;
    private final UserGroupMapper mapper;

    public UserGroupController(UserGroupService service, UserGroupMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "creates a new user group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created user group",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<GetUserGroupDTO> create(@RequestBody @Valid CreateUserGroupDTO dto) {
        UserGroupEntity entity = this.mapper.userGroupCreateDtoToEntity(dto);
        entity = this.service.create(entity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(entity), HttpStatus.CREATED);
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
    @PutMapping("/update/{id}")
    public ResponseEntity<GetUserGroupDTO> update(
            @Parameter(description = "user id", required = true) @PathVariable long id,
            @RequestBody @Valid CreateUserGroupDTO dto) {
        UserGroupEntity updateEntity = mapper.userGroupCreateDtoToEntity(dto);
        updateEntity.setId(id);
        updateEntity = this.service.update(updateEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(updateEntity), HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of user groups")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of user groups",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/getAll")
    public ResponseEntity<List<GetUserGroupDTO>> getAll() {
        return new ResponseEntity<>(this.service
                .readAll()
                .stream()
                .map(e -> this.mapper.entityToGetDto(e))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "delivers a user group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user group by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUserDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/get/{id}")
    public ResponseEntity<GetUserGroupDTO> getById(
            @Parameter(description = "user group id", required = true) @PathVariable long id) {
        return new ResponseEntity<>(this.mapper.entityToGetDto(this.service.readById(id)), HttpStatus.OK);
    }

    @Operation(summary = "deletes a user group by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<GetUserGroupDTO> deleteById(@PathVariable long id) {
        var entity = this.service.readById(id);
        this.service.delete(entity);
        GetUserGroupDTO getUserGroupDTO = mapper.entityToGetDto(entity);
        return new ResponseEntity<>(getUserGroupDTO, HttpStatus.OK);
    }
}
