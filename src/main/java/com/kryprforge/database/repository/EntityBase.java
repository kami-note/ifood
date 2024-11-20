package com.kryprforge.database.repository;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 0;
    public static final int STATUS_DELETED = -1;

    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == STATUS_ACTIVE;
    }

    public boolean isInactive() {
        return status == STATUS_INACTIVE;
    }

    public boolean isDeleted() {
        return status == STATUS_DELETED;
    }
}
