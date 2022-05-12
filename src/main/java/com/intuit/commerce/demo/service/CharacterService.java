package com.intuit.commerce.demo.service;

import com.intuit.commerce.demo.model.dto.CharacterItemDto;
import com.intuit.commerce.demo.model.dto.CharacterViewDto;

import java.util.List;

public interface CharacterService {
    List<CharacterItemDto> getAll();
    CharacterViewDto getOne(Long characterId);
}
