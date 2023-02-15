package cn.ning.springboot.starter.entity.relation.article;

import javax.persistence.*;


@Entity
@Table(name = "article_tutorial_author")
public class TutorialAuthor {

    @Id
    private Long id;

    @Column
    private String authorName;

    //因为是一对一关系  所以使用mapsId 可以让外键主键合为一体
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "tutorial_id")
    private Tutorial tutorial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }
}
