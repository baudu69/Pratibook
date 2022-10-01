package fr.inextenso.pratibook.pratibookapi.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public record JwtResponse(String jwt, List<String> roles) {
}
