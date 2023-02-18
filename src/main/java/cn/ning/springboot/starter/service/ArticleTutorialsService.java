package cn.ning.springboot.starter.service;

import cn.ning.springboot.starter.dto.ArticleTutorialsDTO;
import cn.ning.springboot.starter.entity.ArticleTutorials;
import cn.ning.springboot.starter.repository.ArticleTutorialsRepository;
import cn.ning.springboot.starter.vo.ArticleTutorialsQueryVO;
import cn.ning.springboot.starter.vo.ArticleTutorialsUpdateVO;
import cn.ning.springboot.starter.vo.ArticleTutorialsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ArticleTutorialsService {

    @Autowired
    private ArticleTutorialsRepository articleTutorialsRepository;

    public Long save(ArticleTutorialsVO vO) {
        ArticleTutorials bean = new ArticleTutorials();
        BeanUtils.copyProperties(vO, bean);
        bean = articleTutorialsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        articleTutorialsRepository.deleteById(id);
    }

    public void update(Long id, ArticleTutorialsUpdateVO vO) {
        ArticleTutorials bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        articleTutorialsRepository.save(bean);
    }

    public ArticleTutorialsDTO getById(Long id) {
        ArticleTutorials original = requireOne(id);
        return toDTO(original);
    }

    public Page<ArticleTutorialsDTO> query(ArticleTutorialsQueryVO vO) {
        ArticleTutorials articleTutorials = new ArticleTutorials();
        BeanUtils.copyProperties(vO, articleTutorials);
        Example<ArticleTutorials> example = Example.of(articleTutorials);
        Pageable pageable = PageRequest.of(0, 10);
        Page<ArticleTutorials> all = articleTutorialsRepository.findAll(example, pageable);
        return all.map(this::toDTO);
    }

    public Page<ArticleTutorialsDTO> queryAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ArticleTutorials> users = articleTutorialsRepository.findAll(pageable);
        return users.map(this::toDTO);
    }

    private ArticleTutorialsDTO toDTO(ArticleTutorials original) {
        ArticleTutorialsDTO bean = new ArticleTutorialsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ArticleTutorials requireOne(Long id) {
        return articleTutorialsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
