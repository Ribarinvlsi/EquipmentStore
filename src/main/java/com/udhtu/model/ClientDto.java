package com.udhtu.model;

import java.util.Objects;

public class ClientDto
        extends BasedDto<Long> {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    // region get, set
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    // endregion

    // region equals, hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(firstName, clientDto.firstName) && Objects.equals(lastName, clientDto.lastName) && Objects.equals(phoneNumber, clientDto.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, phoneNumber);
    }

    // endregion
}
