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
			 //createInstructor(theAppDao);
			//findInstructor(theAppDao);
			//deleteInstructor(theAppDao);
			findInstructorDetail(theAppDao);
		};

	}

	private void findInstructorDetail(AppDao theAppDao) {
		
		// get the instructor detail object
		int theId = 1;
		InstructorDetail tempInstructorDetail = theAppDao.findInstructorDetailById(theId);
		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);
		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
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
		Instructor tempInstructor = theAppDao.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorsDetail());

	}

	private void createInstructor(AppDao theAppDao) {
		Instructor tempInstructor = new Instructor("Swagat", "Patel", "patelswagat215@gmail.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("CodingWithPatel", "Java");
		
		tempInstructor.setInstructorsDetail(tempInstructorDetail);
		System.out.println("Saving instructor: " + tempInstructor);
		theAppDao.save(tempInstructor);

		System.out.println("Done!");
	}

}
