package com.aithinkers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aithinkers.dao.AppDao;
import com.aithinkers.entity.Instructor;
import com.aithinkers.entity.InstructorDetail;

@SpringBootApplication
public class SpringDataJpaAdvancedMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaAdvancedMappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao theAppDao) {
		return runner -> {
			// createInstructor(theAppDao);
			// findInstructor(theAppDao);
			// deleteInstructor(theAppDao);
			findInstructorDetail(theAppDao);
		};

	}

	private void findInstructorDetail(AppDao theAppDao) {

		int theId = 1;
		InstructorDetail instructorDetail = theAppDao.findInstructorDetailById(theId);
		//System.out.println("tempInstructorDetail: " + instructorDetail);
		//System.out.println("the associated instructor: " + instructorDetail.getInstructor());
		Instructor instructor = instructorDetail.getInstructor();
		System.out.println("Associated Instructor: " + instructor.getFirstName() + " " + instructor.getLastName());
		System.out.println("Done");

	}

	private void deleteInstructor(AppDao theAppDao) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		theAppDao.deleteInstructorById(theId);
		System.out.println("Deleted instructor");
	}

	private void findInstructor(AppDao theAppDao) {

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = theAppDao.findInstructorById(theId);
		System.out.println("Instructor: " + instructor);
		System.out.println("the associated instructorDetail only: " + instructor.getInstructorsDetail());

	}

	private void createInstructor(AppDao theAppDao) {
		Instructor instructor = new Instructor("Swagat", "Patel", "patelswagat215@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("CodingWithPatel", "Java");

		instructor.setInstructorsDetail(instructorDetail);
		System.out.println("Saving instructor: " + instructor);
		theAppDao.save(instructor);

		System.out.println("Done!");
	}

}
