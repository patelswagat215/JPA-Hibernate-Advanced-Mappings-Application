package com.aithinkers.dao;

import java.util.List;

import com.aithinkers.entity.Course;
import com.aithinkers.entity.Instructor;
import com.aithinkers.entity.InstructorDetail;

public interface AppDao {
	
	//Uni-Directional
	void saveInstructorAndInstructorDetail(Instructor theInstructor);
	
	Instructor findInstructorById(Integer theId);
	
	void deleteInstructorById(Integer theId);
	
	//Bi-Directional
	InstructorDetail findInstructorDetailById(Integer theId);
	
	void deleteInstructorDetailById(Integer theId);
	
	List<Course> findCourseByInstructorId(Integer theId);
	
	Instructor findInstructorByIdJoinFetch(Integer theId);
	
	void update(Instructor theInstructor);
	
	void update(Course theCourse);
	
	Course findCourseById(Integer theId);
	
	void deleteCourseById(Integer id);
	
	//One To many uni-directional
	void save(Course theCourse);
	
	Course findCourseAndReviewsByCourseId(Integer theId);
	
	//------------h5
	Course findCourseAndStudentsByCourseId(Integer theId);


}
