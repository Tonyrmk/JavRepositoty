package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.Console;
import java.util.ResourceBundle;

class DepositCommand implements Command{
    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] getValue =ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        manipulator.addAmount(Integer.parseInt(getValue[0]),Integer.parseInt(getValue[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),Integer.parseInt(getValue[0])*Integer.parseInt(getValue[1]),currencyCode));
    }
}
