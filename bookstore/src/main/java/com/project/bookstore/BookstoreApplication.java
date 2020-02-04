package com.project.bookstore;

//import com.project.bookstore.models.Book;
import com.project.bookstore.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
//import java.util.Arrays;
//import java.util.List;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcBookRepository")
    private BookRepository bookRepository;
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Override
    public void run(String... args) {
        log.info("StartApplication...");
        runJDBC();
    }

    void runJDBC() {

        /*log.info("Creating tables for testing...");

        jdbcTemplate.execute("DROP TABLE IF EXISTS BOOKS");
        jdbcTemplate.execute("CREATE TABLE BOOKS(" +
                "id SERIAL, name VARCHAR(255), description VARCHAR(10000), source VARCHAR(255), cover VARCHAR(255), category VARCHAR(255), author VARCHAR(255), username VARCHAR(255))");

        List<Book> books = Arrays.asList(
				new Book("Pride and Prejudice", "Pride and Prejudice is an 1813 romantic novel of manners written by Jane Austen. The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book, who learns about the repercussions of hasty judgments and eventually comes to appreciate the difference between superficial goodness and actual goodness. A classic piece filled with comedy, its humour lies in its honest depiction of manners, education, marriage and money during the Regency era in Great Britain.",
				"https://www.gutenberg.org/files/1342/1342-h/1342-h.htm", "https://www.gutenberg.org/files/1342/1342-h/images/cover.jpg", "Drama", "Jane Austen", "admin"),
				new Book("A Christmas Carol", "A Christmas Carol. In Prose. Being a Ghost Story of Christmas, commonly known as A Christmas Carol, is a novella by Charles Dickens, first published in London by Chapman & Hall in 1843 and illustrated by John Leech. A Christmas Carol recounts the story of Ebenezer Scrooge, an elderly miser who is visited by the ghost of his former business partner Jacob Marley and the spirits of Christmas Past, Present and Yet to Come. After their visits, Scrooge is transformed into a kinder, gentler man.",
				"https://www.gutenberg.org/files/46/46-h/46-h.htm", "https://www.gutenberg.org/files/46/46-h/images/bookcover.jpg", "Mystery", "Charles Dickens", "admin"),
				new Book("Moby Dick", "Moby-Dick; or, The Whale is an 1851 novel by American writer Herman Melville. The book is sailor Ishmael's narrative of the obsessive quest of Ahab, captain of the whaling ship Pequod, for revenge on Moby Dick, the giant white sperm whale that on the ship's previous voyage bit off Ahab's leg at the knee.",
				"https://www.gutenberg.org/files/2701/2701-h/2701-h.htm", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Moby-Dick_FE_title_page.jpg/330px-Moby-Dick_FE_title_page.jpg", "Drama", "Herman Melville", "admin"),
				new Book("The Strange Case Of Dr. Jekyll And Mr. Hyde","Strange Case of Dr Jekyll and Mr Hyde is a gothic novella by Scottish author Robert Louis Stevenson, first published in 1886. The work is also known as The Strange Case of Jekyll Hyde, Dr Jekyll and Mr Hyde, or simply Jekyll & Hyde. It is about a London legal practitioner named Gabriel John Utterson who investigates strange occurrences between his old friend, Dr Henry Jekyll, and the evil Edward Hyde. The novella's impact is such that it has become a part of the language, with the phrase \"Jekyll and Hyde\" entering the vernacular to refer to people with an unpredictably dual nature: usually very good, but sometimes shockingly evil.",
				"https://www.gutenberg.org/files/43/43-h/43-h.htm","https://www.gutenberg.org/files/43/43-h/images/jekyllhydecover.jpg","Mystery","Robert Louis Stevenson", "admin")
                );

        log.info("[SAVE]");
        books.forEach(book -> {
            log.info("Saving...{}", book.getName());
            bookRepository.save(book);
        });*/

        // count
        log.info("[COUNT] Total books: {}", bookRepository.count());
	}
}
