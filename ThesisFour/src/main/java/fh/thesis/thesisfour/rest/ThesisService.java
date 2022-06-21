package fh.thesis.thesisfour.rest;

import fh.thesis.thesisfour.models.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface ThesisService {
    @GetMapping("/hash")
    ResponseEntity<List<ServiceResponse>> hash(@RequestHeader String originalString, @RequestHeader int difficulty);

    @GetMapping("/fibonacci")
    ResponseEntity<List<ServiceResponse>> fibonacci(@RequestHeader int count);
}
