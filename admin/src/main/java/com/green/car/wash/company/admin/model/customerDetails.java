package com.green.car.wash.company.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.green.car.wash.company.admin.model.Role;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class customerDetails {

    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String email;
    private String password;
    //private String token;
    private String fullname;
    //private boolean enabled;

    //Dbref facilitates the input of roles by using only ID
   // @DBRef
    //private Set<Role> roles= new HashSet<>();

    //Getter and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    /*}
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

    //Default Constructor
    public customerDetails() {
		super();
	}

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
               
                '}';
    }
}