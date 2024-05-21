package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketDTO;
import com.figmaballs.figmaballs_backend.services.TicketService;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketDTO;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.mappers.TicketMapper;
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
public class TicketController {

    private final TicketService service;
    private final TicketMapper mapper;

    public TicketController(TicketService service, TicketMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "creates a new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping("")
    public ResponseEntity<GetTicketDTO> create(@RequestBody @Valid CreateTicketDTO dto) {
        TicketEntity ticketEntity = this.mapper.ticketCreateDtoToEntity(dto);
        ticketEntity = this.service.create(ticketEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(ticketEntity), HttpStatus.CREATED);
    }

    @Operation(summary = "update an ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated ticket",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetTicketDTO> update(
            @Parameter(description = "ticket id", required = true) @PathVariable long id,
            @RequestBody @Valid CreateTicketDTO createTicketDTO) {
        TicketEntity updateEntity = mapper.ticketCreateDtoToEntity(createTicketDTO);
        updateEntity.setId(id);
        updateEntity = this.service.update(updateEntity);
        return new ResponseEntity<>(this.mapper.entityToGetDto(updateEntity), HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of tickets",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<List<GetTicketDTO>> getAll() {
        List<TicketEntity> l = this.service.readAll();
        return new ResponseEntity<>(l
                .stream()
                .map(this.mapper::entityToGetDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "delivers a ticket by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ticket by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<GetTicketDTO> getById(
            @Parameter(description = "ticket id", required = true) @PathVariable long id) {
        return new ResponseEntity<>(this.mapper.entityToGetDto(this.service.readById(id)), HttpStatus.OK);
    }

    @Operation(summary = "deletes a ticket by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete ticket by ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTicketDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "ticket id", required = true) @PathVariable long id) {
        try {
            this.service.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
