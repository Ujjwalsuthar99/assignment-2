package com.example.assignment;

import com.example.assignment.constants.CustomException;
import com.example.assignment.persistence.dto.AuthorRequestDTO;
import com.example.assignment.persistence.entity.AuthorEntity;
import com.example.assignment.persistence.repository.AuthorRepository;
import com.example.assignment.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    void createAuthor_Successful() {

        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("Ujjwal Suthar", "ujjwalsuthar99@gmail.com", "9649916989");
        when(authorRepository.findByEmail(authorRequestDTO.getEmail())).thenReturn(Optional.empty());

        AuthorEntity savedAuthor = new AuthorEntity(
                authorRequestDTO.getAuthorName(),
                authorRequestDTO.getEmail(),
                authorRequestDTO.getMobile(),
                false
        );
        savedAuthor.setId(1L);
        when(authorRepository.save(any(AuthorEntity.class))).thenReturn(savedAuthor);

        Long authorId = authorService.createAuthor(authorRequestDTO);

        assertNotNull(authorId);
        assertEquals(1L, authorId);
        verify(authorRepository).findByEmail(authorRequestDTO.getEmail());
        verify(authorRepository).save(any(AuthorEntity.class));
    }

    @Test
    void createAuthor_ThrowsCustomException_WhenAuthorExists() {

        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("Ujjwal Suthar", "ujjwalsuthar99@gmail.com", "9649916989");
        AuthorEntity existingAuthor = new AuthorEntity("Ujjwal Suthar", "ujjwalsuthar99@gmail.com", "9649916989", false);
        when(authorRepository.findByEmail(authorRequestDTO.getEmail())).thenReturn(Optional.of(existingAuthor));

        CustomException exception = assertThrows(
                CustomException.class,
                () -> authorService.createAuthor(authorRequestDTO)
        );

        assertEquals("author already exists: John Doe", exception.getMessage());
        verify(authorRepository).findByEmail(authorRequestDTO.getEmail());
        verify(authorRepository, never()).save(any(AuthorEntity.class));
    }
}