package com.example.spacecode.model;

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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class History {
	@Id	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "history")
	private String history;
	
	public History(String userName, String history)
	{
		this.userName = userName;
		this.history = history;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		History other = (History) obj;
		return userName == other.userName;
				
	}
	@Override
    public int hashCode() {
        return Objects.hash(userName);
    }

	@Override
	public String toString() {
		return "History {history=" + history + ", userName=" + userName + "}";
	}
}
