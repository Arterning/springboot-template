package cn.ning.springboot.starter.repository;

import java.util.List;

import cn.ning.springboot.starter.entity.relation.article.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
  
  List<Tutorial> findTutorialsByTagsId(Long tagId);
}
