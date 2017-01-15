package com.boris.reservations.integration;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.boris.reservations.ReservationBaseTest;
import com.boris.reservations.TestConfing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfing.class)
@WebAppConfiguration
@Ignore
public class ReservationsIntegrationBaseTest extends ReservationBaseTest {

}
