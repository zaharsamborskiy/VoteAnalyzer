package main;

import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.TodoSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class todoController
{
    @Autowired
    private TodoRepository todoRepository;
    @GetMapping("/todo/")
    public List<TodoSingle> list()
    {
        Iterable<TodoSingle> todoIterable = todoRepository.findAll();
        ArrayList<TodoSingle> arrayTodo = new ArrayList<>();
        for (TodoSingle todoSingle : todoIterable)
        {
            arrayTodo.add(todoSingle);
        }
        return arrayTodo;
    }
    @PostMapping("/todo/")
    public int add(TodoSingle todoSingle)
    {
        TodoSingle todoRepo = todoRepository.save(todoSingle);
        return todoRepo.getId();
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Optional<TodoSingle> optional = todoRepository.findById(id);
        if (!optional.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/todo/{id}")
    public ResponseEntity<TodoSingle> update(@PathVariable int id, @ModelAttribute TodoSingle todoSingle)
    {
        Optional<TodoSingle> todoRepo = todoRepository.findById(id);
        if (todoRepo.isPresent()) {
            todoRepository.save(todoSingle);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/todo/")
    public ResponseEntity updateAllTodos(int year)
    {
        Iterable<TodoSingle> iterable = todoRepository.findAll();
        ArrayList<TodoSingle> arrayList = new ArrayList<>();
        for (TodoSingle todoSingle : iterable)
        {
            todoSingle.setYear(year);
            arrayList.add(todoSingle);
        }
        todoRepository.saveAll(iterable);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        Optional<TodoSingle> todoRepo = todoRepository.findById(id);
        if (todoRepo.isPresent())
        {
            todoRepository.delete(todoRepo.get());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/todo/")
    public ResponseEntity deleteAllToDO()
    {
        Iterable<TodoSingle> iterable = todoRepository.findAll();
        if (iterable.iterator().hasNext())
        {
            todoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
