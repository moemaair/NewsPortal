package models;

import java.util.Objects;

public class Department {
    private int id;
    private String name;
    private String description;
    private int size;

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
        this.size = 0;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}