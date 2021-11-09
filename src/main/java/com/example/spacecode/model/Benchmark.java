package com.example.spacecode.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = " Benchmark")
public class Benchmark implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBenchMark")
	private int idBenchMark;
	
	@Column(name = "machineConfig")
	private String machineConfig;
	
	@Column(name = "score")
	private double score;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "idRegisteredUser")
	private int idRegisteredUser;
	
	
	
	@JoinColumn(name = "idRegisteredUser", nullable = false)
	private User user;

	
	public Benchmark() {
    }

    public Benchmark(String machineConfig, double score, Date date) {
        this.machineConfig = machineConfig;
        this.score = score;
        this.date = date;
    }

	@Override
	public int hashCode() {
		return Objects.hash(idBenchMark);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benchmark other = (Benchmark) obj;
		return idBenchMark == other.idBenchMark;
	}

	public int getIdBenchMark() {
		return idBenchMark;
	}

	public void setIdBenchMark(int idBenchMark) {
		this.idBenchMark = idBenchMark;
	}

	public String getMachineConfig() {
		return machineConfig;
	}

	public void setMachineConfig(String machineConfig) {
		this.machineConfig = machineConfig;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Benchmark [idBenchMark=" + idBenchMark + ", machineConfig=" + machineConfig + ", score=" + score
				+ ", date=" + date + ", idRegisteredUser=" + idRegisteredUser + ", user=" + user + "]";
	}

}
	
	