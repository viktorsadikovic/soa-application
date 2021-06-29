package mk.ukim.finki.soa.forumservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4000)
    private String title;

    @Column(length = 8000)
    private String description;

    private LocalDateTime submissionTime;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Comment> comments;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
        this.submissionTime = LocalDateTime.now();
    }
}
