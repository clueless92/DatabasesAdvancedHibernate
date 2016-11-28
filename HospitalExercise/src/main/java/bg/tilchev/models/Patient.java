package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-07.
 */

@Entity
@Table(name = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private Date birthDate;

    @Column(name = "picture", columnDefinition = "MEDIUMBLOB")
    private byte[] picture;

    @Column(name = "has_medical_insurance")
    private boolean hasMedicalInsurance;

    @OneToMany(mappedBy = "patient", targetEntity = Visitation.class)
    private Set<Visitation> visitations;

    @OneToMany(mappedBy = "patient", targetEntity = Diagnose.class)
    private Set<Diagnose> diagnoses;

    @ManyToMany(mappedBy = "patients", targetEntity = Medicament.class)
    private Set<Medicament> medicaments;

    public Patient() {
        super();
        this.setDiagnoses(new HashSet<>());
        this.setVisitations(new HashSet<>());
        this.setMedicaments(new HashSet<>());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public boolean hasMedicalInsurance() {
        return this.hasMedicalInsurance;
    }

    public void setMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Set<Visitation> getVisitations() {
        if(this.visitations == null) {
            this.visitations = new HashSet<>();
        }
        return this.visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        if (this.diagnoses == null) {
            this.diagnoses = new HashSet<>();
        }
        return this.diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getMedicaments() {
        if(this.medicaments == null) {
            this.medicaments = new HashSet<>();
        }
        return this.medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public void addMedicament(Medicament medicament) {
        this.getMedicaments().add(medicament);
    }

    public void addVisitation(Visitation visitation) {
        this.getVisitations().add(visitation);
    }

    public void addDiagnose(Diagnose diagnose) {
        this.getDiagnoses().add(diagnose);
    }
}
