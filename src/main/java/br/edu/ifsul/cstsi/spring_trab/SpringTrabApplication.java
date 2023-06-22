package br.edu.ifsul.cstsi.spring_trab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTrabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTrabApplication.class, args);
        HomeController.main();
    }

}
