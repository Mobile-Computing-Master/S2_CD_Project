package fh.thesis.thesisone.models;

import java.time.Instant;

public record ServiceResponse(String origin, String payload, Instant timestamp) {
}
