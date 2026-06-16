package com.example.helpdesk.service;

import com.example.helpdesk.dto.TicketCreateDto;
import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.TicketStatus;
import com.example.helpdesk.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket testTicket;
    private TicketCreateDto testDto;

    @BeforeEach
    void setUp() {
        testTicket = new Ticket();
        testTicket.setId(1L);
        testTicket.setTitle("Тестовая заявка");
        testTicket.setDescription("Описание тестовой заявки");
        testTicket.setCustomerName("Тестовый пользователь");
        testTicket.setStatus(TicketStatus.NEW);

        testDto = new TicketCreateDto();
        testDto.setTitle("Тестовая заявка");
        testDto.setDescription("Описание тестовой заявки");
        testDto.setCustomerName("Тестовый пользователь");
    }

    @Test
    void createTicket_ShouldSaveAndReturnTicket() {
        when(ticketRepository.save(any(Ticket.class))).thenReturn(testTicket);

        Ticket result = ticketService.createTicket(testDto);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Тестовая заявка");
        assertThat(result.getStatus()).isEqualTo(TicketStatus.NEW);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void getAllTickets_ShouldReturnListOfTickets() {
        List<Ticket> tickets = List.of(testTicket);
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Тестовая заявка");
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void getTicketById_WhenExists_ShouldReturnTicket() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket));

        Ticket result = ticketService.getTicketById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getTicketById_WhenNotFound_ShouldThrowException() {
        when(ticketRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            ticketService.getTicketById(999L);
        });
    }
}