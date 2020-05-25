package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class InfoCommand implements Command{
    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.info_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        for(CurrencyManipulator cur:CurrencyManipulatorFactory.getAllCurrencyManipulators()){
            if (cur.hasMoney()){
            ConsoleHelper.writeMessage(cur.getCurrencyCode()+" - "+cur.getTotalAmount());}
            else {
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }

        }
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()){
            ConsoleHelper.writeMessage("No money available.");

        }

    }
}
