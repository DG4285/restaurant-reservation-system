package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
 * @created 05/01/2025 : 10:23 AM
 * @author DG4285
 ***********************************************/
public class Restaurant {
    private final String id;
    private String name;
    private String address;
    private int capacity;
    private LocalTime open;
    private LocalTime close;
    private List<LocalDate> closures;

    public Restaurant(String id, String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.open = open;
        this.close = close;
        this.closures = closures;
    }

    public boolean isReservationDateTimeValid(LocalDateTime date, int durationInHours) {
        if (closures.contains(date)) {
            return false;
        }
        LocalTime reservationStart = LocalTime.of(9, 0);
        LocalTime reservationEnd = reservationStart.plusHours(durationInHours);
        return open.isBefore(reservationStart) && close.isAfter(reservationEnd);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalTime getOpen() {
        return open;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }

    public List<LocalDate> getClosures() {
        return closures;
    }

    public void setClosures(List<LocalDate> closures) {
        this.closures = closures;
    }
}
