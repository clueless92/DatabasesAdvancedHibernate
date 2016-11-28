package bg.tilchev.io;

import bg.tilchev.models.*;
import bg.tilchev.services.CourseService;
import bg.tilchev.services.HomeworkService;
import bg.tilchev.services.ResourceService;
import bg.tilchev.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private CourseService courseService;
    private HomeworkService homeworkService;
    private ResourceService resourceService;
    private StudentService studentService;

    @Autowired
    public Terminal(CourseService courseService, HomeworkService homeworkService, ResourceService resourceService,
                     StudentService studentService) {
        this.courseService = courseService;
        this.homeworkService = homeworkService;
        this.resourceService = resourceService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.pr03Task01();
//        this.pr03Task02();
//        this.pr03Task03();
//        this.pr03Task04();
//        this.pr03Task05();

    }

    private void pr03Task01() {
        List<Student> students = this.studentService.getAllStudents();
        for (Student student : students) {
            String studentName = student.getName();
            System.out.println(studentName);
            Set<Homework> homeworks = student.getHomeworkSubmissions();
            for (Homework homework : homeworks) {
                String hwContent = homework.getContent();
                ContentType hwContentType = homework.getContentType();
                System.out.printf(" --- type: %s, descr: %s%n", hwContentType, hwContent);
            }
        }
    }

    private void pr03Task02() {
        List<Course> courses = this.courseService.getAllCoursesOrderedByDates();
        for (Course course : courses) {
            String courseName = course.getName();
            String courseDescr = course.getDescription();
            System.out.printf("%s, %s%n", courseName, courseDescr);
            Set<Resource> resources = course.getResources();
            for (Resource resource : resources) {
                System.out.println(resource);
            }
        }
    }

    private void pr03Task03() {
        List<Course> courses = this.courseService.getAllCoursesWithMoreThan5Res();
        for (Course course : courses) {
            String courseName = course.getName();
            int resourceCount = course.getResources().size();
            System.out.printf("%s, %s%n", courseName, resourceCount);
        }
    }

    private void pr03Task04() {
        List<Course> courses = this.courseService.getAllCoursesActiveOnGivenDate();
        for (Course course : courses) {
            int enrolledStudentsCount = course.getStudents().size();
            System.out.printf("%s, %s%n", course, enrolledStudentsCount);
        }
    }

    private void pr03Task05() {
        List<Student> students = this.studentService.getAllStudents();
        for (Student student : students) {
            String studentName = student.getName();
            System.out.println(studentName);
            Set<Course> courses = student.getCourses();
            System.out.println("COUNT: " + courses.size());
            BigDecimal totalPrice = new BigDecimal(0d);
            for (Course course : courses) {
                BigDecimal coursePrice = course.getPrice();
                totalPrice = totalPrice.add(coursePrice);
            }
            System.out.println("TOTAL: " + totalPrice);
            BigDecimal avgPrice = totalPrice.divide(new BigDecimal(courses.size()));
            System.out.println("AVG: " + avgPrice);
        }
    }

    @PostConstruct
    private void seed() {
        if (this.isDataPopulated()) {
            return;
        }
        Set<Homework> homeworks = new HashSet<>();
        Set<Course> courses = new HashSet<>();
        Set<Resource> resources = new HashSet<>();
        Set<Student> students = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Homework homework = new Homework("Homework" + i, ContentType.APPLICATION_ZIP, new Date());
            Course course = new Course("Course" + i, null, new Date(), new Date(), new BigDecimal(i));
            Resource resource = new Resource("Resource" + i, TypeOfResource.DOCUMENT, null);
            Student student = new Student("Student" + i, "089" + i, new Date(), new Date());

            homework.setCourse(course);
            homework.setStudent(student);
            resource.setCourse(course);

            homeworks.add(homework);
            courses.add(course);
            resources.add(resource);
            students.add(student);

            course.setStudents(students);
            course.setHomeworkSubmissions(homeworks);
            course.setResources(resources);
            student.setHomeworkSubmissions(homeworks);
            student.setCourses(courses);
        }

        Iterator<Student> studentIterator = students.iterator();
        Iterator<Resource> resourceIterator = resources.iterator();
        Iterator<Course> courseIterator = courses.iterator();
        Iterator<Homework> homeworkIterator = homeworks.iterator();

        for (int i = 0; i < students.size(); i++) {
            Student student = studentIterator.next();
            this.studentService.persist(student);
            Resource resource = resourceIterator.next();
            this.resourceService.persist(resource);
            Course course = courseIterator.next();
            this.courseService.persist(course);
            Homework homework = homeworkIterator.next();
            this.homeworkService.persist(homework);
        }
    }

    private boolean isDataPopulated() {
        return this.homeworkService.count() != 0 ||
                this.studentService.count() != 0 ||
                this.courseService.count() != 0 ||
                this.resourceService.count() != 0;
    }
}
