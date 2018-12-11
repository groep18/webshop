package ui.view;

import domain.model.Person;
import domain.model.Role;
import domain.model.User;

public class main {
    public static void main(String[] args) {
        User u = new User("1", "haha", "not too much", Role.ADMINISTRATOR, "hamidkhodayari@gmail.com", "hmd", "kh");
        System.out.println(u.getRole().toString());

    }

}
