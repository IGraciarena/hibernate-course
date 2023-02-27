package igraciarena.hibernatecourse.repository;

import igraciarena.hibernatecourse.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}