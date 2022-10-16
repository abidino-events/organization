package dev.abidino.organization;

import java.util.List;

public record OrganizationDto(Long id,
                              String orgName,

                              Status status,
                              List<String> adminList,
                              List<String> memberList) {

    Organization toOrganization() {
        Organization organization = new Organization();
        organization.id = this.id;
        organization.orgName = this.orgName;
        organization.status = this.status;
        organization.adminList = this.adminList;
        organization.memberList = this.memberList;
        return organization;
    }
}
