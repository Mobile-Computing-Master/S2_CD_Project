package fh.thesis.thesisthree.models;

import java.time.Instant;

public record ServiceResponse(String origin, String payload, Instant timestamp) {
}
