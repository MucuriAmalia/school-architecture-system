package com.emtech.students_microservice.dto1;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradeSystemDTO {
    private Long id;
    @NotNull(message = "Minimum score cannot be null")
    private int minScore;

    @NotNull(message = "Maximum score cannot be null")
    private int maxScore;

    @NotBlank(message = "Grade cannot be null or blank")
    private String grade;

    @NotNull(message = "Points cannot be null")
    private int points;

    public GradeSystemDTO(Long id, String grade, int minScore, int maxScore, double points) {
        this.id = id;
        this.grade = grade;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.points = (int) points;

    }
}
