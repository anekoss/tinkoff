package edu.hw4.Animal;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites

) {
    private static final int COUNT_CAT_DOG_PAWS = 4;
    private static final int COUNT_BIRD_PAWS = 2;
    private static final int COUNT_FISH_PAWS = 0;
    private static final int COUNT_SPIDER_PAWS = 8;

    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> COUNT_CAT_DOG_PAWS;
            case BIRD -> COUNT_BIRD_PAWS;
            case FISH -> COUNT_FISH_PAWS;
            case SPIDER -> COUNT_SPIDER_PAWS;
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
        return "height";
    }

    public String getSexText() {
        return "sex";
    }
}
