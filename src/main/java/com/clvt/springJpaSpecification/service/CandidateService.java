package com.clvt.springJpaSpecification.service;

import com.clvt.springJpaSpecification.dto.ResponseDto;
import com.clvt.springJpaSpecification.model.Candidate;
import com.clvt.springJpaSpecification.model.Passport;
import com.clvt.springJpaSpecification.repository.CandidateRepository;
import com.clvt.springJpaSpecification.repository.PassportRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    SpecificationService specificationService;


    @Transactional
    public ResponseDto savePassportAndCandidate(Candidate candidate) {
        Passport passport = candidate.getPassport();
        passportRepository.save(passport);
        candidateRepository.save(candidate);

        return new ResponseDto(passport, HttpStatus.OK.value(), "Candidate Saved Successfully");
    }
    public ResponseDto findCandidateById(String id) {
        Optional<Candidate> candidate = candidateRepository.findById(Long.parseLong(id));
        return new ResponseDto(candidate,HttpStatus.OK.value(), "Candidate Object Retrieved Successfully");
    }

    public ResponseDto findPassportById(String id) {
        Optional<Passport> passport = passportRepository.findById(Long.parseLong(id));
//        logger.info("Candidate : {}",passport.get().getCandidate());
        return new ResponseDto(passport.get(),HttpStatus.OK.value(), "passport Object Retrieved Successfully");
    }

    public ResponseDto findByPattern(String pattern) {
        List<Candidate> candidates = candidateRepository.findAllByPassportNumberContaining(pattern);
        return new ResponseDto(candidates,HttpStatus.OK.value(), "Candidates with pattern"+ pattern+" Retrieved Successfully");
    }
    public ResponseDto findByAgeRange(String lower, String upper) {
        List<Candidate> candidates = candidateRepository.findAllByAgeBetween(Integer.parseInt(lower),Integer.parseInt(upper));
        return new ResponseDto(candidates,HttpStatus.OK.value(), "Candidates Retrieved Successfully");
    }
    public ResponseDto findByPlace(String place) {
        List<Candidate> candidates = candidateRepository.findByPlace(place);
        return new ResponseDto(candidates,HttpStatus.OK.value(), "Candidates Retrieved Successfully");
    }

    public ResponseDto findByPlaceUsingCriteria(String place) {
        Specification<Candidate> placeSpec = specificationService.PlaceSpecification(place);
        List<Candidate> candidates = candidateRepository.findAll(placeSpec);
        return new ResponseDto(candidates,HttpStatus.OK.value(), "Candidates Retrieved Successfully");
    }
    public ResponseDto combinePredicates() {
        String[] places={"Gujarat","nellore"};
        Specification<Candidate> placeSpec = specificationService.InSpecification("place", Arrays.asList(places));
        Specification<Candidate> ageSpec = specificationService.betweenSpecification("age", 23, 24);
        Specification<Candidate> passportNumberSpec = specificationService.likeSpecification("passport", "%"+15+"%");
        Specification<Candidate> spec = Specification.where(placeSpec).and(ageSpec).or(passportNumberSpec);
        List<Candidate> candidates =candidateRepository.findAll(spec);
        return new ResponseDto(candidates,HttpStatus.OK.value(), "Candidates Retrieved Successfully after predicates Combination");
    }

}
