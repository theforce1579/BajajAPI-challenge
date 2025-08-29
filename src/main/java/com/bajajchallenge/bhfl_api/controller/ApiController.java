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

    @PostMapping("/bfhl")
    public ResponseEntity<ResponsePayload> processData(@RequestBody RequestPayload request) {
        final String userId = "hemanth_balgi_06122003"; 
        final String emailId = "hemanth.shivanand2022@vitstudent.ac.in";
        final String rollNumber = "22BCE3106";

        try {
            List<String> data = request.getData();
            if (data == null) {
                ResponsePayload errorResponse = new ResponsePayload(false, userId, emailId, rollNumber, null, null, null, null, null, null);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();
            int sum = 0;
            StringBuilder alphabetChars = new StringBuilder();

            for (String item : data) {
                if (item.matches("-?\\d+")) {
                    int num = Integer.parseInt(item);
                    if (num % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                    sum += num;
                } 
                else if (item.matches("[a-zA-Z]+")) {
                    alphabets.add(item.toUpperCase());
                    alphabetChars.append(item);
                } 
                else {
                    specialCharacters.add(item);
                }
            }

            String reversed = new StringBuilder(alphabetChars.toString()).reverse().toString();
            StringBuilder concatString = new StringBuilder();
            for (int i = 0; i < reversed.length(); i++) {
                char c = reversed.charAt(i);
                concatString.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }

            ResponsePayload response = new ResponsePayload(
                true,
                userId,
                emailId,
                rollNumber,
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                String.valueOf(sum),
                concatString.toString()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ResponsePayload errorResponse = new ResponsePayload(false, userId, emailId, rollNumber, null, null, null, null, null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}