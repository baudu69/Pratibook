package fr.inextenso.pratibook.model;

import java.util.stream.Stream;

public enum Etat {
	NEUF(0),
	BON(1),
	ABIME(2),
	REPARATION(3);

	private final int value;

	Etat(int value) {
		this.value = value;
	}

	public static Etat of(int value) {
		return Stream.of(Etat.values())
				.filter(p -> p.getValue() == value)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public int getValue() {
		return value;
	}
}
