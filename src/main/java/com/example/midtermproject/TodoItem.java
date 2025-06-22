package com.example.midtermproject;

public class TodoItem {
    private String description;
    private boolean completed;

    public TodoItem(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        return (completed ? "✓ " : "○ ") + description;
    }
}