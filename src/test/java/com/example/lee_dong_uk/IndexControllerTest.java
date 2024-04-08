package com.example.lee_dong_uk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){
        String url = "http://localhost:" + "8080" + "/";

        //when
        String body = this.restTemplate.getForObject(url, String.class);

        System.out.println("=======");
        System.out.println(body);
        System.out.println("=======");

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }

}