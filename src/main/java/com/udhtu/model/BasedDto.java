package com.udhtu.model;

import java.util.Objects;

public class BasedDto<ID> {
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
        BasedDto<?> basedDto = (BasedDto<?>) o;
        return Objects.equals(id, basedDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    // endregion
}
