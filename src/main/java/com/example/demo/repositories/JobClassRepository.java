package com.example.demo.repositories;

import com.example.demo.model.worker_jobs.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class JobClassRepository {

    @Autowired
    EntityManager em;

    List<Job> finJobBydescription(String description) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);

        Root<Job> job = cq.from(Job.class);

        Predicate descriptionPredicate = cb.equal(job.get("description"), description);


        cq.where(descriptionPredicate);

        TypedQuery<Job> query = em.createQuery(cq);
        return query.getResultList();
    }
}
