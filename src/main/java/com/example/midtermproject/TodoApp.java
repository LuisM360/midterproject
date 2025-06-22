package com.example.midtermproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TodoApp extends Application {
    private TodoController controller;
    private ListView<TodoItem> todoListView;
    private TextField newTodoField;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        controller = new TodoController();

        createComponents();

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(newTodoField, createAddButton());

        root.getChildren().addAll(
                new Label("Todo List"),
                inputBox,
                todoListView,
                createButtonBox(),
                statusLabel
        );

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Todo List App");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateStatus();
    }

    private void createComponents() {
        todoListView = new ListView<>();
        newTodoField = new TextField();
        newTodoField.setPromptText("Enter new todo item...");
        statusLabel = new Label();

        newTodoField.setOnAction(e -> addTodoItem());
    }

    private Button createAddButton() {
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addTodoItem());
        return addButton;
    }

    private HBox createButtonBox() {
        Button toggleButton = new Button("Toggle Complete");
        Button deleteButton = new Button("Delete");

        toggleButton.setOnAction(e -> toggleSelectedItem());
        deleteButton.setOnAction(e -> deleteSelectedItem());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(toggleButton, deleteButton);
        return buttonBox;
    }

    private void addTodoItem() {
        String text = newTodoField.getText();
        if (!text.trim().isEmpty()) {
            controller.addTodoItem(text);
            newTodoField.clear();
            refreshListView();
            updateStatus();
        }
    }

    private void toggleSelectedItem() {
        TodoItem selected = todoListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.toggleTodoItem(selected);
            refreshListView();
            updateStatus();
        }
    }

    private void deleteSelectedItem() {
        TodoItem selected = todoListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.removeTodoItem(selected);
            refreshListView();
            updateStatus();
        }
    }

    private void refreshListView() {
        todoListView.getItems().clear();
        todoListView.getItems().addAll(controller.getAllTodoItems());
    }

    private void updateStatus() {
        int total = controller.getTotalCount();
        int completed = controller.getCompletedCount();
        statusLabel.setText(String.format("Total: %d | Completed: %d | Pending: %d",
                total, completed, total - completed));
    }

    public static void main(String[] args) {
        launch(args);
    }
}