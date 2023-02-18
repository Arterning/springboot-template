package cn.ning.springboot.starter.repository;

import cn.ning.springboot.starter.entity.ArticleTutorials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleTutorialsRepository extends JpaRepository<ArticleTutorials, Long>, JpaSpecificationExecutor<ArticleTutorials> {

}