package com.example.assignment.service;

import com.example.assignment.persistence.dto.AuthorRequestDTO;
import com.example.assignment.persistence.entity.AuthorEntity;

public interface AuthorService {
    AuthorEntity getAuthorById(Long id);
    Long createAuthor(AuthorRequestDTO authorRequestDTO);
}
