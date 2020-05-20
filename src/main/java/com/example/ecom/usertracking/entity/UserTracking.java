package com.example.ecom.usertracking.entity;

import com.example.ecom.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "user_tracking", schema = "test")
@NoArgsConstructor
@ToString
public class UserTracking extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userTrackingId;
    private String email;
    private String userNumber;
    private Integer selectedPlan;
    @Embedded
    private AnotherModel auditModel;
    //bi-directional many-to-one association to PartNumber
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "userTracking")
    private List<ContactInfo> contactInfos = new ArrayList<>();

}

