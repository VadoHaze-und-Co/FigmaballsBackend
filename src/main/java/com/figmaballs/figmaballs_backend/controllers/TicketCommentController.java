package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketCommentDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketCommentDTO;
import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.mappers.TicketCommentMapper;
import com.figmaballs.figmaballs_backend.services.TicketCommentService;
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
@RequestMapping("/comments")
public class TicketCommentController {

    private final TicketCommentService service;
    private final TicketCommentMapper mapper;

    public TicketCommentController(TicketCommentService service, TicketCommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "creates a new comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketCommentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping("")
    public ResponseEntity<GetTicketCommentDTO> create(@RequestBody @Valid CreateTicketCommentDTO dto) {
        TicketCommentEntity entity = this.mapper.createDtoToEntity(dto);
        entity = this.service.create(entity);
        return new ResponseEntity<>(this.mapper.entityToGetDTO(entity), HttpStatus.CREATED);
    }

    @Operation(summary = "update an comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketCommentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetTicketCommentDTO> update(
            @Parameter(description = "ticket id", required = true) @PathVariable long id,
            @RequestBody @Valid CreateTicketCommentDTO createTicketDTO) {
        TicketCommentEntity updateEntity = mapper.createDtoToEntity(createTicketDTO);
        updateEntity.setId(id);
        updateEntity = this.service.update(updateEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDTO(updateEntity), HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of comments",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketCommentDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<List<GetTicketCommentDTO>> getAll() {
        List<TicketCommentEntity> l = this.service.readAll();
        return new ResponseEntity<>(l
                .stream()
                .map(this.mapper::entityToGetDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /*@Operation(summary = "delivers a list of tickets by ticket ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of comments",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketCommentDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/ticket/{id}")
    public ResponseEntity<List<GetTicketCommentDTO>> getAllByTicketId(
            @Parameter(description = "ticket id", required = true) @PathVariable long id) {
        List<TicketCommentEntity> l = this.service.readAll();
        if (l.removeIf(entity -> entity.getTicket().getId() != id)) {
            return new ResponseEntity<>(l
                    .stream()
                    .map(this.mapper::entityToGetDTO)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Operation(summary = "delivers a comment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "comment by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketCommentDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<GetTicketCommentDTO> getById(
            @Parameter(description = "comment id", required = true) @PathVariable long id) {
        return new ResponseEntity<>(this.mapper.entityToGetDTO(this.service.readById(id)), HttpStatus.OK);
    }

    @Operation(summary = "deletes a comment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<GetTicketCommentDTO> deleteProjectById(@PathVariable long id) {
        var entity = this.service.readById(id);
        this.service.delete(entity);
        GetTicketCommentDTO getProjectDTO = this.mapper.entityToGetDTO(entity);
        return new ResponseEntity<>(getProjectDTO, HttpStatus.OK);
    }

}
