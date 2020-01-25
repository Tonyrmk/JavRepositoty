package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File file = new File(root);
        Queue<File> fileQueue =new PriorityQueue<>() ;
        List<String> absolutePathlist = new ArrayList<>();
        Collections.addAll(fileQueue,file.listFiles());
         while(!fileQueue.isEmpty()){
             File currentfile=fileQueue.remove();
             if (currentfile.isDirectory()){
                 Collections.addAll(fileQueue,currentfile.listFiles());}
             else {
                 absolutePathlist.add(currentfile.getAbsolutePath());
             }
             }


        return absolutePathlist;

    }

    public static void main(String[] args) throws Exception{
        ArrayList<String> list= new ArrayList<>(getFileTree("D:\\lesson"));
     for(String s: list){
         System.out.println(s);
     }
    }
}
