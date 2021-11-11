package com.example.spacecode.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
	
	@OneToMany (mappedBy = "classification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Family> family = new HashSet<Family>();
		
//	@Transient
//	private List<Family> family;
	
	public Classification() {
	}
	
	public Classification(String name)
	{
		this.name = name;
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
	
	public void setFamily(Set<Family> family) {
		this.family = family;
	}
	
	public Set<Family> getFamily() {
		return family;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classification other = (Classification) obj;
		return idClassification == other.idClassification;
				
	}
	@Override
    public int hashCode() {
        return Objects.hash(idClassification);
    }

	@Override
	public String toString() {
		return "Classification {idClassification=" + idClassification + ", name=" + name + " family:" + family + " }";
	}
}
