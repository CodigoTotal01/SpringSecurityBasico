package com.example.controller;

import jdk.security.jarsigner.JarSigner;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1")
public class CustomController {


    @Autowired
    private SessionRegistry sesionRegistry;

    @GetMapping("/index")
    public String index() {
        return "Hello World!";
    }


    @GetMapping("/index2")
    public String index2() {
        return "Hello World2 is NOT Secure!";
    }

    @GetMapping("/session")
    public ResponseEntity<?> getDetailSession() {

        String sessionId = "";
        User user = null;

        List<Object> session = sesionRegistry.getAllPrincipals();
        for (Object principal : session) {
            if (principal instanceof User) {
                user = (User) principal;
            }
            List<SessionInformation> sessionRegistry = sesionRegistry.getAllSessions(user, false);
            for (SessionInformation sessionInformation : sessionRegistry) {
                sessionId = sessionInformation.getSessionId();
            }
        }


        Map<String, Object> response = new HashMap<>();
        response.put("reponse", "Hellow WOrld");
        response.put("sessionId", sessionId);
        response.put("user", user);
        return ResponseEntity.ok(response);

    }
}
