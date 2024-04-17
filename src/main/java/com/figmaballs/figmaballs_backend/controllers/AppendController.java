package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateAppendDTO;
import com.figmaballs.figmaballs_backend.dtos.create.CreateCategoryDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetAppendDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetCategoryDTO;
import com.figmaballs.figmaballs_backend.entities.AppendEntity;
import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import com.figmaballs.figmaballs_backend.mappers.AppendMapper;
import com.figmaballs.figmaballs_backend.mappers.CategoryMapper;
import com.figmaballs.figmaballs_backend.services.AppendService;
import com.figmaballs.figmaballs_backend.services.CategoryService;
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
import java.util.ArrayList;
import java.util.List;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/append")
public class AppendController {

    public AppendService service;
    public AppendMapper mapper;

    public AppendController(AppendService service, AppendMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "creates a new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping("")
    public ResponseEntity<GetAppendDTO> create(@RequestBody @Valid CreateAppendDTO dto) {
        AppendEntity entity = this.mapper.createDtoToEntity(dto);
        entity = this.service.create(entity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(entity), HttpStatus.CREATED);
    }

    @Operation(summary = "update an ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetAppendDTO> update(
            @Parameter(description = "ticket id", required = true) @PathVariable long id,
            @RequestBody @Valid CreateAppendDTO createTicketDTO) {
        AppendEntity updateEntity = mapper.createDtoToEntity(createTicketDTO);
        updateEntity.setId(id);
        updateEntity = this.service.update(updateEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(updateEntity), HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of tickets",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoryDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<List<AppendEntity>> getAll() {
        return new ResponseEntity<>(new ArrayList<>(this.service
                .readAll()), HttpStatus.OK);
    }

    @Operation(summary = "delivers a ticket by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ticket by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoryDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<AppendEntity> getById(
            @Parameter(description = "ticket id", required = true) @PathVariable long id) {
        return new ResponseEntity<>(this.service.readById(id), HttpStatus.OK);
    }
}
