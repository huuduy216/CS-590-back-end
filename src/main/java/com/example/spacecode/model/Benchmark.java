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
	
	@Column(name = "runTime")
	private double runTime;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "idRegisteredUser")
	private int idRegisteredUser;
	
	@Column(name = "idProblem")
	private int idProblem;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Problem problem;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User user;

	
	public Benchmark() {
    }

    public Benchmark(String machineConfig, double score, Date date) {
        this.machineConfig = machineConfig;
        this.runTime = score;
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
	
	public int getProblemId() {
		return idProblem;
	}

	public void setProblemId(int idProblem) {
		this.idProblem = idProblem;
	}
	
	public int getUserId() {
		return idRegisteredUser;
	}

	public void setUserId(int userId) {
		idRegisteredUser = userId;
	}

	public String getMachineConfig() {
		return machineConfig;
	}

	public void setMachineConfig(String machineConfig) {
		this.machineConfig = machineConfig;
	}

	public double getRunTime() {
		return runTime;
	}

	public void setRunTime(double runTime) {
		this.runTime = runTime;
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
		return "Benchmark [idBenchMark=" + idBenchMark + ", machineConfig=" + machineConfig + ", runTime=" + runTime
				+ ", date=" + date + ", idRegisteredUser=" + idRegisteredUser + ", user=" + user + "]";
	}

}
	
	