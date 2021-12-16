package com.example.spacecode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import org.springframework.boot.test.context.SpringBootTest;

import com.example.spacecode.model.Benchmark;
public class BenchmarkTest {

	@Test
	void BenchmarkConstructorTest()
	{
		Benchmark b1 = new Benchmark("username","date","benchmark","cpu","r","g","l1","l2","l3","t","s","likes","star",1);
		assertEquals("username", b1.getUsername());
		assertEquals("date",b1.getDate());
		assertEquals("benchmark",b1.getBenchmarkType());
		assertEquals("cpu",b1.getCpu());
		assertEquals("r",b1.getRam());
		assertEquals("g",b1.getGpu());
		assertEquals("t",b1.getTime());
		assertEquals("s",b1.getSpace());
		assertEquals("likes",b1.getLikes());
		assertEquals("l1",b1.getL1());
		assertEquals("l2",b1.getL2());
		assertEquals("l3",b1.getL3());
		assertEquals("star",b1.getStars());
		assertEquals(1,b1.getId_Algorithm());
	}
	@Test
	void BenchmarkSetterTest()
	{
		Benchmark b1 = new Benchmark();
		b1.setUsername("username");
		b1.setDate("date");
		b1.setBenchmarkType("benchmark");
		b1.setCpu("cpu");
		b1.setRam("r");
		b1.setGpu("g");
		b1.setTime("t");
		b1.setSpace("S");
		b1.setLikes("l");
		b1.setL1("l1");
		b1.setL2("l2");
		b1.setL3("l3");
		b1.setStars("star");
		b1.setId_Algorithm(1);
		assertEquals("username", b1.getUsername());
		assertEquals("date",b1.getDate());
		assertEquals("benchmark",b1.getBenchmarkType());
		assertEquals("cpu",b1.getCpu());
		assertEquals("r",b1.getRam());
		assertEquals("g",b1.getGpu());
		assertEquals("t",b1.getTime());
		assertNotEquals("s",b1.getSpace());
		assertNotEquals("likes",b1.getLikes());
		assertEquals("l1",b1.getL1());
		assertEquals("l2",b1.getL2());
		assertEquals("l3",b1.getL3());
		assertEquals("star",b1.getStars());
		assertEquals(1,b1.getId_Algorithm());
	}
}
