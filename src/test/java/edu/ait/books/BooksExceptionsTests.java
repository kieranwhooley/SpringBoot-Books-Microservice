package edu.ait.books;

import edu.ait.books.exceptions.BookNotFoundException;
import edu.ait.books.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooksExceptionsTests {

    @Autowired
    BookRepository bookRepository;

    //Test to confirm the BookNotFoundException is thrown and caught correctly
    @Test
    void customBookExceptionTest() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> getNonExistentBookId(20));

        assertTrue(exception.getMessage().contains("not found"));
    }

    String getNonExistentBookId(int id) throws BookNotFoundException {
        throw new BookNotFoundException((id + " not found"));
    }
}
