package cn.ning.springboot.starter.repository;

import java.util.List;

import cn.ning.springboot.starter.entity.relation.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Long> {
  List<Tag> findTagsByTutorialsId(Long tutorialId);
}
