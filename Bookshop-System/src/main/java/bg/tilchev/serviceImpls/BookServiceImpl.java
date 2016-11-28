package bg.tilchev.serviceImpls;

import bg.tilchev.models.Book;
import bg.tilchev.repos.BookRepo;
import bg.tilchev.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */

@Service
@Primary
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void persist(Book book) {
        this.bookRepo.saveAndFlush(book);
    }

    @Override
    public void delete(Book book) {
        this.bookRepo.delete(book);
    }

    @Override
    public void delete(Long id) {
        this.bookRepo.delete(id);
    }

    @Override
    public Book retrieve(Long id) {
        return this.bookRepo.findOne(id);
    }

    @Override
    public List<Book> retrieveAll() {
        return this.bookRepo.findAll();
    }

    @Override
    public long count() {
        return this.bookRepo.count();
    }

    @Override
    public List<Book> booksAfterDate(Date date) {
        return this.bookRepo.findByReleaseDateAfter(date);
    }

    @Override
    public List<Book> boosByAuthor(String firstName, String lastName) {
        return this.bookRepo.findBooksByAuthor(firstName, lastName);
    }
}
