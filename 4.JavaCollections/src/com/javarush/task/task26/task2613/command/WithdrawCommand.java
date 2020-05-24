package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        CurrencyManipulator manipulator =
                CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        while(true){
            int summ =0;
            ConsoleHelper.writeMessage("Введите сумму для снятия ");
            try {
                summ=Integer.parseInt(ConsoleHelper.readString());
                if(summ<=0){
                    ConsoleHelper.writeMessage("Данные введены не корректно");
                    continue;
                }
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage("Данные введены не корректно");
                continue;
            }
            if(!manipulator.isAmountAvailable(summ)){
                continue;
            }
            try{Map<Integer,Integer> map= manipulator.withdrawAmount(summ);
                Map<Integer,Integer> reverseMap=new TreeMap<>(Comparator.reverseOrder());
                for(Map.Entry<Integer,Integer> entry:map.entrySet()){
                    reverseMap.put(entry.getKey(),entry.getValue());
                }
                for(Map.Entry<Integer,Integer> entry:reverseMap.entrySet()){
                    ConsoleHelper.writeMessage("\t"+entry.getKey()+" - "+entry.getValue());
                }
                ConsoleHelper.writeMessage("Транзакция прошла успешно ");
                break;

            }
            catch(NotEnoughMoneyException e){
                ConsoleHelper.writeMessage("Недостаточно денег на счету ");
                continue;
            }
        }


    }
}
