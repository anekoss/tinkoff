package edu.hw10.Task1;

import java.util.Objects;

public class GeneratorTestClass {
    private final Integer integer1;
    private final Integer integer2;
    private final Integer integer3;
    private final String string1;
    private final String string2;
    private final Integer integer4;

    public GeneratorTestClass(
        @NotNull Integer integer1,
        @Min(value = -1) Integer integer2,
        @Max(value = 10) Integer integer3,
        @Min(-5) @Max(100) Integer integer4, @NotNull String string1, String string2
    ) {
        this.integer3 = integer3;
        this.string1 = string1;
        this.integer2 = integer2;
        this.integer1 = integer1;
        this.string2 = string2;
        this.integer4 = integer4;
    }

    static GeneratorTestClass create(
        @NotNull Integer integer1,
        @Min(value = -1) Integer integer2,
        @Max(value = 10) Integer integer3,
        @Min(-5) @Max(100) Integer integer4, @NotNull String string1, String string2
    ) {
        return new GeneratorTestClass(integer1, integer2, integer3, integer4, string1, string2);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GeneratorTestClass that = (GeneratorTestClass) o;
        return Objects.equals(integer1, that.integer1) && Objects.equals(integer2, that.integer2) &&
            Objects.equals(integer3, that.integer3) && Objects.equals(string1, that.string1) &&
            Objects.equals(string2, that.string2) && Objects.equals(integer4, that.integer4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integer1, integer2, integer3, string1, string2, integer4);
    }
}
