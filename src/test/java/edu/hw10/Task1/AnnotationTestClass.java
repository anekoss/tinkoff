package edu.hw10.Task1;

public class AnnotationTestClass {

    public AnnotationTestClass(
        @NotNull @Min(value = 10) @Max(value = 15) Integer integer1,
        @Min(value = -1) Long aLong1,
        @Max(value = -1) Long aLong2,
        @Min(1) @Max(100) Integer integer2, @NotNull String string1, String string2
    ) {
    }


}
