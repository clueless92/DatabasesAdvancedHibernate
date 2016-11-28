package bg.tilchev.repos;

import bg.tilchev.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
