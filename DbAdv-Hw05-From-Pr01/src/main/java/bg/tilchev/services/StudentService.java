package bg.tilchev.services;

import bg.tilchev.models.Student;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */


public interface StudentService {

    void persist(Student course);

    long count();

    List<Student> getAllStudents();
}
