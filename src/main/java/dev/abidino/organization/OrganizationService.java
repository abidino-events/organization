package dev.abidino.organization;

import dev.abidino.organization.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public record OrganizationService(OrganizationRepository organizationRepository) {

    public Organization save(Organization organization) {
        organization.adminList.add(getAuthenticateUser());
        organization.memberList.add(getAuthenticateUser());
        return organizationRepository.save(organization);
    }

    public Organization findById(Long id) {
        return organizationRepository.findById(id).orElseThrow(() -> new BadRequestException("Organization not found"));
    }

    public void delete(Long id) {
        Organization organization = findById(id);

        if (organization.adminList.contains(getAuthenticateUser())) {
            organization.status = Status.PASSIVE;
            organizationRepository.save(organization);
            return;
        }
        throw new BadRequestException("Bu islem icin gerekli yetkiye sahip degilsiniz");
    }

    public boolean isOrganizationAdmin(Organization organization) {
        return organization.adminList.stream().anyMatch(user -> user.equals(getAuthenticateUser()));
    }

    public boolean isOrganizationMember(Organization organization) {
        return organization.memberList.stream().anyMatch(user -> user.equals(getAuthenticateUser()));
    }

    public UserRole getAuthUserRoleInOrganization(Long id) {
        Organization organization = findById(id);
        if (isOrganizationAdmin(organization)){
            return UserRole.ADMIN;
        } else if(isOrganizationMember(organization)){
            return UserRole.MEMBER;
        }
        return UserRole.UNAUTHORIZED;
    }

    String getAuthenticateUser(){
        //TODO : @abidino user bilgilerini aldiginda burayi degistir
        return "user";
    }
}
