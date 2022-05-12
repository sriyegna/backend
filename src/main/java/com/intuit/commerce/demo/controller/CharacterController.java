package com.intuit.commerce.demo.controller;

import com.intuit.commerce.demo.model.dto.CharacterItemDto;
import com.intuit.commerce.demo.model.dto.CharacterViewDto;
import com.intuit.commerce.demo.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/characters")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http:/0a/localhost:3000") //Running into CORS issue in browser without this
public class CharacterController {

    private final CharacterService characterService;

    /**
     * Endpoint to get a list of Characters on a specific page
     * @param page defaultValue = 0
     * @return List of characters in CharacterItemDto view
     */
    @GetMapping
    public List<CharacterItemDto> getAll(@RequestParam(defaultValue = "0") int page) {
        return characterService.getAll(page);
    }

    /**
     * Endpoint to get the number of pages of characters.
     * Calculation is population / PAGE_SIZE which is 18
     * @return number of pages of characters
     */
    @GetMapping("/pages")
    public int getPages() {
        return characterService.countPages();
    }

    /**
     * Endpoint to get a Character
     * @param characterId Id of character to find in the database
     * @return Character in CharacterViewDto view
     */
    @GetMapping("/{characterId}")
    public CharacterViewDto getOne(@PathVariable Long characterId) {
        return characterService.getOne(characterId);
    }
}
