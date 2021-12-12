package com.example.spacecode.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = " Benchmark")
public class Benchmark  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBenchmark")
    private int idBenchmark;

    @Column(name = "username")
    private String username;
    @Column(name = "date")
    private String date;
    @Column(name = "cpu")
    private String cpu;
    @Column(name = "ram")
    private String ram;
    @Column(name = "gpu")
    private String gpu;
    @Column(name = "L1")
    private String L1;
    @Column(name = "L2")
    private String L2;
    @Column(name = "L3")
    private String L3;
    @Column(name = "time")
    private String time;
    @Column(name = "space")
    private String space;
    @Column(name = "likes")
    private String likes;
    @Column(name = "stars")
    private String stars;
    @Column(name = "id_Algorithm")
    private int id_Algorithm;

    public Benchmark() {
    }

    public Benchmark(String username, String date, String cpu, String ram, String gpu, String l1, String l2, String l3, String time, String space, String likes, String stars, int id_Algorithm) {
        this.username = username;
        this.date = date;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        L1 = l1;
        L2 = l2;
        L3 = l3;
        this.time = time;
        this.space = space;
        this.likes = likes;
        this.stars = stars;
        this.id_Algorithm = id_Algorithm;
    }

    public int getIdBenchmark() {
        return idBenchmark;
    }

    public void setIdBenchmark(int idBenchmark) {
        this.idBenchmark = idBenchmark;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getL1() {
        return L1;
    }

    public void setL1(String l1) {
        L1 = l1;
    }

    public String getL2() {
        return L2;
    }

    public void setL2(String l2) {
        L2 = l2;
    }

    public String getL3() {
        return L3;
    }

    public void setL3(String l3) {
        L3 = l3;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getId_Algorithm() {
        return id_Algorithm;
    }

    public void setId_Algorithm(int id_Algorithm) {
        this.id_Algorithm = id_Algorithm;
    }
}
