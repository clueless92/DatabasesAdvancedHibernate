package bg.tilchev.serviceimpls;

import bg.tilchev.models.Comment;
import bg.tilchev.repos.CommentRepo;
import bg.tilchev.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public void persist(Comment comment) {
        this.commentRepo.saveAndFlush(comment);
    }

    @Override
    public long count() {
        return this.commentRepo.count();
    }
}
