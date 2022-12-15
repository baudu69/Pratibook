package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.service.ServiceOeuvre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/oeuvre")
public class OeuvreController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ServiceOeuvre serviceOeuvre;

    public OeuvreController(ServiceOeuvre serviceOeuvre) {
        this.serviceOeuvre = serviceOeuvre;
    }

    @GetMapping
    public ResponseEntity<List<OeuvreDTO>> findAll() {
        logger.info("REST GET /api/oeuvre");
        return ResponseEntity.ok(serviceOeuvre.findAll());
    }
}
