package org.example;

import java.time.LocalDateTime;

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
 * @created 05/01/2025 : 10:46 AM
 * @author DG4285
 ***********************************************/
public interface ReservationSystem {
    String addReservation(String restaurantId, String name, String email, String phone, int partySize, LocalDateTime dateTime);
    String modifyReservation(String reservationId,String restaurantId, String name, String email, String phone, int partySize, LocalDateTime dateTime);
    void cancelReservation(String reservationId);

    Object getReservationById(String reservationId);
}
