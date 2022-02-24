package com.example.firstservice;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/first-service")
public class FirstServiceController {

  Environment env;

  @Autowired
  public FirstServiceController(Environment env) {
    this.env = env;
  }

  @GetMapping("/welcome")
  public String welcome() {
    return "First Service!";
  }

  @GetMapping("/message")
  public String message(@RequestHeader("first-request") String header) {
    log.info(header);
    return "hello world in first service";
  }

  @GetMapping("/check")
  public String check(HttpServletRequest request) {
    log.info("server port = {}", request.getServerPort());
    return String.format("hi there. this is message from first service on port : %s",  env.getProperty("local.server.port"));
  }


}
