package ExpenseTracker;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConversionAPI {
    public static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        String apiUrl = "http://localhost:5000/convert?amount=" + amount + 
                        "&from_currency=" + fromCurrency + 
                        "&to_currency=" + toCurrency;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response using Gson
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Ensure to get the converted_amount as double
            double convertedAmount = jsonResponse.get("converted_amount").getAsDouble();
            return convertedAmount;

        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 to indicate an error
        }
    }
}
