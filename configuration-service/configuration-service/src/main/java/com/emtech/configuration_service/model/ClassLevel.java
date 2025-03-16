package com.emtech.configuration_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "class_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String formName;


    public enum FormName {
        FORM_ONE("Form 1"),
        FORM_TWO("Form 2"),
        FORM_THREE("Form 3"),
        FORM_FOUR("Form 4");

        private final String displayName;

        FormName(String displayName) {
            this.displayName = displayName;
        }
    }
}
