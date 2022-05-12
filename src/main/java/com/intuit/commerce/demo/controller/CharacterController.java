package com.intuit.commerce.demo.controller;

import com.intuit.commerce.demo.model.dto.CharacterItemDto;
import com.intuit.commerce.demo.model.dto.CharacterViewDto;
import com.intuit.commerce.demo.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/characters")
@RestController
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<CharacterItemDto> getAll() {
        return characterService.getAll();
    }

    @GetMapping("/{characterId}")
    public CharacterViewDto getOne(@PathVariable Long characterId) {
        return characterService.getOne(characterId);
    }
}
