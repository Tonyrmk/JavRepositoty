package com.javarush.task.task36.task3608;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.FakeModel;
import com.javarush.task.task36.task3608.model.MainModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
      //  Model model = new FakeModel();
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView ed =new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        ed.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(ed);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126L);
        ed.fireEventUserDeleted(124L);
        ed.fireEventUserChanged("Майк" ,125 ,1);
        usersView.fireEventShowDeletedUsers();

    }
}