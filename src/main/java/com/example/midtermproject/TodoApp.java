package com.example.midtermproject;

import java.util.List;
import java.util.Scanner;

public class TodoApp {
    private final TodoController controller;
    private final Scanner scanner;

    public TodoApp() {
        controller = new TodoController();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Todo List Application ===");
        System.out.println("Welcome to your personal todo manager!");

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTodo();
                case "2" -> listTodos();
                case "3" -> toggleTodo();
                case "4" -> deleteTodo();
                case "5" -> showStats();
                case "6" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Empty line for readability
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Todo Menu ---");
        System.out.println("1. Add todo");
        System.out.println("2. List todos");
        System.out.println("3. Toggle todo completion");
        System.out.println("4. Delete todo");
        System.out.println("5. Show statistics");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void addTodo() {
        System.out.print("Enter todo description: ");
        String description = scanner.nextLine().trim();

        if (description.isEmpty()) {
            System.out.println("Description cannot be empty!");
            return;
        }

        controller.addTodoItem(description);
        System.out.println("Todo added successfully!");
    }

    private void listTodos() {
        List<TodoItem> todos = controller.getAllTodoItems();

        if (todos.isEmpty()) {
            System.out.println("No todos found. Add some tasks to get started!");
            return;
        }

        System.out.println("\n--- Your Todos ---");
        for (int i = 0; i < todos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, todos.get(i));
        }
    }

    private void toggleTodo() {
        List<TodoItem> todos = controller.getAllTodoItems();

        if (todos.isEmpty()) {
            System.out.println("No todos to toggle!");
            return;
        }

        listTodos();
        System.out.print("Enter todo number to toggle: ");

        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index >= 0 && index < todos.size()) {
                controller.toggleTodoItem(todos.get(index));
                System.out.println("Todo toggled successfully!");
            } else {
                System.out.println("Invalid todo number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private void deleteTodo() {
        List<TodoItem> todos = controller.getAllTodoItems();

        if (todos.isEmpty()) {
            System.out.println("No todos to delete!");
            return;
        }

        listTodos();
        System.out.print("Enter todo number to delete: ");

        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index >= 0 && index < todos.size()) {
                controller.removeTodoItem(todos.get(index));
                System.out.println("Todo deleted successfully!");
            } else {
                System.out.println("Invalid todo number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private void showStats() {
        int total = controller.getTotalCount();
        int completed = controller.getCompletedCount();
        int pending = total - completed;

        System.out.println("\n--- Statistics ---");
        System.out.printf("Total todos: %d%n", total);
        System.out.printf("Completed: %d%n", completed);
        System.out.printf("Pending: %d%n", pending);

        if (total > 0) {
            double completionRate = (double) completed / total * 100;
            System.out.printf("Completion rate: %.1f%%%n", completionRate);
        }
    }

    public static void main(String[] args) {
        new TodoApp().run();
    }
}