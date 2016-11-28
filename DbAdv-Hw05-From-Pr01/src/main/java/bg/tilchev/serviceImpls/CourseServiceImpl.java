package bg.tilchev.serviceImpls;

import bg.tilchev.models.Course;
import bg.tilchev.repos.CourseRepo;
import bg.tilchev.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepo;

    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public void persist(Course course) {
        this.courseRepo.saveAndFlush(course);
    }

    @Override
    public long count() {
        return this.courseRepo.count();
    }

    @Override
    public List<Course> getAllCoursesOrderedByDates() {
        return this.courseRepo.getAllCoursesOrderedByDates();
    }

    @Override
    public List<Course> getAllCoursesWithMoreThan5Res() {
        return this.courseRepo.getAllCoursesWithMoreThan5Res();
    }

    @Override
    public List<Course> getAllCoursesActiveOnGivenDate() {
        return this.courseRepo.getAllCoursesActiveOnGivenDate();
    }
}
