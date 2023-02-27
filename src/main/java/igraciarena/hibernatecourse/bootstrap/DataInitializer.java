package igraciarena.hibernatecourse.bootstrap;

import igraciarena.hibernatecourse.domain.Book;
import igraciarena.hibernatecourse.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        var book = new Book("Domain Drive Design", "123", "RandomHouse");
        bookRepository.save(book);
        var bookSia = new Book("Spring in Action", "234234", "O'relly");
        bookRepository.save(bookSia);
    }
}
