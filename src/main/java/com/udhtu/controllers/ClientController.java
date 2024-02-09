package com.udhtu.controllers;

import com.udhtu.model.dto.ClientDto;
import com.udhtu.model.entity.ClientEntity;
import com.udhtu.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController
        implements ICrudController<ClientDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх клієнтів");
        List<ClientEntity> entityList = clientRepository.findAll();
        List<ClientDto> dtoList = new ArrayList<>(entityList.size());
        for (ClientEntity entity : entityList)
            dtoList.add(buildDto(entity));
        return dtoList;
    }

    @Override
    public ClientDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання клієнта за ідентифікатором {}", id);
        ClientDto dto = null;
        Optional<ClientEntity> preloadOptional = clientRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення клієнта за ідентифікатором {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto saveOrUpdate(ClientDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування клієнта {} {} {}", dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber());
        ClientEntity preload;
        if (dto.getId() == null) {
            preload = new ClientEntity();
        } else {
            Optional<ClientEntity> preloadOptional = clientRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(ClientEntity::new);
        }
        preload.setFirstName(dto.getFirstName());
        preload.setLastName(dto.getLastName());
        preload.setPhoneNumber(dto.getPhoneNumber());
        preload = clientRepository.save(preload);
        dto.setId(preload.getId());
        return dto;
    }

    public ClientDto buildDto(ClientEntity entity) {
        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
