package fh.thesis.thesisfive.rest;

import fh.thesis.thesisfive.config.ApplicationMetadata;
import fh.thesis.thesisfive.logic.Fibonacci;
import fh.thesis.thesisfive.logic.ProofOfWork;
import fh.thesis.thesisfive.models.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("thesis")
public class ThesisServiceImpl implements ThesisService {

    final ProofOfWork proofOfWork;
    final Fibonacci fibonacci;
    final ApplicationMetadata applicationMetadata;

    public ThesisServiceImpl(ProofOfWork proofOfWork, Fibonacci fibonacci, ApplicationMetadata applicationMetadata) {
        this.proofOfWork = proofOfWork;
        this.fibonacci = fibonacci;
        this.applicationMetadata = applicationMetadata;
    }

    @Override
    @GetMapping("/hash")
    public ResponseEntity<List<ServiceResponse>> hash(@RequestHeader String originalString, @RequestHeader int difficulty) {

        ArrayList<ServiceResponse> serviceResponses = new ArrayList<>();
        serviceResponses.add(new ServiceResponse(
                applicationMetadata.name(),
                this.proofOfWork.calculateHash(originalString, difficulty),
                Instant.now()
        ));

        return new ResponseEntity<>(serviceResponses, HttpStatus.OK);
    }

    @Override
    @GetMapping("/fibonacci")
    public ResponseEntity<List<ServiceResponse>> fibonacci(@RequestHeader int count) {

        ArrayList<ServiceResponse> serviceResponses = new ArrayList<>();
        serviceResponses.add(new ServiceResponse(
                applicationMetadata.name(),
                this.fibonacci.calculate(count).toString(),
                Instant.now()
        ));

        return new ResponseEntity<>(serviceResponses, HttpStatus.OK);
    }
}
