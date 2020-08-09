package com.example.ecom.hardware.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hardware", schema = "test")
@Setter
@Getter
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID hardwareId;
    private String manufacture;
    private String model;
    private String processor;
    private String memory;
    private String virtualization;
}
