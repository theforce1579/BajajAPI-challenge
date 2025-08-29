package com.bajajchallenge.bhfl_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bajajchallenge.bhfl_api.dto.RequestPayload;
import com.bajajchallenge.bhfl_api.dto.ResponsePayload;

@RestController
public class ApiController {

    @PostMapping("/bfhl") // Handles POST requests to the /bfhl route [cite: 29, 30]
    public ResponseEntity<ResponsePayload> processData(@RequestBody RequestPayload request) {
        // --- 1. Personal Details (REPLACE WITH YOURS) ---
        final String userId = "your_full_name_ddmmyyyy"; // [cite: 23]
        final String emailId = "your.email@example.com";
        final String rollNumber = "YOUR_ROLL_NUMBER";

        try {
            List<String> data = request.getData();

            // --- 2. Input Validation ---
            if (data == null) {
                // Handle null input gracefully [cite: 27]
                ResponsePayload errorResponse = new ResponsePayload(false, userId, emailId, rollNumber, null, null, null, null, null, null);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            // --- 3. Data Processing Logic ---
            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();
            int sum = 0;
            StringBuilder alphabetChars = new StringBuilder();

            for (String item : data) {
                // Check if it's a number
                if (item.matches("-?\\d+")) {
                    int num = Integer.parseInt(item);
                    if (num % 2 == 0) {
                        evenNumbers.add(item); // Add even number string [cite: 9]
                    } else {
                        oddNumbers.add(item); // Add odd number string [cite: 10]
                    }
                    sum += num;
                } 
                // Check if it's purely alphabetic
                else if (item.matches("[a-zA-Z]+")) {
                    alphabets.add(item.toUpperCase()); // Add alphabet, converted to uppercase [cite: 11]
                    alphabetChars.append(item);
                } 
                // Otherwise, it's a special character
                else {
                    specialCharacters.add(item); // Add special character [cite: 12]
                }
            }

            // --- 4. Logic for the concatenated string [cite: 14, 15] ---
            String reversed = new StringBuilder(alphabetChars.toString()).reverse().toString();
            StringBuilder concatString = new StringBuilder();
            for (int i = 0; i < reversed.length(); i++) {
                char c = reversed.charAt(i);
                concatString.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }

            // --- 5. Assemble the Final Response ---
            ResponsePayload response = new ResponsePayload(
                true, // is_success [cite: 26]
                userId,
                emailId,
                rollNumber,
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                String.valueOf(sum), // Sum must be returned as a string [cite: 39]
                concatString.toString()
            );
            
            // Return a 200 OK status for success [cite: 31]
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            // Generic error handling [cite: 27]
            ResponsePayload errorResponse = new ResponsePayload(false, userId, emailId, rollNumber, null, null, null, null, null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}