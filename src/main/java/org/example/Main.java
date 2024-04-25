package org.example;
import com.google.gson.Gson;
import io.muserver.*;

import java.util.ArrayList;
import java.util.List;

import Model.Booking;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {

        MuServer server = MuServerBuilder.httpServer()
                .withHttpPort(8080)
                .addHandler(Method.GET, "/", (request, response, pathParams) -> {
                    response.write("Hello, world");
                })
                .addHandler(Method.POST, "/bookings", (request, response, pathParams) -> {
                    String requestBody = request.readBodyAsString();
                    Booking booking = Booking.fromJson(requestBody);

                    //Validate if there are tables available & store in DB

                    bookings.add(booking);
                    System.out.println("Created: " + booking.toString());

                    response.status(201);
                    response.write("Booking created successfully:" + booking.toString());
                })
                .addHandler(Method.GET, "/bookings", (request, response, pathParams) -> {
                    String date = request.query().get("dateTime");

                    // Filter bookings for the specified date
                    List<Booking> bookingsForDay = bookings.stream()
                            .filter(booking -> booking.getDateTime().startsWith(date))
                            .toList();

                    System.out.println("Filtered Bookings Endpoint");
                    for (Booking booking : bookingsForDay) {
                        System.out.println(booking);
                    }

                    Gson gson = new Gson();
                    String jsonArray = gson.toJson(bookingsForDay);

                    // Respond with the filtered bookings
                    response.status(200);
                    response.contentType("application/json");

                    response.write(jsonArray);
                })
                .start();
        System.out.println("Started server at " + server.uri());
    }
}