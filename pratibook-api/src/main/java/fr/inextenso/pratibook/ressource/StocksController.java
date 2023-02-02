package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.AuteurDTO;
import fr.inextenso.pratibook.dto.InstanceOeuvreCreationDTO;
import fr.inextenso.pratibook.dto.OeuvreEditionDTO;
import fr.inextenso.pratibook.service.ServiceAuteur;
import fr.inextenso.pratibook.service.ServiceInstanceOeuvre;
import fr.inextenso.pratibook.service.ServiceOeuvre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/stocks")
public class StocksController {

    private final ServiceAuteur serviceAuteur;
    private final ServiceOeuvre serviceOeuvre;
    private final ServiceInstanceOeuvre serviceInstanceOeuvre;

    public StocksController(ServiceAuteur serviceAuteur, ServiceOeuvre serviceOeuvre, ServiceInstanceOeuvre serviceInstanceOeuvre) {
        this.serviceAuteur = serviceAuteur;
        this.serviceOeuvre = serviceOeuvre;
        this.serviceInstanceOeuvre = serviceInstanceOeuvre;
    }

    @PostMapping("/auteur")
    public ResponseEntity<Void> addAuteur(@RequestBody AuteurDTO auteur) {
        this.serviceAuteur.addAuteur(auteur);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/auteur")
    public ResponseEntity<Void> updateAuteur(@RequestBody AuteurDTO auteur) {
        if (auteur.idAuteur() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.serviceAuteur.updateAuteur(auteur);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/auteur/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.serviceAuteur.deleteAuteur(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/oeuvre")
    public ResponseEntity<Void> addOeuvre(@RequestBody OeuvreEditionDTO oeuvre) {
        this.serviceOeuvre.addOeuvre(oeuvre);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/oeuvre")
    public ResponseEntity<Void> updateOeuvre(@RequestBody OeuvreEditionDTO oeuvre) {
        if (oeuvre.id() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.serviceOeuvre.updateOeuvre(oeuvre);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/oeuvre/{id}")
    public ResponseEntity<Void> deleteOeuvre(@PathVariable Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/instance-oeuvre")
    public ResponseEntity<Void> addInstanceOeuvre(@RequestBody InstanceOeuvreCreationDTO instanceOeuvre) {
        if (instanceOeuvre.oeuvreId() == null || instanceOeuvre.codeBarre() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.serviceInstanceOeuvre.addInstanceOeuvre(instanceOeuvre);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/instance-oeuvre/{codeBarre}")
    public ResponseEntity<Void> deleteInstanceOeuvre(@PathVariable String codeBarre) {
        if (codeBarre == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.serviceInstanceOeuvre.deleteInstanceOeuvre(codeBarre);
        return ResponseEntity.noContent().build();
    }

}
