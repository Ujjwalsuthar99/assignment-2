package com.example.assignment.service.impl;

import com.example.assignment.constants.CustomException;
import com.example.assignment.persistence.dto.BookRequestDTO;
import com.example.assignment.persistence.entity.AuthorEntity;
import com.example.assignment.persistence.entity.BookEntity;
import com.example.assignment.persistence.repository.AuthorRepository;
import com.example.assignment.persistence.repository.BookRepository;
import com.example.assignment.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookEntity getBookById(Long id) {
        Optional<BookEntity> existing = bookRepository.findById(id);
        if (existing.isPresent()) {
            return existing.get();
        } else {
            throw new CustomException("author not exist", 404, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Long createBook(BookRequestDTO bookRequestDTO) {
        log.info("createBook Begin");
        Optional<BookEntity> existing = bookRepository.findByTitleAndAuthorId(bookRequestDTO.getTitle(), bookRequestDTO.getAuthorId());
        existing.ifPresent(it-> {throw new CustomException("book already exists: " + it.getTitle());});

        AuthorEntity author = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new CustomException("Author not found with ID: " + bookRequestDTO.getAuthorId()));
        log.info("author Exist {}", author);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookRequestDTO.getTitle());
        bookEntity.setAuthor(author);
        bookEntity.setDeleted(false);

        bookRepository.save(bookEntity);
        log.info("createBook End");
        return bookEntity.getId();
    }

    @Override
    public void deleteBook(Long bookId) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookId);
        if (bookEntity.isPresent()) {
            bookRepository.deleteById(bookId);
        } else {
            throw new CustomException("Book not found with ID: " + bookId);
        }
    }

    @Override
    public Long updateBook(Long bookId, BookRequestDTO bookRequestDTO) {
        Optional<BookEntity> existingBook = bookRepository.findById(bookId);
        if (existingBook.isPresent()) {
            BookEntity bookEntity = existingBook.get();
            bookEntity.setTitle(bookRequestDTO.getTitle());

            AuthorEntity author = authorRepository.findById(bookRequestDTO.getAuthorId())
                    .orElseThrow(() -> new CustomException("Author not found with ID: " + bookRequestDTO.getAuthorId()));
            log.info("author Exist {}", author);

            bookEntity.setAuthor(author);
            bookRepository.save(bookEntity);
            return bookEntity.getId();
        } else {
            throw new CustomException("Book not found with ID: " + bookId);
        }
    }
}
