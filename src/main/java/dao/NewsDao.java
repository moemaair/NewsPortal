package dao;

import models.News;
import models.Department_News;

import java.util.List;

public interface NewsDao {
    //create

    void addNews(News news);
    void addDepartmentNews(Department_News department_news);

    //read
    List<News> getAll();

    News findById(int id);

    //update

    //delete

    void clearAll();
}
