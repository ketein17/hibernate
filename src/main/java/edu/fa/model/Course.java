package edu.fa.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Course_name", unique = true)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Syllabus> syllabuses =new ArrayList<>();


    public Course(String name){
        this.name=name;
    }

    public Course(){}

    public Course(String name, Date createdDate) {
        this.name = name;
        this.createdDate = createdDate;
    }


    public Course(String name, Date createdDate, List<Syllabus> syllabuses) {
        this.name = name;
        this.createdDate = createdDate;
        this.syllabuses = syllabuses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public List<Syllabus> getSyllabuses() {
        return syllabuses;
    }

    public void setSyllabuses(List<Syllabus> syllabuses) {
        this.syllabuses = syllabuses;
    }

    @Override
    public String toString(){
        return name;
    }
}
