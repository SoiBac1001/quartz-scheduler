package com.example.demospringquartz.repository.impl;

import com.example.demospringquartz.entities.Book;
import com.example.demospringquartz.repository.JdbcDeApprovalRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcDeApprovalRepositoryImpl implements JdbcDeApprovalRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT (*) FROM Book", Integer.class);
    }

    @Override
    public int save(Book deApproval) {
        return 0;
    }

    @Override
    public int update(Book deApproval) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public String getDeApprovalNameById(Long id) {
        return null;
    }
}
