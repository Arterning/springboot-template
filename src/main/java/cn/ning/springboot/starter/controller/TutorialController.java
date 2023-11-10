package cn.ning.springboot.starter.controller;


import cn.ning.springboot.starter.dto.ArticleTutorialsDTO;
import cn.ning.springboot.starter.entity.relation.article.Tag;
import cn.ning.springboot.starter.entity.relation.article.Tutorial;
import cn.ning.springboot.starter.entity.relation.article.TutorialComment;
import cn.ning.springboot.starter.exception.ResourceNotFoundException;
import cn.ning.springboot.starter.repository.TagRepository;
import cn.ning.springboot.starter.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/v1")
public class TutorialController {

  @Autowired
  TutorialRepository tutorialRepository;

  @Autowired
  TagRepository tagRepository;

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
    List<Tutorial> tutorials = new ArrayList<Tutorial>();

    if (title == null)
      tutorialRepository.findAll().forEach(tutorials::add);
    else
      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Tutorial tutorial = tutorialRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

    return new ResponseEntity<>(tutorial, HttpStatus.OK);
  }

  @PostMapping("/tutorials")
  public ResponseEntity<?> createTutorial(@RequestBody ArticleTutorialsDTO request) {
    Tutorial tutorial = new Tutorial();
    tutorial.setTitle(request.getTitle());
    tutorial.setDescription(request.getDescription());
    tutorial.setPublished(request.getPublished());

    // 一对多关系
    Set<TutorialComment> set = request.getComments().stream().map(co -> {
      TutorialComment tutorialComment = new TutorialComment();
      tutorialComment.setContent(co);
      tutorialComment.setTutorial(tutorial);
      return tutorialComment;
    }).collect(Collectors.toSet());
    tutorial.setComments(set);

    // 多对多关系
    Set<Tag> tags = request.getTags().stream().map(tag -> {
      Tag entity = new Tag();
      entity.setName(tag);
      return entity;
    }).collect(Collectors.toSet());
    tutorial.setTags(tags);

    return new ResponseEntity<>("OK", HttpStatus.CREATED);
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    Tutorial _tutorial = tutorialRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

    _tutorial.setTitle(tutorial.getTitle());
    _tutorial.setDescription(tutorial.getDescription());
    _tutorial.setPublished(tutorial.isPublished());
    
    return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    tutorialRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    tutorialRepository.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }
}
