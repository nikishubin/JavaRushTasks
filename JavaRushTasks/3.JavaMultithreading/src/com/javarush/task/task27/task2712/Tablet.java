package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order customerChoise = null;
        try {
            customerChoise = new Order(this);
            ConsoleHelper.writeMessage(customerChoise.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        if (!customerChoise.isEmpty()) {
            setChanged();
            notifyObservers(customerChoise);
            try {
                AdvertisementManager advertisementManager = new AdvertisementManager(customerChoise.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            } catch (NoVideoAvailableException exc) {
                logger.log(Level.INFO, "No video is available for the order " + customerChoise);
            }
        }
        return customerChoise;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
