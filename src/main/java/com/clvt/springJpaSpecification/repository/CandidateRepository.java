package com.clvt.springJpaSpecification.repository;

import com.clvt.springJpaSpecification.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long>, JpaSpecificationExecutor<Candidate> {
  List<Candidate> findAllByPassportNumberContaining(String number);
  List<Candidate> findAllByAgeBetween(int lower, int upper);
  List<Candidate> findByPlace(String place);

}
