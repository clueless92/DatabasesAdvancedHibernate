package bg.tilchev.services;

import bg.tilchev.models.Category;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */
public interface CategoryService {

    void persist(Category category);

    void delete(Category category);

    void delete(Long id);

    Category retrieve(Long id);

    List<Category> retrieveAll();

    long count();
}
