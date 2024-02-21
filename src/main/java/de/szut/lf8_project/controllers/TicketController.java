package de.szut.lf8_project.controllers;

import de.szut.lf8_project.dtos.CreateTicketDTO;
import de.szut.lf8_project.dtos.GetTicketDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/tickets")
@RequestMapping("v3/api/figmaballs/tickets")
public class TicketController {

    public TicketController() {
    }

    @PostMapping("/create")
    public GetTicketDTO create(@RequestBody @Valid CreateTicketDTO dto) {
        return null;
    }

    @GetMapping("/get")
    public String c() {
        return "kdoifd";
    }
}