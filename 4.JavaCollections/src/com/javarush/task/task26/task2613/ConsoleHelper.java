package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res= ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");
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

        String code =null;
       while(true) {
           writeMessage(res.getString("choose.currency.code"));
            code =readString();
            if (code.length()!=3){
                writeMessage(res.getString("invalid.data"));}

            else {
                code =code.toUpperCase();
                break;
            }
       }
        return code;
    }

    public static String[] getValidTwoDigits(String currencyCode)throws InterruptOperationException{
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
        String[] curVal=null;
        while (true){
            String s =readString();
            curVal=s.split(" ");
            if (curVal.length!=2){
                writeMessage(res.getString("invalid.data"));
                continue;
            }
           try{ if(Integer.parseInt(curVal[0])>=0 && Integer.parseInt(curVal[1])>=0){
            break;}
             else {
                 writeMessage(res.getString("invalid.data"));
                 continue;
           }
           }catch ( NumberFormatException e ){
               writeMessage(res.getString("invalid.data"));
               continue;
            }
        }
        return curVal;
    }
    public static Operation askOperation()throws InterruptOperationException{

        Operation op;
       while(true) {


           try{
               writeMessage(res.getString("choose.operation"));
               writeMessage(res.getString("operation.INFO") + " - 1");
               writeMessage(res.getString("operation.DEPOSIT") + " - 2");
               writeMessage(res.getString("operation.WITHDRAW") + " - 3");
               writeMessage(res.getString("operation.EXIT") + " - 4");
               int num = Integer.parseInt(readString());

               op =Operation.getAllowableOperationByOrdinal(num);
               break;
           }catch (IllegalArgumentException e){
               writeMessage(res.getString("invalid.data"));
               continue;
           }
       }
       return op;
    }
}
