package bg.tilchev.services;

import bg.tilchev.models.Book;

import java.util.Date;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */
public interface BookService {

    void persist(Book book);

    void delete(Book book);

    void delete(Long id);

    Book retrieve(Long id);

    List<Book> retrieveAll();

    long count();

    List<Book> booksAfterDate(Date date);

    List<Book> boosByAuthor(String firstName, String lastName);
}
