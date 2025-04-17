package model;

public class Pokemon {
    private String name;
    private String type;
    private int height;
    private int weight;

    public Pokemon(String name, String type, int height, int weight) {
        this.name = name;
        this.type = type;
        this.height = height;
        this.weight = weight;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getHeight() { return height; }
    public int getWeight() { return weight; }
}
