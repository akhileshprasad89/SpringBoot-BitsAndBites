package com.lcwd.com.services;

import com.lcwd.com.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService {
    Logger logger= LoggerFactory.getLogger(TodoService.class);
    List<Todo> todos= new ArrayList<>();
    public Todo createTodo(Todo todo){
    todos.add(todo);
    logger.info("Todos {}",this.todos);
    return todo;
    }

    public List<Todo> getAllTodo() {
        return todos;
    }

    public Todo getTodo(int todoId) {
      Todo todo =  todos.stream().filter(t->todoId==t.getId()).findAny().get();
      logger.info("Todo {}",todo);
      return todo;
    }

    public Todo updateTodo(Todo todoUpdate, int todoId) {
        List<Todo> newUpdatedList = todos.stream().map(t -> {
            if (t.getId() == todoId) {
                // Set the same ID to the new object (optional if already same)
                todoUpdate.setId(todoId);
                return todoUpdate;
            } else {
                return t;
            }
        }).collect(Collectors.toList());

        // Replace the old list with the updated list
        todos = newUpdatedList;

        return todoUpdate;
    }

    public void deleteTodo(int todoId) {

    }
}
