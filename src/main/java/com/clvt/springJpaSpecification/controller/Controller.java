package com.clvt.springJpaSpecification.controller;

import com.clvt.springJpaSpecification.dto.ResponseDto;
import com.clvt.springJpaSpecification.model.Candidate;
import com.clvt.springJpaSpecification.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("specification")
public class Controller {
    @Autowired
    CandidateService service;


    @PostMapping("/savePassportAndCandidate")
    public ResponseDto savePassportAndCandidate(@RequestBody Candidate candidate) {
        return service.savePassportAndCandidate(candidate);
    }

    @GetMapping("/findCandidate")
    public ResponseDto findCandidateById(@RequestParam String id) {
        return service.findCandidateById(id);
    }

    @GetMapping("/findPassport")
    public ResponseDto findPassportById(@RequestParam String id) {
        return service.findPassportById(id);
    }

    @GetMapping("/findByPattern")
    public ResponseDto findByPattern(@RequestParam String pattern) {
        return service.findByPattern(pattern);
    }

    @GetMapping("/findByAgeRange")
    public ResponseDto findByAgeRange(@RequestParam String lower, @RequestParam String upper) {
        return service.findByAgeRange(lower, upper);
    }

    @GetMapping("/findByPlace")
    public ResponseDto findByPlace(@RequestParam String place) {
        return service.findByPlace(place);
    }

    @GetMapping("/findByPlaceUsingCriteria")
    public ResponseDto findByPlaceUsingCriteria(@RequestParam String place) {
        return service.findByPlaceUsingCriteria(place);
    }

    @GetMapping("/combinePredicates")
    public ResponseDto combinePredicates() {
        return service.combinePredicates();
    }
}
