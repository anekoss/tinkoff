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
    public static final String NAME_FIELD_NAME = "name";
    public static final String HEIGHT_FIELD_NAME = "height";
    public static final String WEIGHT_FIELD_NAME = "weight";
    public static final String SEX_FIELD_NAME = "sex";
    public static final String AGE_FIELD_NAME = "age";
    public static final String TYPE_FIELD_NAME = "type";

    public enum Type {
        CAT(4), DOG(4), BIRD(2), FISH(0), SPIDER(8);
        private final int paws;

        Type(int paws) {
            this.paws = paws;
        }

        public int getPaws() {
            return paws;
        }
    }

    public enum Sex {
        M, F
    }

}
