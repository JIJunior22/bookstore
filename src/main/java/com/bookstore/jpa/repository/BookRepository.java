package com.bookstore.jpa.repository;

import com.bookstore.jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Book findByTitle(String title);

    @Query(value = "select * from book where publisher_id= :id", nativeQuery = true)
    List<Book> findByPublisherId(@Param("id") UUID id);
}
