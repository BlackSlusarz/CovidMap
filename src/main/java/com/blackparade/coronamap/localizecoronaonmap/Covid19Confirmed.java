package com.blackparade.coronamap.localizecoronaonmap;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19Confirmed {


    private static final String URL_GIT = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";


    public List<Point> getCovidData() throws IOException {

        List<Point> points = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String values = restTemplate.getForObject(URL_GIT, String.class);

        StringReader stringReader = new StringReader(values);
        CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

        LocalDateTime dateBeforeFormat = LocalDateTime.now().minusDays(1);
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("M/dd/yy");
        String dateAfterFormat = dateBeforeFormat.format(formatted);

        for (CSVRecord strings : parse){
            double lat = Double.parseDouble(strings.get("Lat"));
            double lon = Double.parseDouble(strings.get("Long"));
            String text = strings.get(dateAfterFormat);
            points.add(new Point(lat, lon, text));


        }
        return points;
    }

}
