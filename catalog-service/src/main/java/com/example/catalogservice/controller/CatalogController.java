package com.example.catalogservice.controller;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

  private final Environment environment;
  private final CatalogService catalogService;

  @GetMapping("/health_check")
  public String status(HttpServletRequest request) {
    return "Cata-Service Working : " + request.getServerPort();
  }

  @GetMapping("/catalogs")
  public ResponseEntity getUsers() {
    Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();

    ArrayList<Object> result = new ArrayList<>();
    catalogList.forEach(v -> result.add(new ModelMapper().map(v, ResponseCatalog.class)));

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }


}
