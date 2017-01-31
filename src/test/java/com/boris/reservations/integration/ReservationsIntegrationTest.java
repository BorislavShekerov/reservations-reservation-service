package com.boris.reservations.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.boris.reservations.dao.ReservationDao;
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Reservation.ReservationBuilder;
import com.boris.reservations.model.Table;
import com.boris.reservations.model.User;
import com.boris.reservations.model.Venue;
import com.boris.reservations.service.VenueServiceStub;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
public class ReservationsIntegrationTest extends ReservationsIntegrationBaseTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	ReservationDao reservationsDao;
	
	
	@Before
	public void setup() {
		reservationsDao.deleteAll();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	@Rollback(true)
	public void getReservationsForUser() throws Exception {

		Reservation userTestReservation = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
				.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(LocalDate.now())
				.withVenue(DUMMY_RESERVED_VENUE).withUserReserved(DUMMY_USER).withTableReserved(venueTable_capacity2)
				.build();

		reservationsDao.save(userTestReservation);

		this.mockMvc.perform(get("/reservations/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(userTestReservation.getId()))
				.andExpect(jsonPath("$.[0].peopleAttending").value(userTestReservation.getPeopleAttending()))
				.andExpect(jsonPath("$.[0].userReserved.email").value(userTestReservation.getUserReserved().getEmail()))
				.andExpect(jsonPath("$.[0].venue.id").value(userTestReservation.getVenue().getId()))
				.andExpect(
						jsonPath("$.[0].reservationDate[0]").value(userTestReservation.getReservationDate().getYear()))
				.andExpect(jsonPath("$.[0].tableReserved.number")
						.value(userTestReservation.getTableReserved().getNumber()));
	}

	@Test
	@Rollback(true)
	public void getReservationsForUser_noReservations() throws Exception {
		this.mockMvc.perform(get("/reservations/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string("[]"));
	}

	@Test
	@Rollback(true)
	public void getAllReservationsForVenue() throws Exception {
		Reservation userTestReservation = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
				.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(LocalDate.now())
				.withVenue(DUMMY_RESERVED_VENUE).withUserReserved(DUMMY_USER).withTableReserved(venueTable_capacity2)
				.build();

		reservationsDao.save(userTestReservation);

		this.mockMvc
				.perform(get("/reservations/" + DUMMY_VENUE_ID + "/" + DUMMY_RESERVATION_DATE.toString())
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(userTestReservation.getId()))
				.andExpect(jsonPath("$.[0].venue.id").value(userTestReservation.getVenue().getId()));
	}

	@Test
	@Rollback(true)
	public void getAllReservationsForVenue_noReservation() throws Exception {
		this.mockMvc
				.perform(get("/reservations/" + DUMMY_VENUE_ID + "/" + DUMMY_RESERVATION_DATE.toString())
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string("[]"));
	}

	@Test
	@Rollback(true)
	public void addReservation() throws Exception {
		ReservationRequest request = new ReservationRequest(DUMMY_RESERVATION_ID, DUMMY_RESERVATION_DATE.toString(), DUMMY_RESERVED_VENUE,
				DUMMY_RESERVATION_PEOPLE_ATTENDING, venueTable_capacity2, DUMMY_USER);

		this.mockMvc
				.perform(post("/reservations/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request))
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").isNotEmpty());
	}

	@Test
	@Rollback(true)
	public void getFreeTablesForVenue_noFreeTables() throws Exception {
		Reservation userTestReservation = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
				.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(LocalDate.now())
				.withVenue(DUMMY_RESERVED_VENUE).withUserReserved(DUMMY_USER).withTableReserved(venueTable_capacity2)
				.build();

		reservationsDao.save(userTestReservation);
		
		this.mockMvc
				.perform(get("/reservations/" + DUMMY_VENUE_ID + "/" + DUMMY_RESERVATION_DATE.toString() + "/"
						+ DUMMY_RESERVATION_PEOPLE_ATTENDING)
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("[]"));

	}
	@Test
	@Rollback(true)
	public void getFreeTablesForVenue_freeTablesAvailable() throws Exception {
		this.mockMvc
		.perform(get("/reservations/" + DUMMY_VENUE_ID + "/" + DUMMY_RESERVATION_DATE.toString() + "/"
				+ VenueServiceStub.AVAILABLE_TABLE.getCapacity())
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$[0]").exists())
		.andExpect(jsonPath("$[0].number").value(VenueServiceStub.AVAILABLE_TABLE.getNumber()));
		
	}

	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class ReservationRequest {

		private long id;
		private String reservationDate;
		private Venue venue;
		private int peopleAttending;
		private Table tableReserved;
		private User userReserved;

		public ReservationRequest(long id, String reservationDate, Venue venue, int peopleAttending,
				Table tableReserved, User userReserved) {
			this.id = id;
			this.reservationDate = reservationDate;
			this.venue = venue;
			this.peopleAttending = peopleAttending;
			this.tableReserved = tableReserved;
			this.userReserved = userReserved;
		}

		public long getId() {
			return id;
		}

		public String getReservationDate() {
			return reservationDate;
		}

		public Venue getVenue() {
			return venue;
		}

		public int getPeopleAttending() {
			return peopleAttending;
		}

		public Table getTableReserved() {
			return tableReserved;
		}

		public User getUserReserved() {
			return userReserved;
		}

	}

}
