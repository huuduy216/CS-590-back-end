package com.example.spacecode.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = " Implementation")

public class Implementation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int implementationID;
	
	@Column(name = "title")
	private String title;
	@Column(name = "Identitykey")
	private String Identitykey;
	@Column(name = "language")
	private String language;
	
	public Implementation() {
	}
	
	public Implementation (String title, String language,String Identitykey)
	{
		this.title = title;
		this.language=language;
		this.Identitykey = Identitykey;
	}
	public int getImplementationID() {
		return implementationID;
	}

	public void setImplementationID(int implementationID) {
		this.implementationID = implementationID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setIdentitykey(String Identitykey){this.Identitykey = Identitykey;}

	public String getIdentitykey(){return Identitykey;}




}
