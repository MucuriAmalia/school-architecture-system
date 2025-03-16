package com.emtech.configuration_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private SubjectName subjectName;


    public enum SubjectName {
        MATHEMATICS("Mathematics"),
        ENGLISH("English"),
        KISWAHILI("Kiswahili"),
        PHYSICS("Physics"),
        CHEMISTRY("Chemistry"),
        BIOLOGY("Biology"),
        GEOGRAPHY("Geography"),
        HISTORY("History"),
        BUSINESS("Business Studies"),
        AGRICULTURE("Agriculture"),
        COMPUTER_STUDIES("Computer Studies"),
        RELIGIOUS_EDUCATION("Religious Education");

        private final String displayName;

        SubjectName(String displayName) {
            this.displayName = displayName;
        }

    }



}
