package igraciarena.hibernatecourse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import igraciarena.hibernatecourse.domain.Book;
import igraciarena.hibernatecourse.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
// Esto hace que se levante el bean de la clase DataInitializer, y asi contar con registros mockeados
@ComponentScan("igraciarena.hibernatecourse.bootstrap")
class BookWithInitializerTest {

    @Autowired
    BookRepository bookRepository;

    // El rollback se usa para que no rollbackee spring cuando se corre este test
    //    @Rollback(value = false) para versiones de spring viejas
    @Commit
    // el order es para saber cual test va a ejecutarse primero
    @Order(1)
    @Test
    @DisplayName("Este test corre primero y hace que en el repositorio en tiempo de ejecucion ya haya 1 registro")
    void testJpaOk() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book of Stubs", "1234", "Self"));
        var countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    @DisplayName("Este test tendria que falla si no fuera porque usamos el rollback arriba y guardamos " +
            "en tiempo de ejecucion")
    void testJpaOkTwo() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }
}
