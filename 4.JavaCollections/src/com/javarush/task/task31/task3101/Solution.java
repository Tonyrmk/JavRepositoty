package com.javarush.task.task31.task3101;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {


    public static void main(String[] args)
    {
    File path = new File(args[0]);
    File resultFileAbsolutePath = new File(args[1]);
    String newName=resultFileAbsolutePath.getParent()+"\\allFilesContent.txt";
    File newFile = new File (newName);
 ArrayList<File> sortedFile =new ArrayList<>();

if (FileUtils.isExist(resultFileAbsolutePath)==true){
        FileUtils.renameFile(resultFileAbsolutePath,newFile);}
       try(FileOutputStream fileOutputStream =new FileOutputStream(newFile,true);){

        recheckFilePatc(path,sortedFile);
        Collections.sort(sortedFile);

        for (int i=0;i<sortedFile.size();i++){
            String file =sortedFile.get(i).getAbsolutePath();
            try(FileInputStream inputStream =new FileInputStream(file);)
            {
                int j;

                while((j = inputStream.read())!= -1){

                    fileOutputStream.write(j);
                }fileOutputStream.write(10);
               // fileOutputStream.write(110);
            }catch (IOException e){}
        }}catch (IOException e){}
}
public static void recheckFilePatc(File patch ,ArrayList<File> sortarray){
    if (patch.isDirectory()){
        for (File file : patch.listFiles())
    {
        if (!file.isDirectory()&& file.length()<=50){
            sortarray.add(file);
        }
        else {
            recheckFilePatc(file,sortarray);
        }
    }
}}

}





