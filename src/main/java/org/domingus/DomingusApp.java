package org.domingus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.domingus.app.Notifier;
import org.domingus.config.DomingusConfiguration;
import org.domingus.polling.*;
import java.io.*;

public class DomingusApp {

    private static final String CONFIG_FILE_PROPERTY = "configFile";

    public static void main(String[] args) throws InterruptedException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        DomingusConfiguration config = mapper.readValue(System.getProperty(CONFIG_FILE_PROPERTY), DomingusConfiguration.class);

        Notifier notifier = new Notifier();

        ChangeDetector changeDetector = new ChangeDetector();
        changeDetector.addObserver(notifier);

        VersionManager versionsManager = new VersionManager(changeDetector);

        DataFetcher dataFetcher = new DataFetcher(versionsManager, config.getDefaultData());

        Timer timer = new Timer(config.getTimerInterval());
        timer.addObserver(dataFetcher);
        timer.start();
    }

    private static DomingusConfiguration opcionDos() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(System.getProperty(CONFIG_FILE_PROPERTY), DomingusConfiguration.class);
    }

    private static DomingusConfiguration opcionUno(String arg) throws IOException {
        File file = new File(arg);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, DomingusConfiguration.class);
    }
}