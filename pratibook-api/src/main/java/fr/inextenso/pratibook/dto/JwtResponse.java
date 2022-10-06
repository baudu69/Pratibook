package fr.inextenso.pratibook.dto;

import java.time.LocalDateTime;
import java.util.List;

public record JwtResponse(String jwt, List<String> roles, LocalDateTime exp) {
}
