package org.javarush.oleksandr.courses;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        CourseHttpClientService courseService = new CourseHttpClientService("admin");

        Course newCourse = new Course(20L, "New course from client", 0L);

//        courseService.create(newCourse);
//
//        courseService.delete(15L);



//        Course course2 = courseService.getCourse(2);

//        System.out.println(course2);

        Course updatedCourse = new Course(20L, "Updated", 0L);
        courseService.update(updatedCourse);


        List<Course> allCourses = courseService.getAllCourses();

        allCourses.forEach(System.out::println);
    }
}
