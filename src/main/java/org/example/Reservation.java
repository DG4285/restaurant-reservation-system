package org.example;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
 * @created 05/01/2025 : 12:13 PM
 * @author DG4285
 ***********************************************/
public class Reservation implements Comparable<Reservation> {
    private static final int DEFAULT_RESERVATION_DURATION_IN_HOURS = 2;
    private final String id;
    private final String restaurantId;
    private String name;
    private String email;
    private String phone;
    private int partySize;
    private LocalDateTime dateTime;
    private final LocalTime startTime;
    private final LocalTime endTime;



    public Reservation(String restaurantId, String name, String email, String phone, int partySize, LocalDateTime dateTime) {
        this.id =  UUID.randomUUID().toString();;
        this.restaurantId = restaurantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.partySize = partySize;
        this.dateTime = dateTime;
        this.startTime = dateTime.toLocalTime();
        this.endTime = this.startTime.plusHours(DEFAULT_RESERVATION_DURATION_IN_HOURS);
    }

    public static LocalTime getEndTimeFrom(LocalDateTime dateTime) {
        return dateTime.toLocalTime().plusHours(DEFAULT_RESERVATION_DURATION_IN_HOURS);
    }

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(Reservation other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    public boolean intersectsWith(Reservation other) {
//        return this.dateTime.plusHours(this.partySize).isAfter(other.dateTime) && other.dateTime.plusHours(other.partySize).isAfter(this.dateTime);
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }

    public boolean intersectsWith(LocalTime startTime, LocalTime endTime) {
        return this.startTime.isBefore(endTime) && startTime.isBefore(this.endTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
