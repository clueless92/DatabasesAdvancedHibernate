package bg.tilchev.repos;

import bg.tilchev.models.Author;
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
public interface AuthorRepo extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author AS a" +
           "  JOIN a.books AS b" +
           " WHERE b.releaseDate < :date")
    List<Author> findByBooksBefore(@Param("date") Date date);


    @Query("SELECT DISTINCT a FROM Author AS a" +
           "  JOIN a.books AS b" +
           " ORDER BY b.size DESC")
    List<Author> findAllOrderByBooksDesc();
}
