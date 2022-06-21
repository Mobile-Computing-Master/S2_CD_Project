package fh.thesis.thesistwo.rest;

import fh.thesis.thesistwo.config.ApplicationMetadata;
import fh.thesis.thesistwo.config.ServiceProperty;
import fh.thesis.thesistwo.exceptions.ThesisException;
import fh.thesis.thesistwo.logic.Fibonacci;
import fh.thesis.thesistwo.logic.ProofOfWork;
import fh.thesis.thesistwo.models.ServiceResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static reactor.core.publisher.Mono.error;

@RestController()
@RequestMapping("thesis")
public class ThesisServiceImpl implements ThesisService {

    final ProofOfWork proofOfWork;
    final Fibonacci fibonacci;
    final WebClient client;
    final ServiceProperty nextService;
    final ApplicationMetadata applicationMetadata;

    public ThesisServiceImpl(ProofOfWork proofOfWork, Fibonacci fibonacci, ServiceProperty serviceProperty, ApplicationMetadata applicationMetadata) {
        this.proofOfWork = proofOfWork;
        this.fibonacci = fibonacci;
        this.nextService = serviceProperty;
        this.client = WebClient.create(nextService.fullServiceAddress() + "/thesis");
        this.applicationMetadata = applicationMetadata;
    }

    @Override
    @GetMapping("/hash")
    public ResponseEntity<List<ServiceResponse>> hash(@RequestHeader String originalString, @RequestHeader int difficulty) {
        Flux<ServiceResponse> responseFlux = client
                .get()
                .uri("/hash")
                .header("originalString", originalString)
                .header("difficulty", String.valueOf(difficulty))
                .retrieve()
                .onStatus(not(HttpStatus::is2xxSuccessful), clientResponse ->
                    error(new ThesisException("Something went wrong calling " + this.nextService.host()))
                )
                .bodyToFlux(ServiceResponse.class);

        Mono<List<ServiceResponse>> responseList = responseFlux.collect(Collectors.toList());
        List<ServiceResponse> serviceResponses = responseList.block();

        if (serviceResponses == null) {
            throw new ThesisException("Responses from " + this.nextService.host() + " were empty!");
        }

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
        Flux<ServiceResponse> responseFlux = client.method(HttpMethod.GET)
                .uri("/fibonacci")
                .header("count", String.valueOf(count))
                .retrieve()
                .bodyToFlux(ServiceResponse.class);

        Mono<List<ServiceResponse>> responseList = responseFlux.collect(Collectors.toList());
        List<ServiceResponse> serviceResponses = responseList.block();
        if (serviceResponses == null) {
            throw new ThesisException("Responses from " + this.nextService.host() + " were empty!");
        }

        serviceResponses.add(new ServiceResponse(
                applicationMetadata.name(),
                this.fibonacci.calculate(count).toString(),
                Instant.now()
        ));

        return new ResponseEntity<>(serviceResponses, HttpStatus.OK);
    }
}
