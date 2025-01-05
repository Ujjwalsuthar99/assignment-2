package com.example.assignment.service.impl;

import com.example.assignment.constants.CustomException;
import com.example.assignment.persistence.dto.AuthorRequestDTO;
import com.example.assignment.persistence.entity.AuthorEntity;
import com.example.assignment.persistence.repository.AuthorRepository;
import com.example.assignment.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity getAuthorById(Long id) {
        log.info("getAuthorById Begin");
        Optional<AuthorEntity> existing = authorRepository.findById(id);
        if (existing.isPresent()) {
            return existing.get();
        } else {
            throw new CustomException("author not exist", 404, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Long createAuthor(AuthorRequestDTO authorRequestDTO) {
        log.info("createAuthor Begin");
        Optional<AuthorEntity> existing = authorRepository.findByEmail(authorRequestDTO.getEmail());
        existing.ifPresent(it-> {throw new CustomException("author already exists: " + it.getAuthorName());});

        AuthorEntity authorEntity = new AuthorEntity(authorRequestDTO.getAuthorName(), authorRequestDTO.getEmail(), authorRequestDTO.getMobile(), false);
        authorRepository.save(authorEntity);
        log.info("createAuthor End");
        return authorEntity.getId();
    }
}
