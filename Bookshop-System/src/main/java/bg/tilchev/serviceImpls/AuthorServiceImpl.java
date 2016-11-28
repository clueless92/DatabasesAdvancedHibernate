package bg.tilchev.serviceImpls;

import bg.tilchev.models.Author;
import bg.tilchev.repos.AuthorRepo;
import bg.tilchev.services.AuthorService;
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
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public void persist(Author author) {
        this.authorRepo.saveAndFlush(author);
    }

    @Override
    public void delete(Author author) {
        this.authorRepo.delete(author);
    }

    @Override
    public void delete(Long id) {
        this.authorRepo.delete(id);
    }

    @Override
    public Author retrieve(Long id) {
        return this.authorRepo.findOne(id);
    }

    @Override
    public List<Author> retrieveAll() {
        return this.authorRepo.findAll();
    }

    @Override
    public long count() {
        return this.authorRepo.count();
    }

    @Override
    public List<Author> findByBooksAfter(Date date) {
        return this.authorRepo.findByBooksBefore(date);
    }

    @Override
    public List<Author> findAllOrderedByBooks() {
        return this.authorRepo.findAllOrderByBooksDesc();
    }
}
