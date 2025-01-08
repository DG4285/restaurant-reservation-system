package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/***********************************************
 * CONFIDENTIAL AND PROPRIETARY
 *
 * The information contained herein is the confidential and the exclusive property of
 * Zebra Technologies Corporation. This document, and the information contained herein, shall not be copied, reproduced, published,
 * displayed or distributed, in whole or in part, in any medium, by any means, for any purpose without the express
 * written consent of Zebra Technologies Corporation.
 *
 * ZEBRA and the stylized Zebra head are trademarks of Zebra Technologies Corporation, registered in many jurisdictions worldwide.
 * All other trademarks are the property of their respective owners.
 * 2025 Zebra Technologies Corporation and/or its affiliates.  All rights reserved.
 *
 * @created 03/01/2025 : 1:23 PM
 * @author DG4285
 ***********************************************/
class RestaurantManagerImplTest {

    private ReservationSystemImpl reservationSystem;
    private RestaurantManager restaurantManager;

    @BeforeEach
    void setUp() {
        restaurantManager = mock(RestaurantManager.class);
        reservationSystem = new ReservationSystemImpl(restaurantManager);
    }


    @Test
    void addReservationSuccessful() {
        String restaurantId = "restaurant-123";
        LocalDate openDate = LocalDate.now().plusDays(2);
        when(restaurantManager.getRestaurant(restaurantId)).thenReturn(new Restaurant(restaurantId, "Test Restaurant", "123 Test St", 50, LocalTime.of(8, 0), LocalTime.of(22, 0), Collections.singletonList(openDate)));

        String reservationId = reservationSystem.addReservation(restaurantId, "John Doe", "john@example.com", "+1 (123) 456-7890", 4, LocalDateTime.of(openDate, LocalTime.of(12, 0)));
        assertNotNull(reservationId);
    }

//    @Test
//    void addReservationRestaurantClosed() {
//        String restaurantId = "restaurant-123";
//        when(restaurantManager.getRestaurantById(restaurantId)).thenReturn(new Restaurant(restaurantId, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.now().plusDays(1))));
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> reservationSystem.addReservation(restaurantId, "John Doe", "john@example.com", "+94123456789", 4, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(12, 0))));
//        assertEquals("Cannot make a reservation on a closed date", exception.getMessage());
//    }
//
//    @Test
//    void addReservationOutsideOpenHours() {
//        String restaurantId = "restaurant-123";
//        when(restaurantManager.getRestaurantById(restaurantId)).thenReturn(new Restaurant(restaurantId, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.now().plusDays(1))));
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> reservationSystem.addReservation(restaurantId, "John Doe", "john@example.com", "+94123456789", 4, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(8, 0))));
//        assertEquals("Reservation must be within the restaurant's open hours", exception.getMessage());
//    }
//
//    @Test
//    void addReservationExceedsCapacity() {
//        String restaurantId = "restaurant-123";
//        when(restaurantManager.getRestaurantById(restaurantId)).thenReturn(new Restaurant(restaurantId, "Test Restaurant", "123 Test St", 10, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.now().plusDays(1))));
//
//        reservationSystem.addReservation(restaurantId, "John Doe", "john@example.com", "+94123456789", 6, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(12, 0)));
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> reservationSystem.addReservation(restaurantId, "Jane Doe", "jane@example.com", "+94123456788", 5, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(12, 30))));
//        assertEquals("Total party size exceeds restaurant capacity", exception.getMessage());
//    }


    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void validateParametersInvalidInputs(Class<Exception> exceptionClass, String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures) {
        restaurantManager = new RestaurantManagerImpl();
        assertThrows(exceptionClass, () ->

                restaurantManager.addRestaurant(name, address, capacity, open, close, closures)
        );
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of(NullPointerException.class, null, "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // null name
                Arguments.of(NullPointerException.class, "Test Restaurant", null, 50, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // null address
                Arguments.of(NullPointerException.class, "Test Restaurant", "123 Test St", 50, null, LocalTime.of(22, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // null open time
                Arguments.of(NullPointerException.class, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), null, Collections.singletonList(LocalDate.of(2025, 1, 1))), // null close time
                Arguments.of(NullPointerException.class, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(22, 0), null), // null closures
                Arguments.of(IllegalArgumentException.class, "Test Restaurant", "123 Test St", -1, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // negative capacity
                Arguments.of(IllegalArgumentException.class, "Test Restaurant", "123 Test St", 50, LocalTime.of(22, 0), LocalTime.of(10, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // close time before open time
                Arguments.of(IllegalArgumentException.class, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(10, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // close time equals open time
                Arguments.of(IllegalArgumentException.class, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(17, 0), Collections.singletonList(LocalDate.of(2025, 1, 1))), // close time less than 8 hours after open time
                Arguments.of(IllegalArgumentException.class, "Test Restaurant", "123 Test St", 50, LocalTime.of(10, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.of(2020, 1, 1))) // closure date in the past
        );
    }


    @Test
    void modifyReservationSuccessful() {
        String restaurantId = "restaurant-123";

        when(restaurantManager.getRestaurant(restaurantId)).thenReturn(new Restaurant(restaurantId, "Test Restaurant", "123 Test St", 50, LocalTime.of(8, 0), LocalTime.of(22, 0), Collections.singletonList(LocalDate.now().plusDays(1))));

        String reservationId = reservationSystem.addReservation(restaurantId, "John Doe", "john@example.com", "+1 (123) 456-7890", 4, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(12, 0)));
        String newReservationId = reservationSystem.modifyReservation(reservationId, restaurantId, "John Doe", "john@example.com", "+1 (123) 456-7890", 4, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(14, 0)));
        assertNotNull(newReservationId);
        assertNotEquals(reservationId, newReservationId);
    }

    @Test
    void cancelReservationSuccessful() {
        String restaurantId = "restaurant-123";
        LocalDate openDate = LocalDate.now().plusDays(2);

        when(restaurantManager.getRestaurant(restaurantId)).thenReturn(new Restaurant(restaurantId, "Test Restaurant", "123 Test St", 50, LocalTime.of(8, 0), LocalTime.of(22, 0), Collections.singletonList(openDate)));

        String reservationId = reservationSystem.addReservation(restaurantId, "John Doe", "john@example.com", "+1 (123) 456-7890", 4, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(12, 0)));
        reservationSystem.cancelReservation(reservationId);
        assertNull(reservationSystem.getReservationById(reservationId));
    }
}