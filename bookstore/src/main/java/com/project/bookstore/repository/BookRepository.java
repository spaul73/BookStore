package com.project.bookstore.repository;

import com.project.bookstore.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    int count();
    int save(Book book);
    int deleteById(Long id);
    List<Book> findAll();
    List<Book> findByName(String name);
    Optional<Book> findById(Long id);
    String getNameById(Long id);
    List<Book> findByCategory(String category);
    List<Book> findByNameOrCategory(String query);
    List<String> getCategories();
}