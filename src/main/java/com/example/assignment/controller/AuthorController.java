package com.example.assignment.controller;

import com.example.assignment.constants.BaseResponse;
import com.example.assignment.persistence.dto.AuthorRequestDTO;
import com.example.assignment.persistence.entity.AuthorEntity;
import com.example.assignment.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/author/")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new BaseResponse<>(authorService.getAuthorById(id)), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return new ResponseEntity<>(new BaseResponse<>(authorService.createAuthor(authorRequestDTO)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        Long updatedAuthorId = authorService.updateAuthor(id, authorRequestDTO);
        return ResponseEntity.ok(new BaseResponse<>(updatedAuthorId));
    }

}
