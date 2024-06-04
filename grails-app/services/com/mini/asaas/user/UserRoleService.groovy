package com.mini.asaas.user

import grails.gorm.transactions.Transactional

@Transactional
class UserRoleService {

    public void assignAUserRole(RoleAuthority roleAuthority, User user) {

        if (roleAuthority.isAdmin()) {
            addAllUserRoles(user)
            return
        }

        Role role = Role.findByAuthority(roleAuthority.getAuthority())
        if (!role) throw new RuntimeException("Função de usuário não encontrada")
        removeAllUserRolesExceptThatOne(user, role)

        if (user.hasRole(role)) return

        UserRole userRole = new UserRole(user: user, role: role)
        userRole.save(failOnError: true)
    }

    private void addAllUserRoles(User user) {
        for (RoleAuthority authority in RoleAuthority.values()) {
            Role role = Role.findByAuthority(authority.getAuthority())
            if (!role) throw new RuntimeException("Função de usuário não encontrada")
            if (user.hasRole(role)) continue
            new UserRole(user: user, role: role).save(failOnError: true)
        }
    }

    private void removeAllUserRolesExceptThatOne(User user, Role role) {
        Set<Role> roles = user.getAuthorities()
        for (Role r : roles) {
            if (r.authority == role.authority) continue
            UserRole.findByUserAndRole(user, r).delete(failOnError: true)
        }
    }
}
