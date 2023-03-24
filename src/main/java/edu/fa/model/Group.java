package edu.fa.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany
    private Set<Fresher> freshers=new HashSet<>();

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, Set<Fresher> freshers) {
        this.name = name;
        this.freshers = freshers;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", freshers=" + freshers +
                '}';
    }
}
