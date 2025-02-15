package otus.constants;

import java.util.Arrays;

public enum CourseCategory {
    ALL("Все направления"),
    PROGRAMMING("Программирование"),
    ARCHITECTURE("Архитектура"),
    DATA_SCIENCE("Data Science"),
    INFRASTRUCTURE("Инфраструктура"),
    GAMEDEV("GameDev"),
    SECURITY("Безопасность"),
    MANAGEMENT("Управление"),
    ANALYTICS("Аналитика и анализ"),
    TESTING("Тестирование"),
    CORPORATE("Корпоративные курсы"),
    IT_WITHOUT_PROGRAMMING("IT без программирования"),
    OTUS_KIDS("OTUS Kids");

    private final String label;

    CourseCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CourseCategory fromLabel(String label) {
        return Arrays.stream(values())
                .filter(c -> c.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown category: " + label));
    }
}

