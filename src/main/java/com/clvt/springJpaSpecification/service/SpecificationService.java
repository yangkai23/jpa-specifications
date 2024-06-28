package com.clvt.springJpaSpecification.service;

import com.clvt.springJpaSpecification.model.Candidate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {
    public Specification<Candidate> PlaceSpecification(String place) {
        return new Specification<>() {
            /*select * from Candidate
            CriteriaQuery<Candidate> query = cb.createQuery(Candidate.class);
            Root<Candidate> from = query.from(Candidate.class);
                cb.like(from.get("place"),"");
                query.where(cb.and(from.get("place").in(List.of("gujarat"))));
*/


            @Override
            public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Predicate predicate = cb.equal(root.get("place"), place);
                return predicate;
            }
        };
    }

    public Specification<Candidate> PlaceSpecificationLambda(String place) {
        return (root, cq, cb) -> cb.equal(root.get("place"), place);
    }
    public Specification<Candidate> likeSpecification(String field,String pattern) {
        return (root, cq, cb) -> cb.like(field.equals("passport.number")? root.get("passport").get("number").as(String.class):root.get(field).as(String.class), "%"+pattern+"%");
    }
    public Specification<Candidate> betweenSpecification(String field, int lower, int upper) {
        return (root, cq, cb) -> cb.between(root.get(field),lower,upper);
    }
    public Specification<Candidate> InSpecification(String field, List<Object> values) {
        return (root, cq, cb) -> root.get(field).in(values);
    }

}
