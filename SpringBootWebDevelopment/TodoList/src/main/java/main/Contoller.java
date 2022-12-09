package main;

import main.model.TodoRepository;
import main.model.TodoSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
public class Contoller
{
    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping("/")
    public String index(Model model)
    {
        Iterable<TodoSingle> todoIterable = todoRepository.findAll();
        ArrayList<TodoSingle> arrayTodo = new ArrayList<>();
        for (TodoSingle todoSingle : todoIterable)
        {
            arrayTodo.add(todoSingle);
        }
        model.addAttribute("booksCount", arrayTodo.size());
        model.addAttribute("books", arrayTodo);
        return "index";
    }
}
