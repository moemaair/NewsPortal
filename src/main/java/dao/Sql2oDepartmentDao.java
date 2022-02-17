package dao;

import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO departments (name, description, size) VALUES (:name, :description, :size)";
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addUserToDepartment(User user, Department department) {

        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO users_departments (user_id,department_id) VALUES (:user_id,:department_id)";
            con.createQuery(sql)
                    .addParameter("user_id", user.getId())
                    .addParameter("department_id", department.getId())
                    .executeUpdate();
            String sizeQuery = "SELECT user_id FROM users_departments";
            List<Integer> size = con.createQuery(sizeQuery)
                    .executeAndFetch(Integer.class);
            String updateDepartmentSize = "UPDATE departments SET size=:size WHERE id=:id";
            con.createQuery(updateDepartmentSize).addParameter("id", department.getId())
                    .addParameter("size", size.size())
                    .executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM departments";
            return con.createQuery(sql)
                    .executeAndFetch(Department.class);

        }
    }

    @Override
    public Department findById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM departments WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);

        }
    }

    @Override
    public List<User> getAllUsersInDepartment(int department_id) {

        List<User> users = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            String sql = "SELECT user_id FROM users_departments WHERE department_id=:department_id";
            List<Integer> userIds = con.createQuery(sql)
                    .addParameter("department_id", department_id)
                    .executeAndFetch(Integer.class);

            for (Integer id : userIds) {
                String userResults = "SELECT * FROM staff WHERE id=:id";
                users.add(con.createQuery(userResults)
                        .addParameter("id", id)
                        .executeAndFetchFirst(User.class));

            }

            return users;
        }

    }

    @Override
    public List<News> getDepartmentNews(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM news WHERE id=:id ";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void clearAll() {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM departments";
            String sqlUsersDepartments = "DELETE FROM users_departments";
            con.createQuery(sql).executeUpdate();
//            con.createQuery(sqlUsersDepartments).executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}