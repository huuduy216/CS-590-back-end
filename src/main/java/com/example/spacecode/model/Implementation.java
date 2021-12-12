package com.example.spacecode.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = " Implementations")

public class Implementation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idImplementation")
	private int idImplementation;
	
	@Column(name = "languageName")
	private String languageName;
	@Column(name = "subtitle")
	private String subtitle;
	@Column(name = "description")
	private String description;
	@Column(name = "code")
	private String code;
	@Column(name = "id_Algorithm")
	private int id_Algorithm;

	public Implementation() {
	}

	public Implementation(String languageName, String subtitle, String description, String code, int id_Algorithm) {
		this.languageName = languageName;
		this.subtitle = subtitle;
		this.description = description;
		this.code = code;
		this.id_Algorithm = id_Algorithm;
	}

	public int getIdImplementation() {
		return idImplementation;
	}

	public void setIdImplementation(int idImplementation) {
		this.idImplementation = idImplementation;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId_Algorithm() {
		return id_Algorithm;
	}

	public void setId_Algorithm(int id_Algorithm) {
		this.id_Algorithm = id_Algorithm;
	}
}
