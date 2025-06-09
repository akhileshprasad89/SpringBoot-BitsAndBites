package com.lcwd.com.controllers;

import com.lcwd.com.models.Todo;
import com.lcwd.com.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {
    Logger logger= LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;
    Random random=new Random();
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        String name=null;
        logger.info("info hai {}",name.length());
        int id=random.nextInt(9999999);
        todo.setId(id);
        Date currentDate=new Date();
        todo.setAddedDate(currentDate);
        logger.info("Create ToDo");
      Todo todo1=  todoService.createTodo(todo);

      return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
      List<Todo> allTodo = todoService.getAllTodo();
      return new ResponseEntity<>(allTodo,HttpStatus.OK);
    }
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodo(@PathVariable int todoId){
      Todo todo =  todoService.getTodo(todoId);
      return new ResponseEntity<>(todo,HttpStatus.OK);
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoUpdate,@PathVariable int todoId){
        Todo todo =todoService.updateTodo(todoUpdate,todoId);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }
  /*  @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerException(NullPointerException exception){
        System.out.println(exception.getMessage());
        System.out.println("Null Pointer exception Generate");
        return new ResponseEntity<>("error hai bhai"+exception.getMessage(),HttpStatus.EXPECTATION_FAILED);

    } */

}
