package com.example.assignment.controller;


import com.example.assignment.constants.BaseResponse;
import com.example.assignment.persistence.dto.BookRequestDTO;
import com.example.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book/")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new BaseResponse<>(bookService.getBookById(id)), HttpStatus.OK);
    }

    @PostMapping(name = "create", consumes = "application/json")
    public ResponseEntity<Object> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntity<>(new BaseResponse<>(bookService.createBook(bookRequestDTO)), HttpStatus.OK);
    }
}
