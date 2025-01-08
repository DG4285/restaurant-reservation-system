package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
 * @created 05/01/2025 : 10:22 AM
 * @author DG4285
 ***********************************************/
public class RestaurantManagerImpl implements RestaurantManager {


    private final Map<String, Restaurant> restaurants = new HashMap<>();

    @Override
    public String addRestaurant(String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures) {
        validateInputs(name, address, capacity, open, close, closures);

        String restaurantId = UUID.randomUUID().toString();
        Restaurant restaurant = new Restaurant(restaurantId, name, address, capacity, open, close, closures);
        restaurants.put(restaurantId, restaurant);
        return restaurantId;
    }

    @Override
    public String modifyRestaurant(String restaurantId, String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures) {
        removeRestaurant(restaurantId);
        return addRestaurant(name, address, capacity, open, close, closures);
    }

    @Override
    public void removeRestaurant(String restaurantId) {
        restaurants.remove(restaurantId);
    }

//    @Override
//    public Restaurant getRestaurantById(String restaurantId) {
//        return restaurants.get(restaurantId);
//    }

    @Override
    public Restaurant getRestaurant(String restaurantId) {
        return restaurants.get(restaurantId);
    }

//    public void validateParameters(String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures) {
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be null or empty");
//        }
//        if (address == null || address.isEmpty()) {
//            throw new IllegalArgumentException("Address cannot be null or empty");
//        }
//        if (capacity <= 0) {
//            throw new IllegalArgumentException("Capacity must be greater than zero");
//        }
//        if (open == null) {
//            throw new IllegalArgumentException("Open time cannot be null");
//        }
//        if (close == null) {
//            throw new IllegalArgumentException("Close time cannot be null");
//        }
//        if (!open.isBefore(close)) {
//            throw new IllegalArgumentException("Open time must be before close time");
//        }
//        if (closures != null) {
//            for (LocalDate closure : closures) {
//                if (closure.isBefore(LocalDate.now())) {
//                    throw new IllegalArgumentException("Closure dates cannot be in the past");
//                }
//            }
//        }
//    }

    private void validateInputs(String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures) {
        if (name == null) {
            throw new NullPointerException("Name parameter cannot be null");
        }
        if (address == null) {
            throw new NullPointerException("Address parameter cannot be null");
        }
        if (open == null) {
            throw new NullPointerException("Open time parameter cannot be null");
        }
        if (close == null) {
            throw new NullPointerException("Close time parameter cannot be null");
        }
        if (closures == null) {
            throw new NullPointerException("Closures parameter cannot be null");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive number");
        }
        if (open.isAfter(close) || open.plusHours(8).isAfter(close)) {
            throw new IllegalArgumentException(String.format("The restaurant must be open for at least 8 hours and the close time must be strictly after open: open=%s, close=%s",
                    open, close));
        }
        if (closures.stream().anyMatch(date -> date.isBefore(LocalDate.now()))) {
            throw new IllegalArgumentException(String.format("Closures must be in the future: closures=%s", closures));
        }
    }


}
