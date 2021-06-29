package mk.ukim.finki.soa.forumservice.web;

import mk.ukim.finki.soa.forumservice.model.Article;
import mk.ukim.finki.soa.forumservice.model.Comment;
import mk.ukim.finki.soa.forumservice.service.ArticleService;
import mk.ukim.finki.soa.forumservice.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    private final ArticleService articleService;
    private final CommentService commentService;

    public ForumController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/articles/all")
    public List<Article> getArticles() {
        return this.articleService.findAll();
    }

    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable Long id) {
        return this.articleService.findById(id);
    }

    @PostMapping("/articles/add")
    public Article addArticle(@RequestBody Article article) {
        return this.articleService.save(article);
    }

    @PostMapping("/articles/edit")
    public Article editArticle(@RequestBody Article article) {
        return this.articleService.edit(article);
    }

    @PostMapping("/articles/{id}/delete")
    public Article deleteArticle(@PathVariable Long id) {
        return this.articleService.delete(id);
    }

    @PostMapping("/articles/{id}/add-comment")
    public Comment addCommentToArticle(@PathVariable Long id,
                                       @RequestBody Comment comment) {
        return this.articleService.addComment(id, comment);
    }

    @PostMapping("/comments/edit")
    public Comment editComment(@RequestBody Comment comment) {
        return this.commentService.edit(comment);
    }

    @PostMapping("{articleId}/comments/{commentId}/delete")
    public Article deleteComment(@PathVariable Long articleId,
                                 @PathVariable Long commentId) {
        return this.articleService.removeComment(articleId, commentId);
    }
}
