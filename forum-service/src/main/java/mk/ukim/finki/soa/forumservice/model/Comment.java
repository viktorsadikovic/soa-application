package mk.ukim.finki.soa.forumservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8000)
    private String text;

    private LocalDateTime submissionTime;

    public Comment(String text) {
        this.text = text;
        this.submissionTime = LocalDateTime.now();
    }
}
