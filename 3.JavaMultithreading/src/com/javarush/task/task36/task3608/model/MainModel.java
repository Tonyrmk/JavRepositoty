package com.javarush.task.task36.task3608.model;


import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model{
    private UserService userService=new UserServiceImpl();
    private ModelData modelData =new ModelData();
    @Override
    public ModelData getModelData() {
       return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        List<User> us =getAllUsers();
        getModelData().setUsers(us);


    }
    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> users = new ArrayList<>(userService.getAllDeletedUsers());
        getModelData().setUsers(users);

    }
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }
    public void deleteUserById(long id){

   getAllUsers().remove(userService.deleteUser(id));
  loadUsers();
    }
    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name,id,level);

        loadUsers();
    }



    private List<User> getAllUsers(){
       List<User> users = new ArrayList<>(userService.getUsersBetweenLevels(1,100));

        return  userService.filterOnlyActiveUsers(users);
   }


}
