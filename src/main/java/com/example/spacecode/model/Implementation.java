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
	
	@Column(name = "name")
	private String name;
	@Column(name = "testcase")
	private String testcase;
	
	public Implementation() {
	}
	
	public Implementation (String name, String testcase)
	{
		this.name = name;
		this.testcase=testcase;
		
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

	public String getBestcase() {
		return testcase;
	}

	public void setBestcase(String bestcase) {
		this.testcase = bestcase;
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


}
