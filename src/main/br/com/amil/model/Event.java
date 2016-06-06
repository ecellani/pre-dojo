package br.com.amil.model;

import java.time.LocalDateTime;

public interface Event<E> extends Comparable<E> {

    LocalDateTime getDateTime();
}
