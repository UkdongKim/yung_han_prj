package com.example.lee_dong_uk;

import com.example.lee_dong_uk.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/dto")
    public HelloResponseDto hello(@RequestParam("name") String name,
                                  @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);

    }

    @GetMapping("/hello")
    public String hellos() {
        return "hello";
    }

}
