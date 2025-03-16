package com.emtech.configuration_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grade_system")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int minScore;

    @Column(nullable = false)
    private int maxScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradingSystem gradingSystem;

    @Column(nullable = false)
    private int points;

    // Constructor for mapping DTOs
    public GradeSystem(Long id, String gradeSystem, int minScore, int maxScore, int points) {
        this.id = id;
        this.gradingSystem = GradingSystem.valueOf(gradeSystem); // Uses built-in method
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.points = points;
    }

    // Enum for grading system
    @Getter
    public enum GradingSystem {
        A(81, 84, "A", 12),
        A_MINUS(77, 80, "A-", 11),
        B_PLUS(73, 76, "B+", 10),
        B(69, 72, "B", 9),
        B_MINUS(65, 68, "B-", 8),
        C_PLUS(61, 64, "C+", 7),
        C(57, 60, "C", 6),
        C_MINUS(53, 56, "C-", 5),
        D_PLUS(49, 52, "D+", 4),
        D(41, 48, "D", 3),
        D_MINUS(30, 35, "D-", 2),
        E(0, 29, "E", 1);

        private final int minScore;
        private final int maxScore;
        private final String grade;
        private final int points;

        GradingSystem(int minScore, int maxScore, String grade, int points) {
            this.minScore = minScore;
            this.maxScore = maxScore;
            this.grade = grade;
            this.points = points;
        }

        // Method to get grade based on a score
        public static GradingSystem getGradeForScore(int score) {
            for (GradingSystem gradingSystem : GradingSystem.values()) {
                if (score >= gradingSystem.minScore && score <= gradingSystem.maxScore) {
                    return gradingSystem;
                }
            }
            return E; // Default to E if the score is below 30
        }
    }
}

