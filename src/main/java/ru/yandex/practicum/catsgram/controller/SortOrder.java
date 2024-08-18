package ru.yandex.practicum.catsgram.controller;

public enum SortOrder {
    ASCENDING, DESCENDING;

    // Преобразует строку в элемент перечисления
    public static SortOrder from(String order) {
        return switch (order.toLowerCase()) {
            case "descending", "desc" -> DESCENDING;
            default -> ASCENDING;
        };
    }
}

