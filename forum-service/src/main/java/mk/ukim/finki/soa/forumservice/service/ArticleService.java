package mk.ukim.finki.soa.forumservice.service;

import mk.ukim.finki.soa.forumservice.model.Article;
import mk.ukim.finki.soa.forumservice.model.Comment;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    Article findById(Long id);

    Article save(Article article);

    Article edit(Article article);

    Comment addComment(Long id, Comment comment);

    Article removeComment(Long articleId, Long commentId);

    Article delete(Long id);
}
