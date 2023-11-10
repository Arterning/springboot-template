package cn.ning.springboot.starter.repository;

import cn.ning.springboot.starter.entity.relation.article.Tutorial;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTutorialsRepositoryTest {

    @Autowired
    ArticleTutorialsRepository articleTutorialsRepository;

    /**
     * Use Example
     * @throws JsonProcessingException
     * 为什么要加事务注解？
     * 为了避免出现 could not initialize proxy - no Session 错误
     */
    @Test
    @Transactional
    public void contextLoads() throws JsonProcessingException {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("good");
        Example<Tutorial> example = Example.of(tutorial);
        List<Tutorial> all = articleTutorialsRepository.findAll(example);
        all.forEach(d -> System.out.println(d.getTitle()));

//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(all));
    }


    /**
     * Build Query
     */
    @Test
    public void testQB() {
        articleTutorialsRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("published"), false)
                ).stream()
                .forEach(d -> {
                    System.out.println(d.getTitle());
                });
    }


    /**
     * Build Query
     * 这构建查询条件也太麻烦了。还不如tk-mybatis呢
     * 比较好的封装：<a href="https://github1s.com/elunez/eladmin/blob/HEAD/eladmin-common/src/main/java/me/zhengjie/utils/QueryHelp.java#L93">...</a>
     */
    @Test
    public void testQB2() {
        articleTutorialsRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                        {
                            List<Predicate> predicateList = new ArrayList<>();
                            Join join = root.join("xxx", JoinType.LEFT);
                            predicateList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("xx"), "%34%")));
                            predicateList.add(criteriaBuilder.and(criteriaBuilder.le(root.get("xx"), 23)));
                            predicateList.add(criteriaBuilder.and(criteriaBuilder.gt(root.get("xx"), 23)));
                            predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(join.get("xx"), 234)));
                            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
                            return criteriaQuery.getRestriction();
                        }
                ).stream()
                .forEach(d -> {
                    System.out.println(d.getTitle());
                });
    }


    /**
     * Base Mapper
     */

    @Test
    public void testFindByTitle() {
        articleTutorialsRepository.findByTitle("很好")
                .forEach(d -> System.out.println(d.getDescription()));
    }
}