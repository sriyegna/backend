package com.intuit.commerce.demo.service.impl;

import com.intuit.commerce.demo.model.dto.CharacterItemDto;
import com.intuit.commerce.demo.model.dto.CharacterViewDto;
import com.intuit.commerce.demo.mapper.CharacterMapper;
import com.intuit.commerce.demo.repository.CharacterRepository;
import com.intuit.commerce.demo.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private static final String CHARACTER_NOT_FOUND_FORMAT = "Character with id: %d not found";

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CharacterItemDto> getAll() {
        return characterRepository.findAll().stream()
                .map(characterMapper::entityToItemDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CharacterViewDto getOne(Long characterId) {
        return characterRepository.findById(characterId)
                .map(characterMapper::entityToViewDto)
                .orElseThrow(() -> new RuntimeException(String.format(CHARACTER_NOT_FOUND_FORMAT, characterId)));
    }
}
