package com.emtech.students_microservice.utils;

public class GradingSystemUtil {

    public static String calculateGrade(double score) {
        if (score >= 81 && score <= 84) {
            return "A (12 points)";
        } else if (score >= 77 && score <= 80) {
            return "A- (11 points)";
        } else if (score >= 73 && score <= 76) {
            return "B+ (10 points)";
        } else if (score >= 69 && score <= 72) {
            return "B (9 points)";
        } else if (score >= 65 && score <= 68) {
            return "B- (8 points)";
        } else if (score >= 61 && score <= 64) {
            return "C+ (7 points)";
        } else if (score >= 57 && score <= 60) {
            return "C (6 points)";
        } else if (score >= 53 && score <= 56) {
            return "C- (5 points)";
        } else if (score >= 49 && score <= 52) {
            return "D+ (4 points)";
        } else if (score >= 41 && score <= 48) {
            return "D (3 points)";
        } else if (score >= 30 && score <= 35) {
            return "D- (2 points)";
        } else {
            return "E (1 point)";
        }
    }

    public static String getRemarks(String grade) {
        return switch (grade) {
            case "A (12 points)" -> "Excellent";
            case "A- (11 points)" -> "Very Good";
            case "B+ (10 points)", "B (9 points)" -> "Good";
            case "B- (8 points)", "C+ (7 points)" -> "Fair";
            case "C (6 points)", "C- (5 points)" -> "Average";
            case "D+ (4 points)", "D (3 points)", "D- (2 points)" -> "Needs Improvement";
            case "E (1 point)" -> "Fail";
            default -> "Invalid Grade";
        };
    }
}
