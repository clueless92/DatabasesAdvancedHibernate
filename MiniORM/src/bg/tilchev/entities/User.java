package bg.tilchev.entities;

import bg.tilchev.persistence.Column;
import bg.tilchev.persistence.Entity;
import bg.tilchev.persistence.Id;

/**
 * Created by Todor Ilchev on 2016-10-30.
 */

@Entity(name = "users")
public class User {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "registration_date")
    private java.util.Date registrationDate;

    @SuppressWarnings("unused")
    public User() {
        super();
    }

    public User(String name, Integer age, java.util.Date registrationDate) {
        this.setName(name);
        this.setAge(age);
        this.setRegistrationDate(registrationDate);
    }

    public long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public java.util.Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(java.util.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Id: ").append(this.getId()).append(System.lineSeparator());
        output.append("Name: ").append(this.getName()).append(System.lineSeparator());
        output.append("Age: ").append(this.getAge()).append(System.lineSeparator());
        output.append("Registration Date: ").append(this.getRegistrationDate());

        return output.toString();
    }
}
