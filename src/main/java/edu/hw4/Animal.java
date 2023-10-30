package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites

) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public String getNameText() {
        return "name";
    }
    public String getTypeText() {
        return "type";
    }

    public String getAgeText() {
        return "age";
    }
    public String getWeightText() {
        return "weight";
    }

    public String getHeightText() {
        return "age";
    }
    public String getSexText() {
        return "sex";
    }

    public String getBitesText() {
        return "bites";
    }
}
