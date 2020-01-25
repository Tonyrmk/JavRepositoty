package com.javarush.task.task31.task3111;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
private String partOfName=null;
private String partOfContent=null;
private int minSize;
private int maxSize;
private List<Path> foundFiles=new ArrayList<>();
private boolean[] isChecked = new boolean[4];
    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;

    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;

    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;

    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file);// размер файла: content.length
        boolean have = true;
     if(partOfName!=null  ){
         have =file.getFileName().toString().contains(partOfName);
     }
     if (partOfContent!=null && have)
     { String containst =new String(Files.readAllBytes(file));
      have =containst.contains(partOfContent);
      }
     if (minSize!=0 && have  ){
        have =content.length>minSize;}


     if (maxSize!=0 && have ){
         have =content.length<maxSize;
     }
     if (have){
         foundFiles.add(file);
     }
        return super.visitFile(file, attrs);

    }

}
