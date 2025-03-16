package com.emtech.students_microservice.utils;

import com.emtech.students_microservice.model.Student;
import java.util.Map;

public class ReportGeneratorUtil {

    public static String generateStudentReport(Student student, Map<String, Double> subjectScores) {
        StringBuilder report = new StringBuilder();
        report.append("📌 **Student Performance Report**\n");
        report.append("===================================\n");
        report.append("🎓 Student Name: ").append(student.getStudentName()).append("\n");
        report.append("🎫 Admission Number: ").append(student.getAdmissionNumber()).append("\n");
        report.append("🏡 Parent Name: ").append(student.getParentDetails().getFatherName()).append("\n");
        report.append("📅 Enrollment Date: ").append(student.getEnrollmentDate()).append("\n\n");

        double totalPoints = 0;
        int subjectCount = subjectScores.size();

        report.append("📊 **Subject Performance**\n");
        for (Map.Entry<String, Double> entry : subjectScores.entrySet()) {
            String subject = entry.getKey();
            double score = entry.getValue();
            String grade = GradingSystemUtil.calculateGrade(score);
            String remarks = GradingSystemUtil.getRemarks(grade);

            report.append("➡ ").append(subject)
                    .append(": Score ").append(score)
                    .append(", Grade: ").append(grade)
                    .append(", Remarks: ").append(remarks).append("\n");

            totalPoints += extractPoints(grade);
        }

        double averagePoints = totalPoints / subjectCount;
        report.append("\n📈 **Overall Performance**\n");
        report.append("📌 Total Points: ").append(totalPoints).append("\n");
        report.append("📊 Average Points: ").append(averagePoints).append("\n");
        report.append("🎖 Final Grade: ").append(GradingSystemUtil.calculateGrade(averagePoints)).append("\n");

        return report.toString();
    }

    private static double extractPoints(String grade) {
        return Double.parseDouble(grade.replaceAll("[^0-9]", ""));
    }
}
