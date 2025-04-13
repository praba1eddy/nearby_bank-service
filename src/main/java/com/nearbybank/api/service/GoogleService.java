package com.nearbybank.api.service;

import com.nearbybank.api.model.BankInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleService {

    @Value("${google.api.key}")
    private String apiKey;

    public List<BankInfo> getNearbyBanks(String zip) {
        RestTemplate restTemplate = new RestTemplate();

        // Step 1: Get coordinates from ZIP code using Geocoding API
        String geoUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + zip + "&key=" + apiKey;
        String geoResponse = restTemplate.getForObject(geoUrl, String.class);
        System.out.println("🔍 Geocoding Response: " + geoResponse);

        JSONObject geoJson = new JSONObject(geoResponse);
        JSONArray geoResults = geoJson.getJSONArray("results");

        if (geoResults.isEmpty()) {
            System.out.println("❌ No results from Geocoding API");
            return new ArrayList<>();
        }

        JSONObject location = geoResults.getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");

        double zipLat = location.getDouble("lat");
        double zipLng = location.getDouble("lng");

        // Step 2: Call Places API to get nearby banks
        String placesUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + zipLat + "," + zipLng +
                "&radius=16093&type=bank&key=" + apiKey;
        String placesResponse = restTemplate.getForObject(placesUrl, String.class);
        System.out.println("🏦 Places API Response: " + placesResponse);

        JSONObject placesJson = new JSONObject(placesResponse);
        JSONArray placesResults = placesJson.getJSONArray("results");

        List<BankInfo> bankList = new ArrayList<>();

        for (int i = 0; i < placesResults.length(); i++) {
            JSONObject bank = placesResults.getJSONObject(i);
            String name = bank.optString("name", "Unknown Bank");
            String address = bank.optString("vicinity", "Unknown Address");

            JSONObject bankLocation = bank.getJSONObject("geometry").getJSONObject("location");
            double bankLat = bankLocation.getDouble("lat");
            double bankLng = bankLocation.getDouble("lng");

            double distance = calculateDistance(zipLat, zipLng, bankLat, bankLng);

            if (distance <= 10.0) {
                bankList.add(new BankInfo(name, address, distance));
            }
        }

        return bankList;
    }

    // Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 3959;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round(EARTH_RADIUS * c * 10.0) / 10.0; // rounded to 1 decimal
    }
}
