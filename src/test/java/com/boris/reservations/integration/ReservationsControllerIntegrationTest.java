package com.boris.reservations.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Reservation.ReservationBuilder;
import com.boris.reservations.service.ReservationService;

public class ReservationsControllerIntegrationTest {

	private static final LocalDate DUMMY_RESERVATION_DATE = LocalDate.now();

	private static final int DUMMY_RESERVATION_VENUE_ID = 12;

	private static final int DUMMY_RESERVATION_PEOPLE_ATTENDING = 10;

	private static final int DUMMY_RESERVATION_ID = 1;

	private static final Reservation DUMMY_RESERVATION = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
			.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(DUMMY_RESERVATION_DATE)
			.withVenueId(DUMMY_RESERVATION_VENUE_ID).build();

	private static final String DUMMY_USER_EMAIL = "dummyEmail";

//	@Autowired
//	private WebApplicationContext wac;

	@Mock
	private ReservationService reservationService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Ignore
	public void getReservationsForUser() throws Exception {

		when(reservationService.getReservationsForUser(DUMMY_USER_EMAIL)).thenReturn(Optional.of(Arrays.asList(DUMMY_RESERVATION)));

		this.mockMvc.perform(get("/reservations/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.reservations[0].id").value(DUMMY_RESERVATION_ID));
	}

	@Ignore
	public void getFreeTablesForVenue() throws Exception {
		this.mockMvc
				.perform(get("/reservations/123/2015-12-11/12")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk());
	}

	@Ignore
	public void getAllReservationsForVenue() throws Exception {
		this.mockMvc
				.perform(
						get("/reservations/venueId").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk());
	}

	@Ignore
	public void addReservation() throws Exception {
		// this.mockMvc.perform(get("/reservations/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		// .andExpect(status().isOk());
	}

//	@Configuration
//	static class MockConfig {
//
//		@Bean
//		public ReservationService personDao() {
//			return Mockito.mock(ReservationServiceImpl.class);
//		}
//	}

}
