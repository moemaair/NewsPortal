package dao;

import models.Department;
import models.User;

import java.util.List;

public interface UserDao {

    //create

    void add(User user);

    //read

    List<User> getAll();

    List<Department> getAllUserDepartments(int user_id);

    User findById(int id);

    //update

    //delete

    void clearAll();

}
