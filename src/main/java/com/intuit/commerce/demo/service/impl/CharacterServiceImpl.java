package com.intuit.commerce.demo.service.impl;

        import com.intuit.commerce.demo.model.dto.CharacterItemDto;
        import com.intuit.commerce.demo.model.dto.CharacterViewDto;
        import com.intuit.commerce.demo.mapper.CharacterMapper;
        import com.intuit.commerce.demo.model.entity.Character;
        import com.intuit.commerce.demo.repository.CharacterRepository;
        import com.intuit.commerce.demo.service.CharacterService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private static final String CHARACTER_NOT_FOUND_FORMAT = "Character with id: %d not found";
    private static final int PAGE_SIZE = 18;

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    /**
     * Get a list of Characters on a specific page
     * @param page defaultValue = 0
     * @return List of characters in CharacterItemDto view or empty list
     */
    @Override
    @Transactional(readOnly = true)
    public List<CharacterItemDto> getAll(int page) {
        Pageable paging = PageRequest.of(page, PAGE_SIZE, Sort.unsorted());
        Page<Character> pagedCharacters = characterRepository.findAll(paging);

        if (pagedCharacters.hasContent()) {
            return pagedCharacters.getContent().stream().map(characterMapper::entityToItemDto).collect(Collectors.toList());
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Get the number of pages of characters.
     * Calculation is population / PAGE_SIZE which is 18
     * @return number of pages of characters
     */
    @Override
    @Transactional(readOnly = true)
    public int countPages() {
        return (int) Math.ceil(characterRepository.count() / PAGE_SIZE);
    }

    /**
     * Get a Character
     * @param characterId Id of character to find in the database
     * @return Character in CharacterViewDto view
     */
    @Override
    @Transactional(readOnly = true)
    public CharacterViewDto getOne(Long characterId) {
        return characterRepository.findById(characterId)
                .map(characterMapper::entityToViewDto)
                .orElseThrow(() -> new RuntimeException(String.format(CHARACTER_NOT_FOUND_FORMAT, characterId)));
    }
}
