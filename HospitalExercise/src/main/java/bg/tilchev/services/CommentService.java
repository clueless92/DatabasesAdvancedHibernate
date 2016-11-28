package bg.tilchev.services;

import bg.tilchev.models.Comment;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
public interface CommentService {

    void persist(Comment comment);

    long count();
}
