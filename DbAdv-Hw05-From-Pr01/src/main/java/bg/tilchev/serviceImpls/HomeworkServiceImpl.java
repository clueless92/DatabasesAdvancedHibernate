package bg.tilchev.serviceImpls;

import bg.tilchev.models.Homework;
import bg.tilchev.repos.HomeworkRepo;
import bg.tilchev.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private HomeworkRepo homeworkRepo;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepo homeworkRepo) {
        this.homeworkRepo = homeworkRepo;
    }

    @Override
    public void persist(Homework homework) {
        this.homeworkRepo.saveAndFlush(homework);
    }

    public long count() {
        return this.homeworkRepo.count();
    }
}
