package org.pupils.example.api.controller;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptorController {

    @GetMapping("/encrypt")
    public ResponseEntity<String> encryptOnlinePwd(@RequestParam String seed, @RequestParam String pwd) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(seed);

        return ResponseEntity.ok(encryptor.encrypt(pwd));
    }

}
