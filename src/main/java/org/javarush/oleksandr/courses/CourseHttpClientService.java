package org.javarush.oleksandr.courses;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class CourseHttpClientService {
    private static final String BASE_URL = "https://fq-backend.onrender.com";

    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    private static final Gson gson = new Gson();

    private String userName;

    public CourseHttpClientService(String userName) {
        this.userName = userName;
    }

    public Course getCourse(long id) throws IOException, InterruptedException {
        HttpRequest getCoursesRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/instructors/" + this.userName + "/courses/" + id))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(getCoursesRequest, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Course.class);
    }

    public List<Course> getAllCourses() throws IOException, InterruptedException {
        HttpRequest getCoursesRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/instructors/" + this.userName + "/courses"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(getCoursesRequest, HttpResponse.BodyHandlers.ofString());

        return Arrays.asList(gson.fromJson(response.body(), Course[].class));
    }


    public void create(Course course) throws IOException, InterruptedException {
        String newCourseJson = gson.toJson(course);


        HttpRequest createRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/instructors/" + this.userName + "/courses"))
                .POST(HttpRequest.BodyPublishers.ofString(newCourseJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<Void> response = httpClient.send(createRequest, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() != 201){
            throw new RuntimeException("Failed to create course");
        }
    }

    public void delete(long id) throws IOException, InterruptedException {
        HttpRequest createRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/instructors/" + this.userName + "/courses/" + id))
                .DELETE()
                .build();

        HttpResponse<Void> response = httpClient.send(createRequest, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() != 204){
            throw new RuntimeException("Failed to delete course");
        }
    }

    public void update(Course course) throws IOException, InterruptedException {
        String newCourseJson = gson.toJson(course);


        HttpRequest createRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/instructors/" + this.userName + "/courses/" + course.getId()))
                .PUT(HttpRequest.BodyPublishers.ofString(newCourseJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<Void> response = httpClient.send(createRequest, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() != 200){
            throw new RuntimeException("Failed to create course");
        }
    }

}
