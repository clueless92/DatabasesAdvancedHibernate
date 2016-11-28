package bg.tilchev.serviceImpls;

import bg.tilchev.models.Student;
import bg.tilchev.repos.StudentRepo;
import bg.tilchev.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void persist(Student student) {
        this.studentRepo.saveAndFlush(student);
    }

    @Override
    public long count() {
        return this.studentRepo.count();
    }

    public List<Student> getAllStudents() {
        return this.studentRepo.findAll();
    }
}
