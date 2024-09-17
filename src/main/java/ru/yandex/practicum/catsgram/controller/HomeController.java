package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String homePage() {
        log.info("Страница приветствия");
        return "<h1>Приветствуем вас, в приложении Котограм<h1>";
    }
}
