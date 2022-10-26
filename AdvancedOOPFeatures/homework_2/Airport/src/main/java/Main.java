package main.java;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        findPlanesLeavingInTheNextTwoHours(Airport.getInstance());
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        return airport.getTerminals().stream().flatMap(t -> t.getFlights().stream()
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
                .filter(f -> convertDateByLocalDate(f.getDate()).isBefore(LocalDateTime.now().plusHours(2)) &&
                        convertDateByLocalDate(f.getDate()).isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
    public static LocalDateTime convertDateByLocalDate (Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}