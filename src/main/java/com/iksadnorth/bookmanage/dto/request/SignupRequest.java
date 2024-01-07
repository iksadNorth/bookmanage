package com.iksadnorth.bookmanage.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    @JsonProperty("admin_key")
    private String adminKey;
}
