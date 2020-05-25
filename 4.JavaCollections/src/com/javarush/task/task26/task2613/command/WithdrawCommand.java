package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String curCode=ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator =
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode);

        while(true){
            int summ =0;
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                summ=Integer.parseInt(ConsoleHelper.readString());
                if(summ<=0){
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if(!manipulator.isAmountAvailable(summ)){
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
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
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),summ,curCode));
                break;

            }
            catch(NotEnoughMoneyException e){
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
        }


    }
}
