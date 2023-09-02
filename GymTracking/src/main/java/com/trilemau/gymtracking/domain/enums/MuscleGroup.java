package com.trilemau.gymtracking.domain.enums;

public enum MuscleGroup {
    CHEST("CHEST"),
    BACK("BACK"),
    SHOULDERS("SHOULDERS"),
    BICEPS("BICEPS"),
    TRICEPS("TRICEPS"),
    NECK("NECK"),
    TRAPS("TRAPS"),
    QUADS("QUADS"),
    GLUTES("GLUTES"),
    HAMSTRING("HAMSTRING"),
    CALVES("CALVES"),
    FOREARMS("FOREARMS"),
    ABS("ABS");

    private final String description;

    MuscleGroup(String description) {
        this.description = description;
    }
}
