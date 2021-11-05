package com.example.spacecode.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRegisteredUser")
	private int idRegisteredUser;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password", length = 255)
	private String password;
	
	@Column(name = "isAdmin")
	private Boolean isAdmin;
	
	//This is not db field
	@Transient
	private Role role;
	
	public User() {
	}
	
	public User(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
		role = isAdmin == null || isAdmin ? new Role(Long.valueOf(1),"ROLE_ADMIN") :  new Role(Long.valueOf(1),"ROLE_NORMAL");
	}
	
	public User(String userName, String password, Boolean isAdmin)
	{
		this(userName, password);
		this.isAdmin = isAdmin;
		role = isAdmin == null || isAdmin ? new Role(Long.valueOf(1),"ROLE_ADMIN") :  new Role(Long.valueOf(1),"ROLE_NORMAL");
	}

	public int getIdRegisteredUser() {
		return idRegisteredUser;
	}

	public void setIdRegisteredUser(int idRegisteredUser) {
		this.idRegisteredUser = idRegisteredUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Role getRoles() { 
        return role; 
    } 
 
    public void setRole(Role role) { 
        this.role = role; 
    } 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return idRegisteredUser == other.idRegisteredUser;
				
	}
	@Override
    public int hashCode() {
        return Objects.hash(idRegisteredUser);
    }

	@Override
	public String toString() {
		return "User {idClassification=" + idRegisteredUser + ", userName=" + userName + "}";
	}
	 
    @Override 
    public boolean isAccountNonExpired() { 
        return true; 
    } 
 
    @Override 
    public boolean isAccountNonLocked() { 
        return true; 
    } 
 
    @Override 
    public boolean isCredentialsNonExpired() { 
        return true; 
    } 
 
    @Override 
    public boolean isEnabled() { 
        return true; 
    } 
 
    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        List<GrantedAuthority> authorities = new ArrayList<>(); 
        authorities.add( new SimpleGrantedAuthority(isAdmin ? "admin" : "user") ); 
        return authorities; 
    }

	@Override
	public String getUsername() {
		return userName;
	} 
}
