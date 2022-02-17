package models;

import java.util.Objects;

public class User {
    private  int id;
    private String name;
    private String position;
    private String role;

    public User(String name, String position, String role) {
        this.name = name;
        this.position = position;
        this.role = role;
    }

    public String getName() {
        return  name;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(position, user.position) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, role);
    }
}