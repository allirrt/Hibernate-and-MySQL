package com.irrt.hibernate.eager_vs_lazy.demo;
import com.irrt.hibernate.eager_vs_lazy.entity.Course;
import com.irrt.hibernate.eager_vs_lazy.entity.Instructor;
import com.irrt.hibernate.eager_vs_lazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class FetchJoinDemo {

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

            // option 2: Hibernate query with HQL

            // get the instructor from db
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "where i.id=:theInstructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Lomonosov Moscow State University: Instructor: " + tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nLomonosov Moscow State University: The session is now closed!\n");

            // get courses for the instructor
            System.out.println("Lomonosov Moscow State University: Courses: " + tempInstructor.getCourses());

            System.out.println("Lomonosov Moscow State University: Done!");
        }
        finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }

}


