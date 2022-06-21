package fh.thesis.thesisfour.models;

import java.time.Instant;

public record ServiceResponse(String origin, String payload, Instant timestamp) {
}
