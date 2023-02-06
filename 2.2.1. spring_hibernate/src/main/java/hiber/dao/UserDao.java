package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    default List<User> getUserToAvto(String model, int series){
        return null;
    }
}
