package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.InstanceOeuvreCreationDTO;
import fr.inextenso.pratibook.exception.BarcodeAlreadyUsedException;
import fr.inextenso.pratibook.exception.NotFoundException;
import fr.inextenso.pratibook.model.Disponibilite;
import fr.inextenso.pratibook.model.InstanceOeuvre;
import fr.inextenso.pratibook.repository.InstanceOeuvreRepository;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiceInstanceOeuvre {

    private final InstanceOeuvreRepository instanceOeuvreRepository;
    private final OeuvreRepository oeuvreRepository;

    public ServiceInstanceOeuvre(InstanceOeuvreRepository instanceOeuvreRepository, OeuvreRepository oeuvreRepository) {
        this.instanceOeuvreRepository = instanceOeuvreRepository;
        this.oeuvreRepository = oeuvreRepository;
    }

    public void addInstanceOeuvre(InstanceOeuvreCreationDTO dto) {
        Objects.requireNonNull(dto.codeBarre());
        Objects.requireNonNull(dto.oeuvreId());

        if (this.instanceOeuvreRepository.existsById(dto.codeBarre())) throw new BarcodeAlreadyUsedException();
        if (!this.oeuvreRepository.existsById(dto.oeuvreId())) throw new NotFoundException();


        final InstanceOeuvre instanceOeuvre = new InstanceOeuvre();

        instanceOeuvre.setCodeBarre(dto.codeBarre());
        instanceOeuvre.setIdOeuvre(dto.oeuvreId());
        instanceOeuvre.setEtatDisponibilite(Disponibilite.DISPONIBLE);

        this.instanceOeuvreRepository.save(instanceOeuvre);
    }

    public void deleteInstanceOeuvre(String codeBarre) {
        Objects.requireNonNull(codeBarre);

        final InstanceOeuvre instanceOeuvre = this.instanceOeuvreRepository.findById(codeBarre)
                .orElseThrow(NotFoundException::new);

        this.instanceOeuvreRepository.delete(instanceOeuvre);
    }

}
