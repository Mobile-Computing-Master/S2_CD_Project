package fh.thesis.thesistwo.models;

import java.time.Instant;

public record ServiceResponse(String origin, String payload, Instant timestamp) {
}
