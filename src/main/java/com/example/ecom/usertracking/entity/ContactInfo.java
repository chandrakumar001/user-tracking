package com.example.ecom.usertracking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "contact_info", schema = "test")
@NoArgsConstructor
@ToString
public class ContactInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID contactInfoId;

    private String firstName;
    private String lastName;
    private String phone;
    //bi-directional many-to-one association to PartcvDetail
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_tracking_id")
    private UserTracking userTracking;
}

