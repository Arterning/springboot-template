package cn.ning.springboot.starter.service;

import cn.ning.springboot.starter.annotation.LogExecutionTime;
import cn.ning.springboot.starter.common.CopyUtil;
import cn.ning.springboot.starter.entity.Person;
import cn.ning.springboot.starter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Page<Person> findPage(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Person findOneById(Long id) {
        return personRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @LogExecutionTime
    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Long id, Person person) {
        return personRepository.update(id, person);
    }

    public Person patch(Long id, Person person) {
        Person existingPerson = findOneById(id);
        CopyUtil.copyNonNullProperties(person, existingPerson);

        return personRepository.update(id, person);
    }

    public Person delete(Long id) {
        Person person = findOneById(id);
        personRepository.delete(person);

        return person;
    }

}
