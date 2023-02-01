package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.exception.AlreadyReservedException;
import fr.inextenso.pratibook.exception.NotAvailableException;
import fr.inextenso.pratibook.model.User;
import fr.inextenso.pratibook.service.ServiceReservation;
import fr.inextenso.pratibook.user.UserDetails;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@Transactional
class ReservationControllerTest {

	@MockBean
	private ServiceReservation serviceReservation;

	@Autowired
	private ReservationController reservationController;

	@Test
	void demandeReservation() {
		User user = new User();
		user.setId(1);
		UserDetails userDetails = new UserDetails(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
		doThrow(AlreadyReservedException.class).when(serviceReservation).demandeReservation(1, 1);
		assertThrows(ResponseStatusException.class, () -> reservationController.demandeReservation(1, authentication));
		doThrow(NotAvailableException.class).when(serviceReservation).demandeReservation(2, 1);
		assertThrows(ResponseStatusException.class, () -> reservationController.demandeReservation(2, authentication));
	}
}