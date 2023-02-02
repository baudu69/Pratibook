package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.InstanceDTO;
import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.service.ServiceInstanceOeuvre;
import fr.inextenso.pratibook.service.ServiceOeuvre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/oeuvre")
public class OeuvreController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ServiceOeuvre serviceOeuvre;
    private final ServiceInstanceOeuvre serviceInstanceOeuvre;

    public OeuvreController(ServiceOeuvre serviceOeuvre, ServiceInstanceOeuvre serviceInstanceOeuvre) {
        this.serviceOeuvre = serviceOeuvre;
        this.serviceInstanceOeuvre = serviceInstanceOeuvre;
    }

    @GetMapping
    public ResponseEntity<List<OeuvreDTO>> findAll() {
        logger.info("REST GET /api/oeuvre");
        return ResponseEntity.ok(serviceOeuvre.findAll());
    }

    @GetMapping("/{idOeuvre}")
    public ResponseEntity<OeuvreDTO> findById(@PathVariable Integer idOeuvre) {
        logger.info("REST GET /api/oeuvre/{}", idOeuvre);
        try {
            return ResponseEntity.ok(serviceOeuvre.findById(idOeuvre));
        } catch (NoSuchElementException e) {
            logger.warn("Oeuvre {} not found", idOeuvre);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idOeuvre}/instances")
    public ResponseEntity<List<InstanceDTO>> getAllInstancesOfOeuvre(@PathVariable Integer idOeuvre) {
        logger.info("REST GET /api/oeuvre/{}/instances", idOeuvre);
        try {
            return ResponseEntity.ok(serviceInstanceOeuvre.getAllInstancesOfOeuvre(idOeuvre));
        } catch (NoSuchElementException e) {
            logger.warn("Oeuvre {} not found", idOeuvre);
            return ResponseEntity.notFound().build();
        }
    }
}
