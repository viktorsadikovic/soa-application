package mk.ukim.finki.soa.forumservice.service.impl;

import mk.ukim.finki.soa.forumservice.model.Article;
import mk.ukim.finki.soa.forumservice.model.Comment;
import mk.ukim.finki.soa.forumservice.repository.ArticleRepository;
import mk.ukim.finki.soa.forumservice.service.ArticleService;
import mk.ukim.finki.soa.forumservice.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    public ArticleServiceImpl(ArticleRepository articleRepository, CommentService commentService) {
        this.articleRepository = articleRepository;
        this.commentService = commentService;
    }

    @Override
    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    @Override
    public Article findById(Long id) {
        return this.articleRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Article save(Article article) {
        article.setSubmissionTime(LocalDateTime.now());

        return this.articleRepository.save(article);
    }

    @Override
    public Article edit(Article article) {
        Article existingArticle = this.findById(article.getId());

        existingArticle.setTitle(article.getTitle());
        existingArticle.setDescription(article.getDescription());
        existingArticle.setComments(article.getComments());

        return this.articleRepository.save(existingArticle);
    }

    @Override
    public Comment addComment(Long id, Comment comment) {
        Article article = this.findById(id);

        comment.setSubmissionTime(LocalDateTime.now());
        comment = this.commentService.save(comment);

        article.getComments().add(comment);
        this.edit(article);

        return comment;
    }

    @Override
    public Article removeComment(Long articleId, Long commentId) {
        Article article = this.findById(articleId);
        Comment comment = this.commentService.findById(commentId);

        article.getComments().remove(comment);

        return this.edit(article);
    }

    @Override
    public Article delete(Long id) {
        Article article = this.findById(id);

        article.getComments().forEach(comment -> this.commentService.delete(comment.getId()));
        this.articleRepository.delete(article);

        return article;
    }
}
