package com.example.helpdesk.controller;

import com.example.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("tickets",
                ticketRepository.findAllByOrderByCreatedAtDesc());
        return "tickets";
    }
}
