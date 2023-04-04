package org.javarush.oleksandr.courses;

public class Course {
    private Long id;
    private String description;
    private Long targetDate;

    public Course(Long id, String description, Long targetDate) {
        this.id = id;
        this.description = description;
        this.targetDate = targetDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Long targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                '}';
    }
}
