package com.intuit.commerce.demo.service;

import com.intuit.commerce.demo.model.dto.CharacterItemDto;
import com.intuit.commerce.demo.model.dto.CharacterViewDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CharacterService {
    List<CharacterItemDto> getAll(int page);

    int countPages();

    CharacterViewDto getOne(Long characterId);
}
