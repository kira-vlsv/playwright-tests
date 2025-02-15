package otus.constants;

import java.util.Arrays;

public enum CourseLevel {
    ANY("Любой уровень"),
    BASIC("Basic"),
    PROFESSIONAL("Professional"),
    ADVANCED("Advanced");

    private final String label;

    CourseLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CourseLevel fromLabel(String label) {
        return Arrays.stream(values())
                .filter(level -> level.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown level: " + label));
    }
}
