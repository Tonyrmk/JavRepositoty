package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class ConsoleHelper {
    private static BufferedReader bis =new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException{
        String s="" ;
        try {
            s=bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (s.equalsIgnoreCase("Exit")){
            throw new InterruptOperationException();
        }
        return s;
    }
    public static String askCurrencyCode() throws InterruptOperationException{
        writeMessage("Введите код валюты , который содержит 3 символа ");
        String code =null;
       while(true) {
            code =readString();
            if (code.length()!=3){
                writeMessage("Вы ввели не корректный код , коддолжен соержать 3 символа ");}

            else {
                code =code.toUpperCase();
                break;
            }
       }
        return code;
    }

    public static String[] getValidTwoDigits(String currencyCode)throws InterruptOperationException{
        writeMessage("Ведите номинал и количество банкнот");
        String[] curVal=null;
        while (true){
            String s =readString();
            curVal=s.split(" ");
            if (curVal.length!=2){
                writeMessage("Данные введены не корректно посторите ввод номинала и количества банкнот");
                continue;
            }
           try{ if(Integer.parseInt(curVal[0])>=0 && Integer.parseInt(curVal[1])>=0){
            break;}
             else {
                 writeMessage("Данные введены не корректно посторите ввод номинала и количества банкнот");
                 continue;
           }
           }catch ( NumberFormatException e ){
               writeMessage("Данные введены не корректно посторите ввод номинала и количества банкнот");
               continue;
            }
        }
        return curVal;
    }
    public static Operation askOperation()throws InterruptOperationException{
        writeMessage("1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT ");
        Operation op;
       while(true) {
           try{
               int num = Integer.parseInt(readString());
               op =Operation.getAllowableOperationByOrdinal(num);
               break;
           }catch (IllegalArgumentException e){
               writeMessage("Данные введены не корректно , повторите ");
               continue;
           }
       }
       return op;
    }
}
