package in.igt.demos.angjstodo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.igt.demos.angjstodo.model.Todo;

/**
 * @author eln7as
 *
 */
public interface TodoRepository extends MongoRepository<Todo, String>{

}
