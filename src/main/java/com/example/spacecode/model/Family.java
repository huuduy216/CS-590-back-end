package com.example.spacecode.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Family")
public class Family implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfamily")
	private int idfamily;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "idClassification")
	private int idClassification;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Classification classification;
	
	@OneToMany (mappedBy = "implementation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Implementation> implementation = new HashSet<Implementation>();
	
	public Family() {
    }

    public Family(String name) {
        this.name=name;
    }

	public int getIdfamily() {
		return idfamily;
	}

	public void setIdfamily(int idfamily) {
		this.idfamily = idfamily;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idfamily);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		return idfamily == other.idfamily;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public Set<Implementation> getImplementation() {
		return implementation;
	}

	public void setImplementation(Set<Implementation> implementation) {
		this.implementation = implementation;
	}

	@Override
	public String toString() {
		return "Family [idfamily=" + idfamily + ", name=" + name + ", idClassification=" + idClassification
				+ ", classification=" + classification + ", implementation=" + implementation + "]";
	}
	
}