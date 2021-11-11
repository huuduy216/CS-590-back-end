package com.example.spacecode.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Implementation")
public class Implementation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "implementationID")
	private int implementationID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "idFamily")
	private int idFamily;
	
	@OneToMany(mappedBy = "implementation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Problem> problem = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Family family;
	
	public Implementation() {
	}
	
	public Implementation (String name)
	{
		this.name = name;
		
	}
	public int getImplementationID() {
		return implementationID;
	}

	public void setImplementationID(int implementationID) {
		this.implementationID = implementationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFamilyId() {
		return idFamily;
	}

	public void setFamilyId(int idFamily) {
		this.idFamily = idFamily;
	}
	
	public Set<Problem> getProblem() {
		return problem;
	}

	public void setProblem(Set<Problem> problem) {
		this.problem = problem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(implementationID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Implementation other = (Implementation) obj;
		return implementationID == other.implementationID;
	}

	@Override
	public String toString() {
		return "Implementation [implementationID=" + implementationID + ", name=" + name + ", problem=" + problem
				+ "]";
	}
}
