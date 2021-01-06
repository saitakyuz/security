package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityApplication.class, args);
  }

  @GetMapping("/error")
  public  String getErrorPage(){
    return  "error page";
  }
  @GetMapping("/denied")
  public  String getDeniedPage(){
    return  "access denied";
  }
}
