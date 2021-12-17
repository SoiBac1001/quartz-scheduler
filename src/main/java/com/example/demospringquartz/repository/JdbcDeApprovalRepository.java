package com.example.demospringquartz.repository;

import com.example.demospringquartz.entities.Book;

import java.util.List;
import java.util.Optional;

public interface JdbcDeApprovalRepository {
    int count();

    int save(Book book);

    int update(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    String getDeApprovalNameById(Long id);
}
