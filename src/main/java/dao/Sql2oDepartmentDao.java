package dao;

import models.Department;
import models.News;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private Sql2o sql2o ;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {

    }

    @Override
    public void addDepartmentToNews(Department department, News news) {

    }

    @Override
    public List<Department> getAll() {
        return null;
    }

    @Override
    public Department findById(int id) {
        return null;
    }

    @Override
    public List<News> getAllNewsByDepartment(int departmentId) {
        return null;
    }

    @Override
    public void update(int id, String name, String description, int numberOfEmployees) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
