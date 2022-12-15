package fr.inextenso.pratibook.model;

import java.util.stream.Stream;

public enum Disponibilite {
    IDLE(0),
    DISPONIBLE(1),
    EMPRUNTE(2),
    PERDU(3),
    SUPPRIME(4);

    private final int value;

    Disponibilite(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Disponibilite of(int value) {
        return Stream.of(Disponibilite.values())
                .filter(p -> p.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
