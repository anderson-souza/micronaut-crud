package com.aps.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class DefaultEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
