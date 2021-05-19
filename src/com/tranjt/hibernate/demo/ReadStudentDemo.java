package com.tranjt.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tranjt.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
			// create a student object
			System.out.println("Creating a new student object...");
			Student temStudent = new Student("Daffy", "Wall", "Daffy@tranjt.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(temStudent);
			session.save(temStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// My new code
			
			// find out the student's id: primary key
			System.out.println("saved student, generated id: " + temStudent.getId());
			
			// now get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " +  temStudent.getId());
			Student myStudent = session.get(Student.class,  temStudent.getId());
			System.out.println("Get completed: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
