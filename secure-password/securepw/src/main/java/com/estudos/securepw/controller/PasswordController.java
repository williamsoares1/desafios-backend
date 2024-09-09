package com.estudos.securepw.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.securepw.dto.PasswordDTO;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/validate-password")
@Log4j2
public class PasswordController {

    @PostMapping
    public ResponseEntity<String> validatePassword(@RequestBody PasswordDTO dto) throws BadRequestException {
        String passwordStr = dto.password();

        if (passwordStr.length() >= 8) {
            Pattern specialChar = Pattern.compile("[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~\"']");
            
            Matcher hasSpecialChar = specialChar.matcher(passwordStr);
            boolean hasUpperCase = passwordStr.chars().anyMatch(Character::isUpperCase);
            boolean hasLowerCase = passwordStr.chars().anyMatch(Character::isLowerCase);
            boolean hasNumber = passwordStr.chars().anyMatch(Character::isDigit);

            boolean isValid = hasSpecialChar.find() && hasUpperCase && hasLowerCase && hasNumber;

            if (isValid)
                return ResponseEntity.noContent().build();
        }

        throw new BadRequestException("Senha inválida.");
    }
}
