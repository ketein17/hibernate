package edu.fa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Fresher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Course> courses=new ArrayList<>();
    @ManyToMany
    private Set<Group> groups =new HashSet<>();



    public Fresher(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Fresher(){}
    public Fresher(String name) {
        this.name = name;
    }

    public Fresher(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Fresher(String name, Set<Group> groups) {
        this.name = name;
        this.groups = groups;
    }
}
