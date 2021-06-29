package mk.ukim.finki.soa.forumservice.service.impl;

import mk.ukim.finki.soa.forumservice.model.Comment;
import mk.ukim.finki.soa.forumservice.repository.CommentRepository;
import mk.ukim.finki.soa.forumservice.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment findById(Long id) {
        return this.commentRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        Comment existingComment = this.findById(comment.getId());

        existingComment.setText(comment.getText());

        return this.commentRepository.save(existingComment);
    }

    @Override
    public Comment delete(Long id) {
        Comment comment = this.findById(id);

        this.commentRepository.delete(comment);

        return comment;
    }
}
