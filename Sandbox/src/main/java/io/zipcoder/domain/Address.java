package io.zipcoder.domain;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue
    @Column(name="ADDRESS_ID")
    private Long id;

    @Column(name="ADDRESS_1")
    private String address1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

}
