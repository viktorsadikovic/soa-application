package mk.ukim.finki.soa.forumservice.repository;

import mk.ukim.finki.soa.forumservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
