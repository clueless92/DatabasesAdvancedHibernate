package bg.tilchev.repos;

import bg.tilchev.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

//    @Query("SELECT s FROM Student AS s")
//    List<Student> getAllStudents();
}
