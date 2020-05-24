package com.javarush.task.task26.task2613;

public enum Operation {
    INFO ,
    DEPOSIT,
    WITHDRAW,
    EXIT;
    public static Operation getAllowableOperationByOrdinal(Integer i)throws  IllegalArgumentException{
        Operation[] op =Operation.values();
        Operation operation =null;
          if (i<=op.length && i> 0){
              operation =op[i-1];
          }
          else {
              throw new  IllegalArgumentException();
          }


       return operation;
    }
}
