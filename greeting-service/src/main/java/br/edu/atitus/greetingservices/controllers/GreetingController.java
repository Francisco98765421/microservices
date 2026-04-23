package br.edu.atitus.greetingservices.controllers;

import br.edu.atitus.greetingservices.configs.GreetingConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.edu.atitus.greetingservices.dto.NameRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

//    @Value("${greeting-service.greeting}")
//    private String greeting;
//
//    @Value("${greeting-service.default-name}")
//    private String defaultName;

    private final GreetingConfig config;
    //Injeção de dependência -
    public GreetingController(GreetingConfig config) {
        this.config = config;
    }

    @GetMapping({"", "/"})
    public String getGreeting(
            @RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            name = config.getDefaultName();
        }
        String greetingReturn = String.format("%s %s!!!", config.getGreeting(), name);
        return greetingReturn;
    }
    @GetMapping("/{name}")
    public String getGreetingPath(@PathVariable String name) {
        if (name == null || name.isEmpty()) {
            name = config.getDefaultName();
        }
        String greetingReturn = String.format("%s %s!!!", config.getGreeting(), name);
        return greetingReturn;
    }

    @PostMapping
    public String postGreeting(@RequestBody NameRequest request) {
        String name = request.getName();

        if (name == null || name.isEmpty()) {
            name = config.getDefaultName();
        }

        String greetingReturn = String.format("%s %s!!!", config.getGreeting(), name);
        return greetingReturn;
    }
}