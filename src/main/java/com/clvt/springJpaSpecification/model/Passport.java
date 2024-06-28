package com.clvt.springJpaSpecification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passport {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "passport_number",nullable = false)
    private String number;
    @OneToOne(mappedBy = "passport")
    @JsonIgnore
    private Candidate candidate;
}
