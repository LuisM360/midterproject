package com.example.midtermproject;

import java.util.ArrayList;
import java.util.List;

public class TodoController {
    private List<TodoItem> todoItems;

    public TodoController() {
        this.todoItems = new ArrayList<>();
    }

    public void addTodoItem(String description) {
        if (description != null && !description.trim().isEmpty()) {
            todoItems.add(new TodoItem(description.trim()));
        }
    }

    public void removeTodoItem(TodoItem item) {
        todoItems.remove(item);
    }

    public void toggleTodoItem(TodoItem item) {
        item.toggleCompleted();
    }

    public List<TodoItem> getAllTodoItems() {
        return new ArrayList<>(todoItems);
    }

    public List<TodoItem> getCompletedItems() {
        return todoItems.stream()
                .filter(TodoItem::isCompleted)
                .toList();
    }

    public List<TodoItem> getPendingItems() {
        return todoItems.stream()
                .filter(item -> !item.isCompleted())
                .toList();
    }

    public int getTotalCount() {
        return todoItems.size();
    }

    public int getCompletedCount() {
        return (int) todoItems.stream().filter(TodoItem::isCompleted).count();
    }
}