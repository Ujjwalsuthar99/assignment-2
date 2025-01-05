package com.example.assignment.service;

import com.example.assignment.persistence.dto.BookRequestDTO;
import com.example.assignment.persistence.entity.BookEntity;


public interface BookService {
    BookEntity getBookById(Long id);
    Long createBook(BookRequestDTO bookRequestDTO);
    void deleteBook(Long bookId);
    Long updateBook(Long bookId, BookRequestDTO bookRequestDTO);
}
