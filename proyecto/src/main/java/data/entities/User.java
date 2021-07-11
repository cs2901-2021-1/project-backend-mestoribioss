/*- email: string
- name: string
- lastName:string
- status: int
- role : int */
package data.entities;

import javax.persistence.*;

import static config.GlobalConstants.DB_CHAR_LENGTH;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = DB_CHAR_LENGTH, nullable = false)
    private String name;

    @Column(name = "lastName", length = DB_CHAR_LENGTH, nullable = false)
    private String lastName;
    
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name="email",nullable = false,length = 512,unique=true )
    private String email;

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

   
    

}