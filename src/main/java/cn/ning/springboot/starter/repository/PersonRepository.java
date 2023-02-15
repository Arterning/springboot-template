package cn.ning.springboot.starter.repository;

import cn.ning.springboot.starter.entity.Person;
import io.jsonwebtoken.lang.Assert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    default Person create(Person person) {
        return this.save(person);
    }

    default Person update(Long id, Person person) {
        Assert.notNull(id);

        return this.save(person);
    }

    default Person upsert(Person person) {
        if (person.getId() == null) {
            return create(person);
        }

        return update(person.getId(), person);
    }
}
