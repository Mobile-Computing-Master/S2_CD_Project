package fh.thesis.thesisfive.models;

import java.time.Instant;

public record ServiceResponse(String origin, String payload, Instant timestamp) {
}
