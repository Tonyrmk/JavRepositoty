package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(" хочеш выйти - варианты <y,n>.");
        String s=ConsoleHelper.readString();
        if (s.equals("y")){
            ConsoleHelper.writeMessage("GoodBye");
        }
    }
}
