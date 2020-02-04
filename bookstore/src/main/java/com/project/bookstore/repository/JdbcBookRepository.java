package com.project.bookstore.repository;

import com.project.bookstore.models.Book;
import com.project.bookstore.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookRepository implements BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SecurityService securityService;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOKS", Integer.class);
    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO BOOKS (name, description, source, cover, category, author, username) values(?,?,?,?,?,?,?)", 
        book.getName(), book.getDescription(), book.getSource(), book.getCover(), book.getCategory(), book.getAuthor(), book.getUsername());
    }

    @Override
    public int deleteById(Long id) {
        String sql;
        if(securityService.findLoggedInUsername()!=null){
            sql = "DELETE BOOKS WHERE id = ? AND (username = 'admin' OR username ='" + securityService.findLoggedInUsername() +"')";
        }
        else{
            sql = "DELETE BOOKS WHERE id = ? AND username = 'admin'";
        }
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findAll() {
        String sql;
        if(securityService.findLoggedInUsername()!=null){
            sql = "SELECT * FROM BOOKS WHERE username = 'admin' OR username ='" + securityService.findLoggedInUsername() +"'";
        }
        else{
            sql = "SELECT * FROM BOOKS WHERE username = 'admin'";
        }
        return jdbcTemplate.query(sql, 
        (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getString("description"), rs.getString("source"),
        rs.getString("cover"), rs.getString("category"), rs.getString("author"), rs.getString("username")));
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql;
        if(securityService.findLoggedInUsername()!=null){
            sql = "SELECT * FROM BOOKS WHERE id=? AND (username = 'admin' OR username ='" + securityService.findLoggedInUsername() +"')";
        }
        else{
            sql = "SELECT * FROM BOOKS WHERE id=? AND username = 'admin'";
        }
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (rs, rowNum) -> Optional
                        .of(new Book(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
                                rs.getString("source"), rs.getString("cover"), rs.getString("category"), rs.getString("author"), rs.getString("username"))));
    }

    @Override
    public List<Book> findByName(String name) {
        String sql;
        if(securityService.findLoggedInUsername()!=null){
            sql = "SELECT * FROM BOOKS WHERE lower(name) LIKE '%'||lower(?)||'%' AND (username = 'admin' OR username ='" + securityService.findLoggedInUsername() +"')";
        }
        else{
            sql = "SELECT * FROM BOOKS WHERE lower(name) LIKE '%'||lower(?)||'%' AND username = 'admin'";
        }
        return jdbcTemplate.query(sql, new Object[]{name},
        (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
                rs.getString("source"), rs.getString("cover"), rs.getString("category"), rs.getString("author"), rs.getString("username")));
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT name FROM BOOKS WHERE id = ?", new Object[]{id}, String.class);
    }

    @Override
    public List<Book> findByCategory(String category) {
        String sql;
        if(securityService.findLoggedInUsername()!=null){
            sql = "SELECT * FROM BOOKS WHERE lower(category) LIKE lower(?) AND (username = 'admin' OR username ='" + securityService.findLoggedInUsername() +"')";
        }
        else{
            sql = "SELECT * FROM BOOKS WHERE lower(category) LIKE lower(?) AND username = 'admin'";
        }
        return jdbcTemplate.query(sql, new Object[]{category},
        (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
                rs.getString("source"), rs.getString("cover"), rs.getString("category"), rs.getString("author"), rs.getString("username")));
    }

    @Override
    public List<Book> findByNameOrCategory(String query) {
        String sql;
        if(securityService.findLoggedInUsername()!=null){
            sql = "SELECT * FROM BOOKS WHERE lower(name) LIKE CONCAT('%',lower(?),'%') OR lower(category) LIKE lower(?) AND (username = 'admin' OR username ='" + securityService.findLoggedInUsername() +"')";
        }
        else{
            sql = "SELECT * FROM BOOKS WHERE lower(name) LIKE CONCAT('%',lower(?),'%') OR lower(category) LIKE lower(?) AND username = 'admin'";
        }
        return jdbcTemplate.query(sql, new Object[]{query, query},
        (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
                rs.getString("source"), rs.getString("cover"), rs.getString("category"), rs.getString("author"), rs.getString("username")));
    }

    @Override
    public List<String> getCategories() {
        String sql;
        if(securityService.findLoggedInUsername()!=null) {
            sql = "SELECT DISTINCT(category) FROM BOOKS WHERE username = 'admin' OR username = '" + securityService.findLoggedInUsername() + "'";
        }
        else {
            sql = "SELECT DISTINCT(category) FROM BOOKS WHERE username = 'admin'";
        }
        return jdbcTemplate.query(sql,
        (rs, rowNum) -> rs.getString("category"));
    }
}