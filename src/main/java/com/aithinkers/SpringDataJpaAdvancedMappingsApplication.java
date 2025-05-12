package com.aithinkers;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aithinkers.dao.AppDao;
import com.aithinkers.entity.Course;
import com.aithinkers.entity.Instructor;
import com.aithinkers.entity.InstructorDetail;
import com.aithinkers.entity.Review;
import com.aithinkers.entity.Student;

@SpringBootApplication
public class SpringDataJpaAdvancedMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaAdvancedMappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao theAppDao) {
		return runner -> {
			// Uni-Directional Operation(From Instructor Only)

			//createInstructorAndInstructorDetail(theAppDao);
			// findInstructor(theAppDao);
			 deleteInstructor(theAppDao);

			// Bi-Directional Operation(From InstructorDetail also)

			// findInstructorDetail(theAppDao);
			// deleteInstructorDetailsById(theAppDao);

			// createInstructorithCourses(theAppDao);
			// findInstructorWithCourses(theAppDao);
			// findCourseForInstructor(theAppDao);
			// findInstructorWithCoursesJoinFetch(theAppDao);
			// updateInstructor(theAppDao);
			// updateCourse(theAppDao);
			// deleteInstructor(theAppDao);
			// deleteCourse(theAppDao);

			// ---------------------------------------
			// createCourseAndReviews(theAppDao);
			// retrieveCourseAndReview(theAppDao);
			// deleteCourseAndReviews(theAppDao);

			// -----------------------------------------
			//createCourseAndStudents(theAppDao);
			//findCourseAndStudent(theAppDao);(Getting Exception)
		};

	}

	private void findCourseAndStudent(AppDao theAppDao) {
		int theId = 10;
		Course tempCourse = theAppDao.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());
		System.out.println("Done!");
		
	}

	private void createCourseAndStudents(AppDao theAppDao) {
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");
		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		// save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());
		theAppDao.save(tempCourse);
		System.out.println("Done!");

	}

	private void deleteCourseAndReviews(AppDao theAppDao) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		theAppDao.deleteCourseById(theId);
		System.out.println("Done!");

	}

	private void retrieveCourseAndReview(AppDao theAppDao) {
		Integer theId = 10;
		Course theCourse = theAppDao.findCourseAndReviewsByCourseId(theId);
		System.out.println(theCourse);
		System.out.println(theCourse.getReviews());
		System.out.println("Done!");

	}

	private void createCourseAndReviews(AppDao theAppDao) {
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");
		// add some reviews
		tempCourse.addReview(new Review("Great course ... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course and leverage the cascade all...
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		theAppDao.save(tempCourse);
		System.out.println("Done!");

	}

	private void deleteCourse(AppDao theAppDao) {
		int theld = 10;
		System.out.println("Deleting course id: " + theld);
		theAppDao.deleteCourseById(theld);
		System.out.println("Done!");

	}

	private void updateCourse(AppDao theAppDao) {
		int theId = 10;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course course = theAppDao.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		course.setTitle("Enjoy the Simple Things");
		theAppDao.update(course);

		System.out.println("Done!");

	}

	private void updateInstructor(AppDao theAppDao) {
		int theId = 1;
		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = theAppDao.findInstructorById(theId);
		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");
		theAppDao.update(tempInstructor);
		System.out.println("Done!");

	}

	private void findInstructorWithCoursesJoinFetch(AppDao theAppDao) {
		int theId = 1;
		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = theAppDao.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
		System.out.println("Done!");

	}

	private void findCourseForInstructor(AppDao theAppDao) {
		Integer id = 1;
		/// Find Instructor
		Instructor instructor = theAppDao.findInstructorById(id);
		System.out.println(instructor);

		// Find Course for instructor
		System.out.println("Finding courses for instructor id:-" + id);

		List<Course> courses = theAppDao.findCourseByInstructorId(id);

		// Associate the objects
		instructor.setCourses(courses);

		System.out.println("the associated courses:-" + instructor.getCourses());

		System.out.println("Done");

	}

	private void findInstructorWithCourses(AppDao theAppDao) {
		Integer id = 1;
		Instructor instructor = theAppDao.findInstructorById(id);
		System.out.println(instructor);
		System.out.println("Done!" + instructor.getCourses());

	}

	private void createInstructorithCourses(AppDao theAppDao) {

		Instructor instructor = new Instructor("Swagat", "Patel", "patelswagat215@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("CodingWithPatel", "Java");

		instructor.setInstructorsDetail(instructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Spring Boot");
		Course tempCourse2 = new Course("Sql");

		// add courses to instructor
		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		// save the instructor
		//
		// NOTE: this will ALSO save the courses
		// because of CascadeType.PERSIST
		//
		// System.out.println("Saving instructor: " + instructor);
		// System.out.println("The courses: " + instructor.getCourses());
		theAppDao.saveInstructorAndInstructorDetail(instructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetailsById(AppDao theAppDao) {
		int theId = 2;
		System.out.println("Deleting instructorDetail id: " + theId);
		theAppDao.deleteInstructorDetailById(theId);
		System.out.println("Deleted instructorDetail");

	}

	private void findInstructorDetail(AppDao theAppDao) {

		int theId = 1;
		InstructorDetail instructorDetail = theAppDao.findInstructorDetailById(theId);
		// System.out.println("tempInstructorDetail: " + instructorDetail);
		// System.out.println("the associated instructor: " +
		// instructorDetail.getInstructor());
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

	private void createInstructorAndInstructorDetail(AppDao theAppDao) {
		Instructor instructor = new Instructor("Swagat", "Patel", "patelswagat215@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("CodingWithPatel", "Java");

		instructor.setInstructorsDetail(instructorDetail);
		System.out.println("Saving instructor: " + instructor);
		theAppDao.saveInstructorAndInstructorDetail(instructor);

		System.out.println("Done!");
	}

}
