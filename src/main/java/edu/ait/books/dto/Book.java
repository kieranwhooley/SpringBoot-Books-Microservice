package edu.ait.books.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Books")
@ApiModel(description = "Information relating to books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The id is generated automatically")
    private Integer id;
    @NotBlank(message = "The title cannot be left blank")
    @ApiModelProperty(notes = "The title of the book")
    private String title;
    @NotBlank(message = "The author cannot be left blank")
    @ApiModelProperty(notes = "The author of the book")
    private String author;
    @Size(min = 4, max = 4, message = "The year must be 4 characters in length, for example 1898, 1957, 2003")
    @ApiModelProperty(notes = "The year the book was published")
    private String publishedYear;
    @NotBlank(message = "The publisher cannot be left blank")
    @ApiModelProperty(notes = "The name of the publisher")
    private String publisher;
    @NotBlank(message = "The genre cannot be left blank")
    @ApiModelProperty(notes = "The genre of the book")
    private String genre;
    @NotBlank(message = "The format cannot be left blank")
    @ApiModelProperty(notes = "The format of the book - Hardback, Paperback or eBook")
    private String format;

    public Book() {
    }

    public Book(Integer id, String title, String author, String publishedYear, String publisher, String genre, String format) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.publisher = publisher;
        this.genre = genre;
        this.format = format;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedYear='" + publishedYear + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
