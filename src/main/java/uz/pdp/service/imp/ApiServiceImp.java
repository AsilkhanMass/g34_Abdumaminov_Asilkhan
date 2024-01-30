package uz.pdp.service.imp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import uz.pdp.service.ApiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ApiServiceImp implements ApiService {
    @Override
    public String dayInfo(Integer month, Integer day) {
        HttpClient httpClient = HttpClients.createDefault();
        String apiUrl = String.format("https://numbersapi.p.rapidapi.com/%d/%d/date?fragment=true&json=true", month, day);

        try {
            HttpGet request = new HttpGet(apiUrl);
            request.addHeader("X-RapidAPI-Key", "120c5e702bmsh02f92eb539e196bp1e9644jsnaaf379e619f7");
            request.addHeader("X-RapidAPI-Host", "numbersapi.p.rapidapi.com");

            HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;

            StringBuilder result = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (
                IOException e) {
            System.out.println("sd");
            e.printStackTrace();
        }
        return "Something went wrong";
    }
}
