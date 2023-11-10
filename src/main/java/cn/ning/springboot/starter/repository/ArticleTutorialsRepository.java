package cn.ning.springboot.starter.repository;

import cn.ning.springboot.starter.entity.relation.article.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ArticleTutorialsRepository extends JpaRepository<Tutorial, Long>, QueryByExampleExecutor<Tutorial>, JpaSpecificationExecutor<Tutorial> {


    List<Tutorial> findByTitle(String title);


}