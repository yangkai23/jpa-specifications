package com.clvt.springJpaSpecification.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "candidate_name",nullable = false)
    private String name;
    @Column(name="candidate_place")
    private String place;
    @Column(name="candidate_age")
    private int age;
    @OneToOne
    private Passport passport;
}
