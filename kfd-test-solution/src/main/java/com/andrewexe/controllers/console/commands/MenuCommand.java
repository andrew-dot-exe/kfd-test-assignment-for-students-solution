package com.andrewexe.controllers.console.commands;

/// Интерфейс для реализации паттерна Команда, который будет использоваться в меню.
public interface MenuCommand {
    public String getTitle();
    public void execute();
}
