package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.TodoSingle;

import java.util.List;


@RestController
public class todoController
{
    @GetMapping("/todo/")
    public List<TodoSingle> list()
    {
        return Storage.getAllToDo();
    }
    @PostMapping("/todo/")
    public int add(TodoSingle todoSingle)
    {
        return Storage.addToDo(todoSingle);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        TodoSingle todoSingle = Storage.getToDo(id);
        if (todoSingle == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(todoSingle, HttpStatus.OK);
    }
    @PutMapping(value = "/todo/{id}")
    public ResponseEntity<TodoSingle> update(@PathVariable int id, @ModelAttribute TodoSingle todoSingle)
    {
        if (Storage.todoMap.containsKey(id)) {
            Storage.update(id, todoSingle);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("id not found").body(null);
    }

    @PutMapping("/todo/")
    public ResponseEntity updateAllCourses(int year)
    {
        Storage.updateAll(year);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        if (Storage.todoMap.containsKey(id))
        {
            Storage.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/todo/")
    public ResponseEntity deleteAllToDO()
    {
        if (Storage.todoMap.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Storage.deleteAllTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
