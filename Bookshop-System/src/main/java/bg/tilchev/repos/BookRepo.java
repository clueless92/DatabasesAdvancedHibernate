package bg.tilchev.repos;

import bg.tilchev.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByReleaseDateAfter(Date date);

    @Query("SELECT b FROM Book AS b" +
           "  JOIN b.author AS a" +
           " WHERE a.firstName = :first" +
           "   AND a.lastName = :last" +
           " ORDER BY b.releaseDate DESC, b.title ASC")
    List<Book> findBooksByAuthor(@Param("first") String firstName, @Param("last") String lastName);

}
