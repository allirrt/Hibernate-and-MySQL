package com.irrt.hibernate.eager_vs_lazy.demo;

import com.irrt.hibernate.eager_vs_lazy.entity.Instructor;
import com.irrt.hibernate.eager_vs_lazy.entity.InstructorDetail;
import com.irrt.hibernate.one_to_many.demo.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazy {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Lomonosov Moscow State University: Instructor: " + tempInstructor);

            System.out.println("Lomonosov Moscow State University: Courses: " + tempInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nLomonosov Moscow State University: The session is now closed!\n");

            // option 1: call getter method while session is open

            // get courses for the instructor
        //    System.out.println("Lomonosov Moscow State University: Courses: " + tempInstructor.getCourses());

            System.out.println("Lomonosov Moscow State University: Done!");
        }
        finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }

}

