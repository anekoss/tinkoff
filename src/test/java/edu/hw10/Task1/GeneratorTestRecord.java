package edu.hw10.Task1;

public record GeneratorTestRecord(String string1, @Min(100) Integer integer1,
                                  @Min(10) Integer integer2, @NotNull String string2) {
}
