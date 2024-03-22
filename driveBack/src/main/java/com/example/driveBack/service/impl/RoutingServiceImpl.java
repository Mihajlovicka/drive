package com.example.driveBack.service.impl;

import com.example.driveBack.exception.ForeignApiRequestException;
import com.example.driveBack.model.Position;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.example.driveBack.service.RoutingService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutingServiceImpl implements RoutingService {
    private final String baseURL = "https://routing.openstreetmap.de/routed-car/route/v1/car/";

    @Override
    public List<Position> getPositionsFromRoute(Position departure, Position stop, Position destination) {
        String responseJson = requestRoute(departure, stop, destination);
        List<Position> positions = extractPositions(responseJson);
        return positions;
    }

    private List<Position> extractPositions(String responseJson) {
        List<Position> positions = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode routeResponse = objectMapper.readTree(responseJson);

            JsonNode coordinates = routeResponse.get("routes").get(0).get("geometry").get("coordinates");

            for (JsonNode coordinate : coordinates) {
                double latitude = coordinate.get(0).asDouble();
                double longitude = coordinate.get(1).asDouble();
                positions.add(new Position(latitude, longitude));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ForeignApiRequestException("Routing api response processing exception.");
        }
        return positions;
    }

    private String requestRoute(Position departure, Position stop, Position destination) {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(makeURL(departure, stop, destination)))
                    .GET()
                    .build();

            response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ForeignApiRequestException("Routing api request exception.");
        }


        return response.body();
    }

    private String makeURL(Position departure, Position stop, Position destination) {
        return baseURL + departure.getLongitude() + "," + departure.getLatitude() +
                ";" + stop.getLongitude() + "," + stop.getLatitude() +
                ";" + destination.getLongitude() + "," + destination.getLatitude() +
                "?geometries=geojson&overview=simplified&steps=false&continue_straight=true";
    }

}
