package drc.project.toDoList.entitys;

public enum UserRole {

    ADMIN("gerente"),
    FACILITADOR("productowner"),
    USER("usuario");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
