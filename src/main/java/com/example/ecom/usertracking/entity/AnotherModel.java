package com.example.ecom.usertracking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class AnotherModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("testName")
    private String testName;
}

