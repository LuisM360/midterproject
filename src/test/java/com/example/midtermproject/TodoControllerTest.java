package com.example.midtermproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoControllerTest {

    private TodoController controller;

    @BeforeEach
    void setUp(){
        controller = new TodoController();
    }

    @Test
    @DisplayName("Should add valid todo item")
    void addValidTodoItem() {
        controller.addTodoItem("Buy Video Games");

        assertEquals(1, controller.getTotalCount());
        assertEquals("Buy Video Games", controller.getAllTodoItems().get(0).getDescription());
    }

    @Test
    @DisplayName("Should not add empty or null todo items")
    void addInvalidTodoItems() {
        controller.addTodoItem("");
        controller.addTodoItem("   ");
        controller.addTodoItem(null);

        assertEquals(0, controller.getTotalCount());
    }


    @Test
    @DisplayName("Should remove todo item successfully")
    void removeTodoItem() {
        controller.addTodoItem("Task 1");
        controller.addTodoItem("Task 2");

        TodoItem itemToRemove = controller.getAllTodoItems().get(0);
        controller.removeTodoItem(itemToRemove);

        assertEquals(1, controller.getTotalCount());
        assertEquals("Task 2", controller.getAllTodoItems().get(0).getDescription());
    }

    @Test
    @DisplayName("Should toggle todo item completion status")
    void toggleTodoItem() {
        controller.addTodoItem("Exercise");
        TodoItem item = controller.getAllTodoItems().get(0);

        assertFalse(item.isCompleted());

        controller.toggleTodoItem(item);
        assertTrue(item.isCompleted());

        controller.toggleTodoItem(item);
        assertFalse(item.isCompleted());
    }

    @Test
    @DisplayName("Should return correct completed items")
    void getCompletedItems() {
        controller.addTodoItem("Task 1");
        controller.addTodoItem("Task 2");
        controller.addTodoItem("Task 3");

        List<TodoItem> allItems = controller.getAllTodoItems();
        controller.toggleTodoItem(allItems.get(0));
        controller.toggleTodoItem(allItems.get(2));

        List<TodoItem> completedItems = controller.getCompletedItems();
        assertEquals(2, completedItems.size());
        assertTrue(completedItems.get(0).isCompleted());
        assertTrue(completedItems.get(1).isCompleted());
    }

    @Test
    @DisplayName("Should return correct pending items")
    void getPendingItems() {
        controller.addTodoItem("Task 1");
        controller.addTodoItem("Task 2");
        controller.addTodoItem("Task 3");

        List<TodoItem> allItems = controller.getAllTodoItems();
        controller.toggleTodoItem(allItems.get(1));

        List<TodoItem> pendingItems = controller.getPendingItems();
        assertEquals(2, pendingItems.size());
        assertFalse(pendingItems.get(0).isCompleted());
        assertFalse(pendingItems.get(1).isCompleted());
    }

    @Test
    @DisplayName("Should return correct counts")
    void testCounts() {
        assertEquals(0, controller.getTotalCount());
        assertEquals(0, controller.getCompletedCount());

        controller.addTodoItem("Task 1");
        controller.addTodoItem("Task 2");
        controller.addTodoItem("Task 3");

        assertEquals(3, controller.getTotalCount());
        assertEquals(0, controller.getCompletedCount());

        controller.toggleTodoItem(controller.getAllTodoItems().get(0));
        controller.toggleTodoItem(controller.getAllTodoItems().get(1));

        assertEquals(3, controller.getTotalCount());
        assertEquals(2, controller.getCompletedCount());
    }
}