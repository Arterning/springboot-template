package cn.ning.springboot.starter.controller;

import cn.ning.springboot.starter.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping(value = "hello")
@Slf4j
public class HelloController {

    @Autowired
    private ApplicationProperties properties;


    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok(properties.getJwt());
    }

    @PostMapping
    public ResponseEntity<?> postThing(@RequestBody HelloVO helloVO) {
        log.info("message {}", helloVO.getMessage());
        return ResponseEntity.ok(properties.getJwt());
    }

    static class HelloVO implements Serializable {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    static class HelloDTO implements Serializable {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }



}
