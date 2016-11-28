package bg.tilchev.io;

import bg.tilchev.models.*;
import bg.tilchev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by Todor Ilchev on 2016-11-07.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private CommentService commentService;
    private DiagnoseService diagnoseService;
    private MedicamentService medicamentService;
    private PatientService patientService;
    private VisitationService visitationService;
    private DoctorService doctorService;

    private HospitalService<Comment> asd;
    private HospitalService<Diagnose> dsa;

    @Autowired
    public Terminal(CommentService commentService,
                    DiagnoseService diagnoseService,
                    MedicamentService medicamentService,
                    PatientService patientService,
                    VisitationService visitationService,
                    DoctorService doctorService) {
        this.commentService = commentService;
        this.diagnoseService = diagnoseService;
        this.medicamentService = medicamentService;
        this.patientService = patientService;
        this.visitationService = visitationService;
        this.doctorService = doctorService;
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    @PostConstruct
    private void seed() {
        if (this.isDataPopulated()) {
            return;
        }
        Patient patient = new Patient();
        patient.setFirstName("Pesho");
        patient.setLastName("Ivanov");
        patient.setAddress("Majnata si");
        patient.setBirthDate(new Date());
        patient.setEmail("pesho@abv.bg");
        patient.setMedicalInsurance(true);
        patient.setPicture(new byte[10]);
        this.patientService.persist(patient);

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Vaaaaj");
        diagnose.setPatient(patient);
        this.diagnoseService.persist(diagnose);

        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setPatient(patient);
        this.visitationService.persist(visitation);

        Comment comment = new Comment();
        comment.setText("dejba");
        comment.setDiagnose(diagnose);
        comment.setVisitation(visitation);
        this.commentService.persist(comment);

        Medicament medicament = new Medicament();
        medicament.setName("Nacepin");
        medicament.addPatient(patient);
        this.medicamentService.persist(medicament);

        Doctor doctor = new Doctor("Radeva", "Alkoholism");
        doctor.addVisitation(visitation);
        this.doctorService.persist(doctor);
    }

    private boolean isDataPopulated() {
        return this.commentService.count() > 0 ||
                this.diagnoseService.count() > 0 ||
                this.medicamentService.count() > 0 ||
                this.patientService.count() > 0 ||
                this.visitationService.count() > 0 ||
                this.doctorService.count() > 0;
    }
}
