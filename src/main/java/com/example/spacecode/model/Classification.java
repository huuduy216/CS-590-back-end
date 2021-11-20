package com.example.spacecode.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Classification")
public class Classification implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idClassificaiton")
	private int idClassification;
	
	@Column(name = "name")
	private String name;

	@Column(name = "subtitle")
	private String subtitle;

	@Column(name = "Identitykey")
	private String Identitykey;

	@Column(name = "desription")
	private String desription;


	public Classification() {
	}

	public Classification(String name, String Identitykey, String desription,String subtitle) {
		this.name = name;
		this.Identitykey = Identitykey;
		this.desription = desription;
		this.subtitle = subtitle;
	}

	public int getIdClassification() {
		return idClassification;
	}

	public void setIdClassification(int idClassification) {
		this.idClassification = idClassification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentitykey() {
		return Identitykey;
	}

	public void setIdentitykey(String Identitykey) {
		this.Identitykey = Identitykey;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
}
