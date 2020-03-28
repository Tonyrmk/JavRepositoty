package com.javarush.task.task37.task3711;

public class Computer {
    private CPU cpu =new CPU();
    private Memory menory =new Memory();
    private HardDrive hardDrive =new HardDrive();

    public void run(){
        cpu.calculate();
        menory.allocate();
        hardDrive.storeData();

    }
}
