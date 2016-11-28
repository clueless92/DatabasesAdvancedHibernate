package bg.tilchev.services;

import bg.tilchev.models.Author;

import java.util.Date;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */
public interface AuthorService {

    void persist(Author author);

    void delete(Author author);

    void delete(Long id);

    Author retrieve(Long id);

    List<Author> retrieveAll();

    long count();

    List<Author> findByBooksAfter(Date date);

    List<Author> findAllOrderedByBooks();
}
