package com.bookstore.jpa.service;

import com.bookstore.jpa.domain.Book;
import com.bookstore.jpa.domain.Review;
import com.bookstore.jpa.dto.BookRecordDTO;
import com.bookstore.jpa.repository.AuthorRepository;
import com.bookstore.jpa.repository.BookRepository;
import com.bookstore.jpa.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor

public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book saveBook(BookRecordDTO bookRecordDto) {
        Book book = new Book();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        Review reviewModel = new Review();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }


}
