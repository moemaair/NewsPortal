//public class App {
//    public static void main(String[] args) {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
////        Sql2o sql2o = new Sql2o(connectionString, "postgres", "bnmk");
//    }
//}
import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import Exception.ApiExceptions;
import models.Department;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

import static spark.Spark.staticFileLocation;

public class App {

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        staticFileLocation("/public");



        Gson gson = new Gson();
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "New Alias", "bnmk");

        Sql2oDepartmentDao sql2oDepartmentDao = new Sql2oDepartmentDao(sql2o);
        Sql2oNewsDao sql2oNewsDao= new Sql2oNewsDao(sql2o);
        Sql2oUserDao sql2oUsersDao= new Sql2oUserDao(sql2o);
        Connection conn =sql2o.open();
        get("/users", "application/json", (request, response) -> {

            if(sql2oDepartmentDao.getAll().size() > 0){
                return gson.toJson(sql2oUsersDao.getAll());
            }
            else {
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });
        get("/users", "application/json", (request, response) -> {

            if(sql2oDepartmentDao.getAll().size() > 0){
                return gson.toJson(sql2oUsersDao.getAll());
            }
            else {
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });

        get("/departments","application/json",(request, response) -> {
            if(sql2oDepartmentDao.getAll().size()>0){
                return gson.toJson(sql2oDepartmentDao.getAll());
            }
            else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }
        });
        get("/news/general","application/json",(request, response) -> {
            if(sql2oNewsDao.getAll().size()>0){
                return gson.toJson(sql2oNewsDao.getAll());
            }
            else {
                return "{\"message\":\"OOPs, looks like you don't have news to view yet.\"}";
            }
        });
        get("/user/:id/departments","application/json",(request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oUsersDao.getAllUserDepartments(id).size()>0){
                return gson.toJson(sql2oUsersDao.getAllUserDepartments(id));
            }
            else {
                return "{\"message\":\"OOPs, enroll the user to the department.\"}";
            }
        });
        get("/user/:id", "application/json", (request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oUsersDao.findById(id)==null){
                throw new ApiExceptions.ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            }
            else {
                return gson.toJson(sql2oUsersDao.findById(id));
            }
        });
        get("/department/:id/users","application/json",(request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentDao.getAllUsersInDepartment(id).size()>0){
                return gson.toJson(sql2oDepartmentDao.getAllUsersInDepartment(id));
            }
            else {
                return "{\"message\":\"OOPs , add users to this department.\"}";
            }
        });
        get("/department/:id","application/json",(request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentDao.findById(id)==null){
                throw new ApiExceptions.ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            else {
                return gson.toJson(sql2oDepartmentDao.findById(id));
            }
        });
        get("/news/department/:id","application/json",(request, response) -> {

            int id=Integer.parseInt(request.params("id"));
            Department departments=sql2oDepartmentDao.findById(id);
            if(departments==null){
                throw new ApiExceptions.ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            if(sql2oDepartmentDao.getDepartmentNews(id).size()>0){
                return gson.toJson(sql2oDepartmentDao.getDepartmentNews(id));
            }
            else {
                return "{\"message\":\"There are no news for this department.\"}";
            }
        });
        //create users
        post("/users/new","application/json",(request, response) -> {
            User user=gson.fromJson(request.body(),User.class);
            sql2oUsersDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        post("/departments/new","application/json",(request, response) -> {
            Department departments =gson.fromJson(request.body(),Department.class);
            sql2oDepartmentDao.add(departments);
            response.status(201);
            return gson.toJson(departments);
        });

        post("/news/new/general","application/json",(request, response) -> {

            News news =gson.fromJson(request.body(),News.class);
            sql2oNewsDao.addNews(news);
            response.status(201);
            return gson.toJson(news);
        });
        post("/news/new/department","application/json",(request, response) -> {
            News department_news =gson.fromJson(request.body(),News.class);
            Department departments=sql2oDepartmentDao.findById(department_news.getDepartment_id());
            User users=sql2oUsersDao.findById(department_news.getUser_id());
            if(departments==null){
                throw new ApiExceptions.ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            if(users==null){
                throw new ApiExceptions.ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            }
            sql2oNewsDao.addNews(department_news);
            response.status(201);
            return gson.toJson(department_news);
        });

        post("/news/new/general","application/json",(request, response) -> {

            News news =gson.fromJson(request.body(),News.class);
            sql2oNewsDao.addNews(news);
            response.status(201);
            return gson.toJson(news);
        });
        post("/add/user/:user_id/department/:department_id","application/json",(request, response) -> {

            int user_id=Integer.parseInt(request.params("user_id"));
            int department_id=Integer.parseInt(request.params("department_id"));
            Department departments=sql2oDepartmentDao.findById(department_id);
            User users=sql2oUsersDao.findById(user_id);
            if(departments==null){
                throw new ApiExceptions.ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("department_id")));
            }
            if(users==null){
                throw new ApiExceptions.ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("user_id")));
            }
            sql2oDepartmentDao.addUserToDepartment(users,departments);

            List<User> departmentUsers=sql2oDepartmentDao.getAllUsersInDepartment(departments.getId());

            response.status(201);
            return gson.toJson(departmentUsers);
        });
        exception(ApiExceptions.ApiException.class, (exception, request, response) -> {
            ApiExceptions.ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });


        after((request, response) ->{
            response.type("application/json");
        });


    }}