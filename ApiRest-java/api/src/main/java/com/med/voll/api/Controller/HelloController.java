package com.med.voll.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@SecurityRequirement(name = "bearer-key")
public class HelloController {

    @GetMapping
    public String helloWorld() {        return "Hello World from Mexico";    }


}
