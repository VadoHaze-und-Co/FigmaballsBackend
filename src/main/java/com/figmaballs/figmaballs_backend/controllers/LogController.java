package com.figmaballs.figmaballs_backend.controllers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateAppendDTO;
import com.figmaballs.figmaballs_backend.dtos.create.CreateLogDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetAppendDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetCategoryDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetLogDTO;
import com.figmaballs.figmaballs_backend.entities.AppendEntity;
import com.figmaballs.figmaballs_backend.entities.LogEntity;
import com.figmaballs.figmaballs_backend.mappers.AppendMapper;
import com.figmaballs.figmaballs_backend.services.AppendService;
import com.figmaballs.figmaballs_backend.services.LogService;
import io.swagger.models.auth.In;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/log")
public class LogController {

    public LogService service;

    public LogController(LogService service) {
        this.service = service;
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
    public ResponseEntity<GetAppendDTO> create(@RequestBody @Valid CreateLogDTO dto) {
        this.service.log(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "delivers a list of append")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of append",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoryDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{page}/{amount}")
    public ResponseEntity<List<GetLogDTO>> getAll(@PathVariable long page, @PathVariable long amount) {
        List<GetLogDTO> finalLogs = new ArrayList<>();
        List<LogEntity> logs = this.service.logs().stream().skip(page * amount).toList();
        for (int i = 0; i < Math.min(amount, logs.size()); i++) {
            GetLogDTO l = new GetLogDTO();
            LogEntity log = logs.get(i);
            l.setUser(log.getUsername());
            l.setAction(log.getAction());
            l.setMessage(log.getMessage());
            l.setObject(log.getObject());
            l.setTimestamp(log.getTime().format(DateTimeFormatter.ISO_DATE_TIME));
            finalLogs.add(l);
        }
        return new ResponseEntity<>(finalLogs, HttpStatus.OK);
    }

    @Operation(summary = "delivers a list of append")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of append",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoryDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/size")
    public ResponseEntity<Integer> getSize() {
        return new ResponseEntity<>(this.service.logs().size(), HttpStatus.OK);
    }
}
