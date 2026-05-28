package com.example.helpdesk.controller;

import com.example.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("tickets",
                ticketRepository.findAllByOrderByIdAsc());
        return "tickets";
    }

    @GetMapping("/tickets/customer")
    public String ticketsByCustomer(@RequestParam(defaultValue = "Иван") String name, Model model) {
        model.addAttribute("tickets",
                ticketRepository.findByCustomerNameContainingIgnoreCaseOrderByIdAsc(name));
        model.addAttribute("customerName", name);
        return "tickets";
    }
}
