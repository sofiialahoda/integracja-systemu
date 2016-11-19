package pl.pollub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.pollub.service.model.Movie;
import pl.pollub.service.repository.MovieRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component
public class ApplicationMoviesListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MovieRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        init();
    }

    public void init() {
        if (repository.count() == 0) {
            try {
                InputStream stream = getClass().getClassLoader().getResourceAsStream("ids.file");
                Scanner scanner = new Scanner(stream);
                while (scanner.hasNext()) {
                    String id = scanner.nextLine();
                    try {
                        String json = fetch(id);
                        Movie movie = mapper.readValue(json, Movie.class);
                        repository.save(movie);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String fetch(String imdbId) throws Exception {
        String url = "http://www.omdbapi.com/?i=tt" + imdbId + "&plot=full&r=json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}