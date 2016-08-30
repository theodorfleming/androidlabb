package labb2.komplexakomponenter;

import java.util.ArrayList;

public class Model {
    ArrayList<Shades> headline;

    public Model() {
        this.headline = new ArrayList();
        Shades light = new Shades("Light");
        light.addColor("Yellow");
        light.addColor("White");
        Shades medium = new Shades("Medium");
        medium.addColor("Blue");
        medium.addColor("Green");
        medium.addColor("Red");
        Shades dark = new Shades("Dark");
        dark.addColor("Black");
        dark.addColor("Red");
        this.headline.add(light);
        this.headline.add(medium);
        this.headline.add(dark);
    }

    public ArrayList<Shades> getHeadline() {
        return this.headline;
    }

    public int getGroupCount() {
        return this.headline.size();
    }

    public int getChildrenCount(int groupPosition) {
        return this.headline.get(groupPosition).colors.size();
    }

    public String getGroupName(int groupPosition) {
        return this.headline.get(groupPosition).name;
    }

    public String getChildName(int groupPosition, int childPosition) {
        return this.headline.get(groupPosition).colors.get(childPosition);
    }
}