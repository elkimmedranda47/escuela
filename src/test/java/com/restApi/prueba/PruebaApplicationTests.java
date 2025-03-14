package com.restApi.prueba;

/*import com.restApi.prueba.models.Author;
import com.restApi.prueba.models.Book;
import com.restApi.prueba.models.Contact;
import com.restApi.prueba.models.Style;
import com.restApi.prueba.models.Theme;
import com.restApi.prueba.repositorys.AuthorRepository;
import com.restApi.prueba.repositorys.BookRepository;
import com.restApi.prueba.repositorys.ContactRepository;
import com.restApi.prueba.repositorys.StyleRepository;
import com.restApi.prueba.repositorys.ThemeRepository;*/

//import org.junit.jupiter.api.Test;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;*/

 //@SpringBootTest
class PruebaApplicationTests {
     // private static final Logger logger = LoggerFactory.getLogger(PruebaApplicationTests.class);

 /*
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StyleRepository styleRepository;*/

  //  @Autowired
   // private JdbcTemplate jdbcTemplate;

  /* @Test
    void contextLoads() {
       
       //  logger.info("Starting contextLoads test...******************************88888999999***************************");
 
        
        // Insertar datos en las tablas
        jdbcTemplate.update("INSERT INTO Style ( name, description) VALUES ( 'Epic', 'Large-scale and grand narratives')");
        jdbcTemplate.update("INSERT INTO Style ( name, description) VALUES ( 'Satire', 'Use of humor, irony, exaggeration, or ridicule')");
        jdbcTemplate.update("INSERT INTO Style ( name, description) VALUES ( 'Adventure', 'Exciting and unusual experiences')");

        jdbcTemplate.update("INSERT INTO Contact ( email, phone) VALUES ( 'contact1@example.com', 123456789)");
        jdbcTemplate.update("INSERT INTO Contact ( email, phone) VALUES ('contact2@example.com', 987654321)");
        jdbcTemplate.update("INSERT INTO Contact ( email, phone) VALUES ('contact3@example.com', 555555555)");

        jdbcTemplate.update("INSERT INTO Author ( name, surname, contact_id, style_id) VALUES ( 'J.K.', 'Rowling', '1','1')");
        jdbcTemplate.update("INSERT INTO Author ( name, surname, contact_id, style_id) VALUES ( 'J.R.R.', 'Tolkien', '2','2')");
        jdbcTemplate.update("INSERT INTO Author ( name, surname, contact_id, style_id) VALUES ( 'George', 'Orwell', '3','3')");

        jdbcTemplate.update("INSERT INTO Theme (id, name) VALUES ('1', 'Fantasy')");
        jdbcTemplate.update("INSERT INTO Theme (id, name) VALUES ('2', 'Science Fiction')");
        jdbcTemplate.update("INSERT INTO Theme (id, name) VALUES ('3', 'Dystopian')");

        jdbcTemplate.update("INSERT INTO Book ( name, isbn, author_id, theme_id) VALUES ( 'Harry Potter and the Philosopher''s Stone', '9780747532743', '1', '1')");
        jdbcTemplate.update("INSERT INTO Book ( name, isbn, author_id, theme_id) VALUES ( 'The Lord of the Rings', '9780618640157', '2', '2')");
        jdbcTemplate.update("INSERT INTO Book ( name, isbn, author_id, theme_id) VALUES ( '1984', '9780451524935', '3', '3')");

        // Verificar que la base de datos ha sido poblada correctamente
        Integer bookCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Book", Integer.class);
        assertThat(bookCount).isGreaterThan(0);

        Integer authorCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Author", Integer.class);
        assertThat(authorCount).isGreaterThan(0);

        Integer themeCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Theme", Integer.class);
        assertThat(themeCount).isGreaterThan(0);

        Integer styleCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Style", Integer.class);
        assertThat(styleCount).isGreaterThan(0);

        Integer contactCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Contact", Integer.class);
        assertThat(contactCount).isGreaterThan(0);
    }
    */

 
}
