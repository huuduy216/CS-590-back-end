package com.example.spacecode.model;
import javax.persistence.*;
import java.io.Serializable;
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
	@Column(name = "testcase")
	private String testcase;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Implementation implementation;
	
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

	public String gettestcase() {
		return testcase;
	}

	public void settestcase(String testcase) {
		this.testcase = testcase;
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
		return "Implementation [implementationID=" + implementationID + ", name=" + name + ", testcase=" + testcase
				+ "]";
	}
	

}
