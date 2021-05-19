package com.tranjt.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tranjt.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			displayStudent(theStudents);

			// query students: lastName="Doe"
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

			// display the students
			System.out.println("\nthe students how have last name of Doe");
			displayStudent(theStudents);

			// query students: lastName='Doe' OR firstName='Daffy'
			theStudents = 
					session.createQuery("from Student s where" 
					+ " s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

			// display the students
			System.out.println("\nQuery students: lastName='Doe' OR firstName='Daffy'");
			displayStudent(theStudents);
			
			// query students where email Like '%tranjt.com'
			theStudents = session.createQuery("from Student s where "
					+ "s.email LIKE '%tranjt.com'").getResultList();
			
			// display the students
			System.out.println("\nquery students where email Like '%tranjt.com'");
			displayStudent(theStudents);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudent(List<Student> theStudents) {
		for (Student tempstudent : theStudents) {
			System.out.println(tempstudent);
		}
	}

}
