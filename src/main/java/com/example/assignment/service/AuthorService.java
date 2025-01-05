package com.example.assignment.service;

import com.example.assignment.persistence.dto.AuthorRequestDTO;
import com.example.assignment.persistence.entity.AuthorEntity;

public interface AuthorService {
    AuthorEntity getAuthorById(Long id);
    Long createAuthor(AuthorRequestDTO authorRequestDTO);
    void deleteAuthor(Long authorId);
    Long updateAuthor(Long authorId, AuthorRequestDTO authorRequestDTO);

}
