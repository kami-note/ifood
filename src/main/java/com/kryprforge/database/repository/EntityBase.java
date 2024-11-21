package com.kryprforge.database.repository;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == Status.ACTIVE;
    }

    public boolean isInactive() {
        return status == Status.INACTIVE;
    }

    public boolean isDeleted() {
        return status == Status.DELETED;
    }
}
