package in.igt.demos.angjstodo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.igt.demos.angjstodo.model.Todo;
import in.igt.demos.angjstodo.repositories.TodoRepository;

/**
 * @author eln7as
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	TodoRepository todoRepository;

	@GetMapping("/todoList")
	public List<Todo> getToDoList() {
		return todoRepository.findAll();
	}

	@PostMapping("/todoList")
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		todo.setCompleted(false);
		return todoRepository.save(todo);
	}

	@GetMapping(value = "/todoList/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id) {
		Optional<Todo> todo = todoRepository.findById(id);

		if (!todo.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(todo.get(), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/todoList/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody Todo todo) {
		Optional<Todo> todoDataOpt = todoRepository.findById(id);
		if (!todoDataOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Todo todoData = todoDataOpt.get();
		todoData.setTitle(todo.getTitle());
		todoData.setCompleted(todo.isCompleted());
		Todo updatedTodo = todoRepository.save(todoData);
		return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
	}

	@DeleteMapping(value = "/todoList/{id}")
	public void deleteTodo(@PathVariable("id") String id) {
		todoRepository.deleteById(id);
	}

}
