package labb2.komplexakomponenter;

import java.util.ArrayList;

public class Shades {
    ArrayList<String> colors;
    String name;

    public Shades(String name) {
        this.name = name;
        this.colors = new ArrayList();
    }

    public void addColor(String name) {
        this.colors.add(name);
    }
}
