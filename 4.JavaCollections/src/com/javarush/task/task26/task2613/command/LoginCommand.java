package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import javax.print.DocFlavor;

public class LoginCommand implements Command{
    private final static String CARDNUMBER ="123456789012";
    private final static String PIN ="1234";
    @Override
    public void execute() throws InterruptOperationException {
        while(true){
            ConsoleHelper.writeMessage("Введите номер карты");

            String card=ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите пин");
            String pin=ConsoleHelper.readString();
            if(card.length()!=12 || pin.length()!=4 ){
                ConsoleHelper.writeMessage("Данные введены не корректно");
                continue;
            }
            if(card.equals(CARDNUMBER) && pin.equals(PIN)){
                ConsoleHelper.writeMessage("Верификация прошла успешно");
                break;
            }
            else {
                continue;
            }
        }
    }
}
