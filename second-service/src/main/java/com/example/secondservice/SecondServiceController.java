package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/second-service")
public class SecondServiceController {

  @GetMapping("/welcome")
  public String welcome() {
    return "Second Service!";
  }

  @GetMapping("/message")
  public String message(@RequestHeader("second-request") String header) {
    log.info(header);
    return "hello world in second service";
  }

  @GetMapping("/check")
  public String check() {
    return "hi there. this is message from second service";
  }
}
