package mestoribios.proyecto.data.dtos;

public class UserDTO {
    String name;
    String lastName;
    Integer status;
    String role;
    String email;
    
    public UserDTO(){
        //DEFAULT CONSTRUCTOR
    }

    public String getNameDTO() {
        return name;
    }

    public void setNameDTO(String name) {
        this.name = name;
    }

    public String getLastNameDTO() {
        return lastName;
    }

    public void setLastNameDTO(String lastName) {
        this.lastName = lastName;
    }

    public Integer getStatusDTO() {
        return status;
    }

    public void setStatusDTO(Integer status) {
        this.status = status;
    }

    public String getRoleDTO() {
        return role;
    }

    public void setRoleDTO(String role) {
        this.role = role;
    }

    public String getEmailDTO() {
        return email;
    }

    public void setEmailDTO(String email) {
        this.email = email;
    }
}