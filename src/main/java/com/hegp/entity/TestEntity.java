package com.hegp.entity;

import com.hegp.core.jpa.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author hgp
 * @date 20-5-15
 */
@Entity
@Table(name = "sys_test_entity")
public class TestEntity extends IdEntity {
    private String name;
    private Integer age;
    private Float floatNumber;
    private Double doubleNumber;
    private String address;
    public TestEntity() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getFloatNumber() {
        return floatNumber;
    }

    public void setFloatNumber(Float floatNumber) {
        this.floatNumber = floatNumber;
    }

    public Double getDoubleNumber() {
        return doubleNumber;
    }

    public void setDoubleNumber(Double doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
