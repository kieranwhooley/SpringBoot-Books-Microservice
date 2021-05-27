package edu.ait.books;

import edu.ait.books.dto.Book;
import edu.ait.books.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BooksIntegrationTests {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void contextLoads() {

    }

    //Test to confirm the toString method is working correctly
    @Test
    public void toStringMethodTest() {
        Book book = new Book(5, "Harry Potter", "J.K. Rowling", "1997", "Bloomsbury", "Fantasy", "eBook");
        String expected = "Book{id=5, title='Harry Potter', author='J.K. Rowling', publishedYear='1997', publisher='Bloomsbury', genre='Fantasy', format='eBook'}";
        assertEquals(expected, book.toString());
    }

    //Test to check save functionality is working
    @Test
    public void bookRepository_saveBooksTest()  {
        Book book1 = new Book(1, "Book 1", "Alan Smith", "1998", "Random House", "Fantasy", "eBook");
        Book book2 = new Book(2, "Book 2", "Mary Jones", "2005", "Penguin", "Fiction", "Hardback");
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> bookList = (List<Book>) bookRepository.findAll();

        assertEquals("Book 2", bookList.get(1).getTitle());
        assertEquals("Mary Jones", bookList.get(1).getAuthor());
        assertEquals("2005", bookList.get(1).getPublishedYear());
        assertEquals("Penguin", bookList.get(1).getPublisher());
        assertEquals("Fiction", bookList.get(1).getGenre());
        assertEquals("Hardback", bookList.get(1).getFormat());
    }

    //Test to check get functionality is working by checking the size of the database after inserting records
    @Test
    public void bookRepository_getBooksTest() {
        Book book1 = new Book(1, "Book 1", "Alan Smith", "1998", "Random House", "Fantasy", "eBook");
        Book book2 = new Book(2, "Book 2", "Mary Jones", "2005", "Penguin", "Fiction", "Hardback");
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> bookList = (List<Book>) bookRepository.findAll();

        assertThat(bookList).hasSize(2);
    }

    //Test to confirm update functionality works as expected
    @Test
    public void bookRepository_updateBooksTest() {
        Book book1 = new Book(1, "Book 1", "Alan Smith", "1998", "Random House", "Fantasy", "eBook");
        Book book2 = new Book(2, "Book 2", "Mary Jones", "2005", "Penguin", "Fiction", "Hardback");
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> bookList = (List<Book>) bookRepository.findAll();

        bookList.get(1).setTitle("Book 3");
        bookList.get(1).setAuthor("John Murphy");
        bookList.get(1).setPublishedYear("2010");
        bookList.get(1).setPublisher("Schuster & Schuster");
        bookList.get(1).setGenre("History");
        bookList.get(1).setFormat("Paperback");

        assertEquals("Book 3", bookList.get(1).getTitle());
        assertEquals("John Murphy", bookList.get(1).getAuthor());
        assertEquals("2010", bookList.get(1).getPublishedYear());
        assertEquals("Schuster & Schuster", bookList.get(1).getPublisher());
        assertEquals("History", bookList.get(1).getGenre());
        assertEquals("Paperback", bookList.get(1).getFormat());
    }

}
