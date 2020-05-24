package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode ;
    private Map<Integer,Integer> denominations =new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getTotalAmount(){
        int count =0;
        for(Map.Entry<Integer,Integer> entry :denominations.entrySet()){
            count+=entry.getKey()*entry.getValue();
        }
        return count;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public void addAmount(int denomination ,int count ) {
        if (denominations.containsKey(denomination)){
            denominations.put(denomination,denominations.get(denomination)+count);
        }
        else {
            denominations.put(denomination,count);
        }
    }
    public boolean hasMoney(){
        return denominations.size()!=0;
    }
   public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount()>=expectedAmount;
   }
   public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
       int sum = expectedAmount;
       Map<Integer, Integer> temp = new LinkedHashMap<>(denominations);


       Map<Integer, Integer> result = new HashMap<>();


       while(temp.size()!=0){
           List<Integer> list = new ArrayList<>(temp.keySet());
           Collections.sort(list);
           Collections.reverse(list);

           for (Integer aList : list) {
               int key = aList;
               int value = temp.get(key);
               while (true) {

                   if (sum < key || value <= 0) {
                       //temp.put(key, value);
                       break;
                   }

                   sum -= key;
                   value--;

                   if (result.containsKey(key))
                       result.put(key, result.get(key) + 1);
                   else
                       result.put(key, 1);


               }
           }
           if (sum > 0){
               Iterator<Map.Entry<Integer,Integer>> iterator =temp.entrySet().iterator();
               while(iterator.hasNext()){
                   if (iterator.next().getValue()<=0){
                       iterator.remove();
                   }
               }
               Integer key=0 ;
               if(temp.size()!=0){
                   for (Map.Entry<Integer,Integer> pair : temp.entrySet()) {
                       if (pair.getKey()>key) {
                           key=pair.getKey();
                       }}

                   temp.put(key,temp.get(key)-1);}
               result.clear();
               sum=expectedAmount;

           }
           else if (sum ==0){
               Integer key ;
               Integer value;
               for(Map.Entry<Integer,Integer> entry : result.entrySet()){
                   key=entry.getKey();
                   value =entry.getValue();
                   denominations.put(key,denominations.get(key)-value);
               }
               break;
           }
       }

       if (sum>0){
           throw new NotEnoughMoneyException();
       }






       return result;
   }
}
