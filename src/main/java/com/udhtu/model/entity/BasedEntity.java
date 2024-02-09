package com.udhtu.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class BasedEntity<ID extends Number> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    // region get, set
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    // endregion

    // region equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasedEntity<?> that = (BasedEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    // endregion
}
