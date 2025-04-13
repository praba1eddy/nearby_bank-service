**# Nearby Bank Service 🏦

This is a Spring Boot microservice that finds **nearby banks within a 10-mile radius** of a given ZIP code.  
It uses **Google Maps Geocoding API** and **Places API** to fetch real-time bank information.

---

## 🚀 How It Works

The service exposes a single REST endpoint that:
- Accepts a ZIP code
- Finds the latitude & longitude via Google Geocoding API
- Fetches nearby banks using Google Places API
- Filters results within 10 miles
- Returns the list of banks with name, address, and distance

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3.x
- REST API
- Google Maps API (Geocoding + Places)
- Maven
- IntelliJ IDEA
- Git & GitHub

---

## 📦 API Endpoint

```http
GET http://localhost:8080/banks/nearby?zip=75201

Setup Instructions
1. Clone the Repository
git clone https://github.com/praba1eddy/nearby_bank-service.git
cd nearby_bank-service
2.Configure Google API Key
In src/main/resources/application.properties, add your API key:
google.api.key=YOUR_GOOGLE_MAPS_API_KEY
Make sure the following APIs are enabled in your Google Cloud Console:
Geocoding API
Places API

Example ZIP Codes to Try
71270 → Ruston, LA
75201 → Dallas, TX
75001 → Addison, TX











---

Would you also like:
- `.gitignore` to clean up your repo (ignore `/target`, `.idea`, etc.)
- `architecture diagram` as an image?
- `business/technical document` to submit to your manager?

Let me know, and I’ll prep those for you too!



























**
