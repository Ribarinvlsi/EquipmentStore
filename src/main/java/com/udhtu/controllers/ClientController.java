package com.udhtu.controllers;

import com.udhtu.model.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("clients")
public class ClientController
        implements ICrudController<ClientDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    private final HashMap<Long, ClientDto> mockDb = new HashMap();

    @Override
    public List<ClientDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх клієнтів");
        return new ArrayList(this.mockDb.values());
    }

    @Override
    public ClientDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання клієнта за ідентифікатором {}", id);
        return this.mockDb.get(id);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення клієнта за ідентифікатором {}", id);
        this.mockDb.remove(id);
    }

    @Override
    public ClientDto saveOrUpdate(ClientDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування клієнта {} {} {}", dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber());
        this.mockDb.put(dto.getId(), dto);
        return dto;
    }
}
