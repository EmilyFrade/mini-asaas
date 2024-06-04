package com.mini.asaas.user

import com.mini.asaas.base.BaseEntity
import com.mini.asaas.customer.Customer
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = "email")
@ToString(includes = "email", includeNames = true, includePackage = false)
class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1

    String name

    String email

    String password

    Customer customer

    boolean enabled = true

    boolean accountExpired = false

    boolean accountLocked = false

    boolean passwordExpired = false

    public Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    public Boolean hasRole(Role role) {
        return this.getAuthorities().contains(role)
    }

    public Boolean isAdmin() {
        Role adminRole = Role.findByAuthority(RoleAuthority.ADMIN.getAuthority())

        return this.hasRole(adminRole)
    }

    public RoleAuthority getMainAuthority() {
        if (this.isAdmin()) return RoleAuthority.ADMIN
        Role mainRole = this.getAuthorities().stream().findFirst().get()

        return RoleAuthority.parseFromString(mainRole.authority)
    }

    static constraints = {
        name nullable: true, blank: false
        email blank: false, email: true
        password blank: false, password: true
        customer nullable: true
    }

    static mapping = {
        table '`user`'
        password column: '`password`'
        email unique: true
    }
}
