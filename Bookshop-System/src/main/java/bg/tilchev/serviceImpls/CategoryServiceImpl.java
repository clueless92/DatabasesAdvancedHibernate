package bg.tilchev.serviceImpls;

import bg.tilchev.models.Category;
import bg.tilchev.repos.CategoryRepo;
import bg.tilchev.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */

@Service
@Primary
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void persist(Category category) {
        this.categoryRepo.saveAndFlush(category);
    }

    @Override
    public void delete(Category category) {
        this.categoryRepo.delete(category);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepo.delete(id);
    }

    @Override
    public Category retrieve(Long id) {
        return this.categoryRepo.findOne(id);
    }

    @Override
    public List<Category> retrieveAll() {
        return this.categoryRepo.findAll();
    }

    @Override
    public long count() {
        return this.categoryRepo.count();
    }
}
