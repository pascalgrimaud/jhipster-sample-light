package com.mycompany.myapp.beer.primary;

import java.time.Instant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beer")
class BeerResource {

  @GetMapping
  public String getBeer() {
    return "Beer at " + Instant.now();
  }
}
