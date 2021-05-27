package edu.ait.books;

import edu.ait.books.controllers.BookController;
import edu.ait.books.dto.Book;
import edu.ait.books.repositories.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BooksUnitTests {

    @InjectMocks
    BookController bookController;

    @Mock
    BookRepository bookRepository;

    //Test to check the save method is working as expected
    @Test
    public void saveNewBook_UnitTest() {
        Book book = new Book(5, "Test", "A.N. Other", "2005", "Penguin", "Fiction", "Hardback");
        bookRepository.save(book);
        assertThat(book).hasFieldOrPropertyWithValue("id", 5);
        assertThat(book).hasFieldOrPropertyWithValue("title", "Test");
        assertThat(book).hasFieldOrPropertyWithValue("author", "A.N. Other");
        assertThat(book).hasFieldOrPropertyWithValue("publishedYear", "2005");
        assertThat(book).hasFieldOrPropertyWithValue("publisher", "Penguin");
        assertThat(book).hasFieldOrPropertyWithValue("genre", "Fiction");
        assertThat(book).hasFieldOrPropertyWithValue("format", "Hardback");

    }

    //Test to delete a newly saved book
    @Test
    public void deleteBook_UnitTest() {
        Book book = new Book(5, "Test", "A.N. Other", "2005", "Penguin", "Fiction", "Hardback");
        bookRepository.save(book);
        bookRepository.deleteById(book.getId());

    }

}
