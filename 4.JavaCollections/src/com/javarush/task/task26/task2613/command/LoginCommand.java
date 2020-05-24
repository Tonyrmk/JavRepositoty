package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import javax.print.DocFlavor;
import java.util.ResourceBundle;

public class LoginCommand implements Command{
    private final static String CARDNUMBER ="123456789012";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
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
                 if (validCreditCards.containsKey(card)){
                    if (validCreditCards.getString(card).equals(pin))
                         ConsoleHelper.writeMessage("Верификация прошла успешно");
                         break;
                }
            }

        }
    }

