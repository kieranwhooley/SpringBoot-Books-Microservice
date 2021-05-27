package edu.ait.books.controllers;

import edu.ait.books.dto.Book;
import edu.ait.books.exceptions.BookNotFoundException;
import edu.ait.books.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    //Get all books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Get a specific book using the book id
    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        Optional<Book> foundBook = bookRepository.findById(bookId);
        if (foundBook.isPresent()) {
            return foundBook.get();
        }
        else
            throw new BookNotFoundException("There is no book with id " + bookId + " in the database");
    }

    //Create a new book
    @PostMapping("/books")
    public ResponseEntity createBook(@Valid @RequestBody Book newBook) {
        Book savedBook = bookRepository.save(newBook);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(newBook.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Update and existing book or create a new one if the id of the one being updated does not already exist
    @PutMapping("/books")
    public ResponseEntity updateBook(@Valid @RequestBody Book newBook) {
        if (newBook.getId() != null) {
            bookRepository.save(newBook);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            Book savedBook = bookRepository.save(newBook);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(newBook.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    //Delete a specific book using the book id
    @DeleteMapping("/books/{bookId}")
    public void deleteBookById(@PathVariable int bookId) {
        try {
            bookRepository.deleteById(bookId);
        }
        catch(EmptyResultDataAccessException e) {
            throw new BookNotFoundException("Unable to delete as there is no book in the database with id " + bookId);
        }
    }
}
