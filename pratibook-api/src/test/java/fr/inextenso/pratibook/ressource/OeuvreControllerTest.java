package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.service.ServiceOeuvre;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class OeuvreControllerTest {

	@MockBean
	private ServiceOeuvre serviceOeuvre;

	@Autowired
	private OeuvreController oeuvreController;

	@Test
	void findAll() {
		OeuvreDTO oeuvreDTO = new OeuvreDTO(1, "Titre", (short) 2021, "1234567890123", 1, null, null);
		when(serviceOeuvre.findAll()).thenReturn(List.of(oeuvreDTO));
		ResponseEntity<List<OeuvreDTO>> res = oeuvreController.findAll();
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(List.of(oeuvreDTO), res.getBody());
	}

	@Test
	void findById() {
		when(serviceOeuvre.findById(1)).thenThrow(NoSuchElementException.class);
		assertEquals(HttpStatus.NOT_FOUND, oeuvreController.findById(1).getStatusCode());
		OeuvreDTO oeuvreDTO = new OeuvreDTO(1, "Titre", (short) 2021, "1234567890123", 1, null, null);
		when(serviceOeuvre.findById(2)).thenReturn(oeuvreDTO);
		assertEquals(oeuvreDTO, oeuvreController.findById(2).getBody());
	}
}