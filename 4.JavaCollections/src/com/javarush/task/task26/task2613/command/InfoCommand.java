package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class InfoCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        for(CurrencyManipulator cur:CurrencyManipulatorFactory.getAllCurrencyManipulators()){
            if (cur.hasMoney()){
            ConsoleHelper.writeMessage(cur.getCurrencyCode()+" - "+cur.getTotalAmount());}
            else {
                ConsoleHelper.writeMessage("No money available.");
            }

        }
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()){
            ConsoleHelper.writeMessage("No money available.");

        }

    }
}
