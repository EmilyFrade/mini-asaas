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

    public List<Role> getAuthorities() {
        return UserRoleRepository.query([userId: this.id]).column("role").list() as List<Role>
    }

    public Boolean hasRole(Role role) {
        return UserRoleRepository.query([userId: this.id, roleAuthority: role.authority]).exists()
    }

    public Boolean isAdmin() {
        return UserRoleRepository.query([userId: this.id, roleAuthority: RoleAuthority.ADMIN.getAuthority()]).exists()
    }

    public Boolean isAdminButNotUniqueAdminOfCustomer() {
        Integer countAdminUserRoleOfCustomer = UserRoleRepository.query([
            customerId   : this.customerId,
            roleAuthority: RoleAuthority.ADMIN.getAuthority()
        ]).count()
        return this.isAdmin() && countAdminUserRoleOfCustomer > 1
    }

    public RoleAuthority getRoleAuthority() {
        if (this.isAdmin()) return RoleAuthority.ADMIN
        return RoleAuthority.SELLER
    }

    static constraints = {
        name nullable: true, blank: false
        email blank: false, email: true
        password blank: false, password: true
    }

    static mapping = {
        table '`user`'
        password column: '`password`'
        email unique: true
    }
}
