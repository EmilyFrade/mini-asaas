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

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        name blank: false
        email blank: false, email: true
        password blank: false, password: true
    }

    static mapping = {
        table '`user`'
        password column: '`password`'
        email unique: true
    }
}
