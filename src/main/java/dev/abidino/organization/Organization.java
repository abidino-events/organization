package dev.abidino.organization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String orgName;

    @Enumerated(EnumType.STRING)
    Status status = Status.ACTIVE;

    @ElementCollection
    List<String> adminList = new ArrayList<>();

    @ElementCollection
    List<String> memberList = new ArrayList<>();

    OrganizationDto toOrganizationDto() {
        return new OrganizationDto(this.id, this.orgName, this.status, this.adminList, this.memberList);
    }
}
