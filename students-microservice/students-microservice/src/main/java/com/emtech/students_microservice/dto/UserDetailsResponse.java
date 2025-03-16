package com.emtech.students_microservice.dto; // ✅ Ensure this is in the correct package

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
}
