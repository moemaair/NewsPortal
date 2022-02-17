package dao;

import models.Department;
import models.News;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //create
    void add(Department department);
    void addUserToDepartment(User user, Department department);

    //read
    List<Department> getAll();
    Department findById(int id);
    List<User> getAllUsersInDepartment(int department_id);
    List<News> getDepartmentNews(int id);
    //delete
    void clearAll();
}
