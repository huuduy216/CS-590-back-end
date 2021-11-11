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
	@Table(name = "Problem")
	public class Problem implements Serializable{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "idProblem")
		private int idProblem;
		@Column(name = "code")
		private String code;
		
		@Column(name = "testcase")
		private String testcase;
		
		@Column(name = "idImplementation")
		private int idImplementation;
		
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		private Implementation implementation;
		
		@OneToMany (mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private Set<Benchmark> benchmark = new HashSet<>();
		
		public int getIdProblem() {
			return idProblem;
		}


		public void setIdProblem(int idProblem) {
			this.idProblem = idProblem;
		}
		
		public int getIdImplementation() {
			return idImplementation;
		}


		public void setIdImplementation(int idImplementation) {
			this.idImplementation = idImplementation;
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

		public Set<Benchmark> getBenchmark() {
			return benchmark;
		}


		public void setBenchmark(Set<Benchmark> benchmark) {
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
			return "Problem [idProblem=" + idProblem + ", code=" + code + ", testcase=" + testcase + ", benchmark=" + benchmark + "]";
		}
		
		


		
		
}
