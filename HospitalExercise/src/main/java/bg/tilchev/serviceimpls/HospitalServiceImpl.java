//package bg.tilchev.serviceimpls;
//
//import bg.tilchev.repos.HospitalRepo;
//import bg.tilchev.services.HospitalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//
///**
// * Created by Todor Ilchev on 2016-11-10.
// */
//
//@Service
//public class HospitalServiceImpl<E extends Serializable> implements HospitalService<E> {
//
//    private HospitalRepo<E> hospitalRepo;
//
//    @Autowired
//    public HospitalServiceImpl(HospitalRepo<E> hospitalRepo) {
//        this.hospitalRepo = hospitalRepo;
//    }
//
//    @Override
//    public void persist(E entity) {
//        this.hospitalRepo.saveAndFlush(entity);
//    }
//
//    @Override
//    public long count() {
//        return this.hospitalRepo.count();
//    }
//}
