package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.InstanceDTO;
import fr.inextenso.pratibook.dto.InstanceOeuvreCreationDTO;
import fr.inextenso.pratibook.exception.BarcodeAlreadyUsedException;
import fr.inextenso.pratibook.exception.NotFoundException;
import fr.inextenso.pratibook.model.*;
import fr.inextenso.pratibook.repository.InstanceOeuvreRepository;
import fr.inextenso.pratibook.repository.LocationRepository;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import fr.inextenso.pratibook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceInstanceOeuvre {

    private final InstanceOeuvreRepository instanceOeuvreRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final OeuvreRepository oeuvreRepository;

    public ServiceInstanceOeuvre(InstanceOeuvreRepository instanceOeuvreRepository, UserRepository userRepository, LocationRepository locationRepository, OeuvreRepository oeuvreRepository) {
        this.instanceOeuvreRepository = instanceOeuvreRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
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

    public List<InstanceDTO> getAllInstancesOfOeuvre(int oeuvreId) {
        List<InstanceDTO> instancesDTO = new ArrayList<>();
        List<InstanceOeuvre> instances = this.instanceOeuvreRepository.findByOeuvre_Id(oeuvreId);
        for (InstanceOeuvre instanceOeuvre : instances) {
            final String codeBarre = instanceOeuvre.getCodeBarre();
            Etat etat = null;
            Disponibilite disponibilite = instanceOeuvre.getEtatDisponibilite();
            String nomReserveur = null;
            String prenomReserveur = null;
            LocalDate dateRenduTheorique = null;
            Optional<Location> oplocation = locationRepository.findByCodeBarreOrderByDateRenduReelDesc(codeBarre).stream().filter(location -> location.getDateRenduReel() == null).findFirst();
            if (oplocation.isPresent()) {
                Location location = oplocation.get();
                etat = location.getEtatPhysiqueRendu();
                if (etat == null) {
                    etat = Etat.NEUF;
                }
                if (location.getDateRenduReel() == null) {
                    int idUser = location.getIdUtilisateur();
                    User user = userRepository.findById(idUser).orElseThrow(NotFoundException::new);
                    nomReserveur = user.getNom();
                    prenomReserveur = user.getPrenom();
                    dateRenduTheorique = location.getDateRenduTheorique();
                }
            }
            InstanceDTO instanceDTO = new InstanceDTO(codeBarre, etat, disponibilite, nomReserveur, prenomReserveur, dateRenduTheorique);
            instancesDTO.add(instanceDTO);
        }
        return instancesDTO;
    }

}
