package com.aithinkers.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aithinkers.entity.Course;
import com.aithinkers.entity.Instructor;
import com.aithinkers.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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
	public void saveInstructorAndInstructorDetail(Instructor theInstructor) {

		entityManager.persist(theInstructor);
	}

	@Override
	public Instructor findInstructorById(Integer theId) {

		return entityManager.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorById(Integer theId) {
		//Retrieve Instructor
		Instructor instructor = entityManager.find(Instructor.class, theId);
		
		//Get the courses
		List<Course> courses=instructor.getCourses();
		
		//Break association of all coursesfor the instructor
		for(Course theCourse: courses )
		{
			theCourse.setInstructor(null);
		}
		
		//Delete the instructor
		entityManager.remove(instructor);

	}

	@Override
	public InstructorDetail findInstructorDetailById(Integer theId) {
		return entityManager.find(InstructorDetail.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(Integer theId) {
		InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

		// Remove the associated object refference
		// Break bi-directional link
		instructorDetail.getInstructor().setInstructorsDetail(null);

		// Delate the instructor detail
		entityManager.remove(instructorDetail);

	}

	@Override
	public List<Course> findCourseByInstructorId(Integer theId) {

		// create query
		TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
		query.setParameter("data", theId);

		// execute query
		List<Course> courses = query.getResultList();

		return courses;
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(Integer theId) {
	    try {
	        TypedQuery<Instructor> query = entityManager.createQuery(
	            "SELECT i FROM Instructor i " +
	            "JOIN FETCH i.courses " +
	            "JOIN FETCH i.instructorDetail "+
	            "WHERE i.id = :data", Instructor.class);
	        
	        query.setParameter("data", theId);

	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // or throw a custom exception if preferred
	    }
	}

	@Override
	@Transactional
	public void update(Instructor theInstructor) {
		entityManager.merge(theInstructor);
		
	}

	@Override
	@Transactional
	public void update(Course theCourse) {
		entityManager.merge(theCourse);
	}

	@Override
	public Course findCourseById(Integer theId) {
		return entityManager.find(Course.class, theId);
	}

	@Override
	@Transactional
	public void deleteCourseById(Integer theId) {
		// retrieve the course
		Course tempCourse = entityManager.find(Course.class, theId);
		// delete the course
		entityManager.remove(tempCourse);
		}

	@Override
	@Transactional
	public void save(Course theCourse) {
		entityManager.persist(theCourse);
		
	}

	@Override
	public Course findCourseAndReviewsByCourseId(Integer theId) {
		
		// create query
		TypedQuery<Course> query = entityManager.createQuery(
		    "SELECT c FROM Course c " +
		    "JOIN FETCH c.reviews " +
		    "WHERE c.id = :data", Course.class);

		
		query.setParameter( "data", theId);
		
		// execute query
		Course course = query.getSingleResult();
		return course;
	}

	@Override
	public Course findCourseAndStudentsByCourseId(Integer theId) {
		
		// create query
		TypedQuery<Course> query = entityManager.createQuery(
		        "SELECT c FROM Course c " +
		        "JOIN FETCH c.students " +
		        "WHERE c.id = :data", Course.class);
		query.setParameter("data", theId);
		// execute query
		Course course = query.getSingleResult();
		return course;
	}
		
	}

