package com.example.spacecode.model;

import java.io.Serializable;
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
	@Table(name = " Problem")
	public class Problem implements Serializable{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "idProblem")
		private int idProblem;
		@Column(name = "code")
		private String code;
		
		@Column(name = "testcase")
		private String testcase;
		
		@Column(name = "benchMarkId")
		private int benchMarkId;
		
		
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "benchMarkID", nullable = false)
		private Benchmark benchmark;
		
		
		public int getIdProblem() {
			return idProblem;
		}


		public void setIdProblem(int idProblem) {
			this.idProblem = idProblem;
		}


		public String getCode() {
			return code;
		}


		public void setCode(String code) {
			this.code = code;
		}


		public String getTestcase() {
			return testcase;
		}


		public void setTestcase(String testcase) {
			this.testcase = testcase;
		}


		public int getBenchMarkId() {
			return benchMarkId;
		}


		public void setBenchMarkId(int benchMarkId) {
			this.benchMarkId = benchMarkId;
		}


		public Benchmark getBenchmark() {
			return benchmark;
		}


		public void setBenchmark(Benchmark benchmark) {
			this.benchmark = benchmark;
		}


		@Override
		public int hashCode() {
			return Objects.hash(idProblem);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Problem other = (Problem) obj;
			return idProblem == other.idProblem;
		}


		@Override
		public String toString() {
			return "Problem [idProblem=" + idProblem + ", code=" + code + ", testcase=" + testcase + ", benchMarkId="
					+ benchMarkId + ", benchmark=" + benchmark + "]";
		}
		
		


		
		
}
