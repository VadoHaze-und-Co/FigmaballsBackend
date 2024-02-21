package de.szut.lf8_project.controllers;

import de.szut.lf8_project.dtos.CreateTicketDTO;
import de.szut.lf8_project.dtos.GetTicketDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/tickets")
@RequestMapping
public class TicketController {

    public TicketController() {
    }

    @PostMapping("")
    public GetTicketDTO create(@RequestBody @Valid CreateTicketDTO dto) {
        return null;
    }

    @GetMapping("")
    public String c() {
        return "kdoifd";
    }
}