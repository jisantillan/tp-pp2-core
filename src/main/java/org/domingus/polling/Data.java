package org.domingus.polling;

import org.domingus.interfaces.Comparable;

import java.util.List;

public class Data implements Comparable {
    private String name;
    private String url;
    private String date;

    public Data() {
    }

    public Data(String name, String url, String date) {
        this.name = name;
        this.url = url;
        this.date = date;
    }

    @Override
    public List<String> detectChanges(Comparable other) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }
}
