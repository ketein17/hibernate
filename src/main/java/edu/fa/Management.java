package edu.fa;

import edu.fa.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;

public class Management {
    public static void main(String[] args) {
/*        createCourseSyllabus();
        getCourseSyllabus(1);*/
        //createFresher();
        //createFresherAndCourse();
        //createFresherAndGroup();
        createGroup();
//        getGroup();
        //queryGroupUsingHQL();
        updateGroupUsingHQL();
        ConnectionUtil.getSessionFactory().close();
    }

    private static void updateGroupUsingHQL(){
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String queryString= "UPDATE Group SET name = :name WHERE id = :id";
            Query query=session.createQuery(queryString);
            query.setParameter("id",1);
            query.setParameter("name","Java Script Update");
            int result=query.executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
            /*List<Group> list=query.list();
            System.out.println(list);*/
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    private static void queryGroupUsingHQL(){
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
//            String queryString= "FROM Group WHERE id = ?";
//            Query query=session.createQuery(queryString);
//            query.setInteger(0,1);
            String queryString= "SELECT name FROM Group WHERE id = :id AND name LIKE :name";
            Query query=session.createQuery(queryString);
            query.setParameter("id",1);
            query.setParameter("name","Java%");
            List<Group> list=query.list();
            System.out.println(list);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void getGroup(){
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Group javaGroup=(Group)session.get(Group.class,2);
            System.out.println(javaGroup);
            javaGroup.setName("New Java Group");
            session.update(javaGroup);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void createGroup(){
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Group javaGroup=new Group("Java Group");
            Group jsGroup = new Group("JavaScript Group");
            session.save(jsGroup);
            session.save(javaGroup);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void createFresherAndGroup(){
        Fresher fresher1 = new Fresher();
        Fresher fresher2=new Fresher();
        Group group1=new Group();
        Group group2=new Group();
        Set<Fresher> freshers=new HashSet<>();
        freshers.add(fresher1);
        freshers.add(fresher2);
        Set<Group> groups=new HashSet<>();
        groups.add(group1);
        groups.add(group2);
        fresher1.setName("Fresher 1");
        fresher2.setName("Fresher 2");
        group1.setName("Group 1");
        group2.setName("Group 2");
        fresher1.setGroups(groups);
        fresher2.setGroups(groups);
        group1.setFreshers(freshers);
        group2.setFreshers(freshers);
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(fresher1);
            session.save(fresher2);
            session.save(group1);
            session.save(group2);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    private static void createFresherAndCourse(){

        List<Course> courses=new ArrayList<>();
        courses.add(new Course("Java"));
        courses.add(new Course("Hibernate"));

        Fresher fresher = new Fresher("Ducky",courses);
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Course course:courses){
                session.save(course);
            }
            session.save(fresher);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    private static void createFresherAndAddress(){
        Address address=new Address("Ha Noi","Hoang Cau");
        Fresher fresher = new Fresher("Ducky",address);
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(fresher);
            session.save(address);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void getCourseSyllabus(int id){
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course= (Course) session.get(Course.class,id );
            System.out.println(course.getName());
            session.getTransaction().commit();
            session.close();
            System.out.println(course.getSyllabuses());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void createCourseSyllabus() {
        List<Syllabus> syllabusList = new ArrayList<>();
        Syllabus syllabus=new Syllabus("Hibernate Online Content",30);
        syllabusList.add(syllabus);
        Syllabus offlineSyllabus=new Syllabus("Hibernate Offline Content ",50);
        syllabusList.add(offlineSyllabus);

        Course course = new Course("Hibernate aaa", new Date(),syllabusList);
        SessionFactory sessionFactory=ConnectionUtil.getSessionFactory();
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
