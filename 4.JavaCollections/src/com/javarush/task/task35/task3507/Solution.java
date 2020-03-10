package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
Реализуй логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals.toString());
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws Exception {
       Set<Animal> animalSet = new HashSet<>();
        File[] list = new File(pathToAnimals).listFiles();


        for (File f:list){

           boolean paramFind = false;
           String packageName = Solution.class.getPackage().getName() + ".data";
           Class clazz =new LoadClass().findClass(f.getAbsolutePath());
           Class[] interfaces= clazz.getInterfaces();
           for(Class s:interfaces){
               if(s.getSimpleName().equals("Animal")){
                   paramFind =true;
                   break;
               }
                      }
            Constructor[] constr = clazz.getConstructors();
                       for(Constructor con:constr){
                if( Modifier.isPublic(con.getModifiers()) && con.getParameterTypes().length==0 ) {
                    paramFind = true;
                    break;
                }
                else paramFind =false;
                           }
            if (paramFind ==true){
               try{ animalSet.add((Animal)clazz.newInstance());}
               catch (Exception e){}
            }
        }


        return animalSet;
    }
    public static class LoadClass extends ClassLoader{
@Override
        public  Class<?> findClass(String name) throws ClassNotFoundException {

            try{
                Path file = Paths.get(name);
                byte[] bytes = Files.readAllBytes(file);
                return defineClass(null, bytes, 0, bytes.length);
               }
            catch( Exception e){}
                return super.findClass(name);
        }
    }
}
