package bg.tilchev.repos;

import bg.tilchev.models.Course;
import bg.tilchev.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course AS c" +
           " ORDER BY c.startDate ASC, c.endDate DESC")
    List<Course> getAllCoursesOrderedByDates();

    @Query("SELECT c FROM Course AS c" +
           " WHERE c.resources.size > 5" +
           " ORDER BY c.resources.size DESC, c.startDate DESC")
    List<Course> getAllCoursesWithMoreThan5Res();

    @Query("SELECT c FROM Course AS c" +
//            " WHERE c.startDate >= '2016-11-08 00:00:00.0'" +
//            "   AND c.endDate <= '2016-11-08 00:00:00.0'" +
            " ORDER BY c.students.size DESC")
    List<Course> getAllCoursesActiveOnGivenDate();
}
