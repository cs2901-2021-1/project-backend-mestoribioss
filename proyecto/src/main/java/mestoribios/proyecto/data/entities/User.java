/*- email: string
- name: string
- lastName:string
- status: int
- role : int */
package mestoribios.proyecto.data.entities;

import static mestoribios.proyecto.config.GlobalConstants.DB_CHAR_LENGTH;

import javax.persistence.*;

// import org.graalvm.compiler.lir.LIRInstruction.Use;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = DB_CHAR_LENGTH, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "lastName", length = DB_CHAR_LENGTH, nullable = false)
    private String lastName;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "role", nullable = false,length = DB_CHAR_LENGTH)
    private String role; //admin,user

    @Column(name="email",nullable = false,length = 512,unique=true )
    private String email;
    
    public User() {}

    public User(String email, String password,String rol,String name,String lastName) {
        this.name=name;
        this.lastName=lastName;
        this.email = email;
        this.password = password;
        this.status=1;
        this.role=rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

        
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


   
    

}