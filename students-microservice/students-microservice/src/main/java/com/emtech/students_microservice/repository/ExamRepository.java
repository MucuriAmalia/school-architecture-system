package com.emtech.students_microservice.repository;

import com.emtech.students_microservice.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("SELECT e.student.id, e.student.studentName, SUM(e.score), AVG(e.score), " +
            "RANK() OVER (ORDER BY AVG(e.score) DESC) " +
            "FROM Exam e " +
            "WHERE e.term = :term AND e.year = :year " +
            "GROUP BY e.student.id, e.student.studentName")
    List<Object[]> getTermYearReport(@Param("term") String term, @Param("year") int year);



    List<Exam> findByStudentId(Long studentId);
}


