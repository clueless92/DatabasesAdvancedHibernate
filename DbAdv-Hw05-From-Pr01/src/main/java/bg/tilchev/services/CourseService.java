package bg.tilchev.services;

import bg.tilchev.models.Course;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */


public interface CourseService {

    void persist(Course course);

    long count();

    List<Course> getAllCoursesOrderedByDates();

    List<Course> getAllCoursesWithMoreThan5Res();

    List<Course> getAllCoursesActiveOnGivenDate();
}
