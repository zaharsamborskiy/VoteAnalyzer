package main;

import main.model.TodoSingle;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class Storage
{
    private static AtomicInteger atomicInteger = new AtomicInteger();
    public static ConcurrentHashMap<Integer ,TodoSingle> todoMap = new ConcurrentHashMap<>();

    public static int addToDo(TodoSingle todoSingle)
    {
        int id = atomicInteger.incrementAndGet();
        todoSingle.setId(id);
        todoMap.put(id,todoSingle);
        return id;
    }

    public static List<TodoSingle> getAllToDo()
    {
        ArrayList<TodoSingle> todoList = new ArrayList<>();
        todoList.addAll(todoMap.values());
        return todoList;
    }

    public static TodoSingle getToDo(int todoId)
    {
        if (todoMap.containsKey(todoId))
        {
            return todoMap.get(todoId);
        }
        return null;
    }

    public static void update(int id, TodoSingle todoSingle)
    {
        todoMap.replace(id, todoSingle);
    }

    public static void updateAll(int year)
    {
        for(Map.Entry<Integer, TodoSingle> entry : todoMap.entrySet())
        {
             entry.getValue().setYear(year);
        }

    }

    public static void delete(int id)
    {
        todoMap.remove(id);
    }

    public static void deleteAllTodos()
    {
        todoMap.clear();
    }
}
