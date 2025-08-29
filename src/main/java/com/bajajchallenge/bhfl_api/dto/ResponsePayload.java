package com.bajajchallenge.bhfl_api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Lombok: Creates a no-argument constructor
@AllArgsConstructor // Lombok: Creates a constructor with all arguments
public class ResponsePayload {
    private boolean is_success;
    private String user_id;
    private String email;
    private String roll_number;
    private List<String> odd_numbers;
    private List<String> even_numbers;
    private List<String> alphabets;
    private List<String> special_characters; // The PDF has a typo "sepcial_characters", but we'll use the correct spelling.
    private String sum;
    private String concat_string;
}