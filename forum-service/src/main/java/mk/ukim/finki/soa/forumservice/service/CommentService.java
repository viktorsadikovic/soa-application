package mk.ukim.finki.soa.forumservice.service;

import mk.ukim.finki.soa.forumservice.model.Comment;

public interface CommentService {
    Comment findById(Long id);

    Comment save(Comment comment);

    Comment edit(Comment comment);

    Comment delete(Long id);
}
