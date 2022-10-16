package dev.abidino.organization;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
record OrganizationController(OrganizationService organizationService) {

    @PostMapping
    OrganizationDto save(@RequestBody OrganizationDto organizationDto) {
        Organization savedOrganization = organizationService.save(organizationDto.toOrganization());
        return savedOrganization.toOrganizationDto();
    }

    @GetMapping("/{id}")
    OrganizationDto get(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);
        return organization.toOrganizationDto();
    }

    @GetMapping("/{id}/user-role")
    UserRole getAuthUserRoleInOrganization(@PathVariable Long id) {
        return organizationService.getAuthUserRoleInOrganization(id);
    }

    @GetMapping("/{id}/")
    OrganizationDto isOrganizationAdmin(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);
        return organization.toOrganizationDto();
    }

}
