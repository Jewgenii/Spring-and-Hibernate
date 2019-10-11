package com.example.demo.repositories;

import com.example.demo.model.worker_jobs.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import static org.springframework.data.jpa.domain.Specification.*;

@Repository
public class JobClassRepository {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    EntityManager em;

    List<Job> finJobBydescription(String description) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);

        Root<Job> job = cq.from(Job.class);

        Predicate descriptionPredicate = cb.equal(job.get("description"), description);

        cq.where(descriptionPredicate);

        return em.createQuery(cq).getResultList();
    }

    public static Specification<Job> getJobs(String description){
        return (book, cq, cb) -> cb.like(book.get("description"), "%" + description + "%");
    }

    public static Specification<Job> creationTimeStamp(Date date){
        return (book, cq, cb) -> cb.lessThan(book.get("creattiontime"), date);
    }

    public List<Job> example(){
        Specification<Job> jobSpecification =
                where(getJobs("nothing")
                .or(getJobs("something"))
                .and(not(creationTimeStamp(new Date())))
                );

        return jobRepository.findAll(jobSpecification);
    }

}


