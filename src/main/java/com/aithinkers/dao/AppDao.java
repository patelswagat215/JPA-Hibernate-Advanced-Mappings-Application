package com.aithinkers.dao;

import com.aithinkers.entity.Instructor;
import com.aithinkers.entity.InstructorDetail;

public interface AppDao {
	
	void save(Instructor theInstructor);
	
	Instructor findInstructorById(Integer theId);
	
	void deleteInstructorById(Integer theId);
	
	InstructorDetail findInstructorDetailById(Integer theId);

}
