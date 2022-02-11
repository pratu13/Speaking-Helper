package com.speechhelper.databasemanager;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class UserEntity{

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="userId")
  private Long userId;
  
  @Column(name="username")
  private String username;
  
  @Column(name="password")
  private String password;
  
  @Column(name="firstName")
  private String firstName;
  
  @Column(name="lastName")
  private String lastName;
  
  @Column(name="email")
  private String email;

  public UserEntity() {
	  
  }

  @Override
  public String toString() {
    return String.format(
        "User[userId=%d, username='%s', password='%s', firstName='%s', lastName='%s', email='%s']",
        userId, username, password, firstName, lastName, email);
  }

  public Long getUserId() {
    return userId;
  }

  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
	  this.firstName = firstName;
  }


	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

