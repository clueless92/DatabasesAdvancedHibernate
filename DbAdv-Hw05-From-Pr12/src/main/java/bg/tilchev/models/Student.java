package bg.tilchev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends Person {

    @Column
    private double avgGrade;

    @Column
    private boolean[] attendance;

    public Student() {
        super();
    }

    public double getAvgGrade() {
        return this.avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public boolean[] getAttendance() {
        return this.attendance;
    }

    public void setAttendance(boolean[] attendance) {
        this.attendance = attendance;
    }
}
