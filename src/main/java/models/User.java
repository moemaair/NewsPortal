package models;

public class User {
    private int id;
    private String name;
    private String role;
    private int departmentId; //connecting users to depart (1:M)R

    public User(String name, String role, int departmentId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
