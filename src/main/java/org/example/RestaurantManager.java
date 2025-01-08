package org.example;

import java.time.LocalDate;
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
 * @created 05/01/2025 : 8:37 AM
 * @author DG4285
 ***********************************************/
public interface RestaurantManager {

    String addRestaurant(String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures);

    String modifyRestaurant(String restaurantId, String name, String address, int capacity, LocalTime open, LocalTime close, List<LocalDate> closures);

    void removeRestaurant(String restaurantId);

//    Restaurant getRestaurantById(String restaurantId);

    Restaurant getRestaurant(String restaurantId);

}
