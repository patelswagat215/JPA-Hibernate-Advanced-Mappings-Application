package com.aithinkers.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aithinkers.entity.Instructor;
import com.aithinkers.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImpl implements AppDao {

	private EntityManager entityManager;

	@Autowired
	public AppDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Instructor theInstructor) {

		entityManager.persist(theInstructor);
	}

	@Override
	public Instructor findInstructorById(Integer theId) {

		return entityManager.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorById(Integer theId) {
		Instructor instructor = entityManager.find(Instructor.class, theId);
		entityManager.remove(instructor);

	}

	@Override
	public InstructorDetail findInstructorDetailById(Integer theId) {
		return entityManager.find(InstructorDetail.class,theId);
	}

}
