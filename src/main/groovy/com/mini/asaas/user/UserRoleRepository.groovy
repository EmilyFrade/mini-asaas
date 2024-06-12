package com.mini.asaas.user

import com.mini.asaas.repository.Repository
import grails.compiler.GrailsCompileStatic
import org.grails.datastore.mapping.query.api.BuildableCriteria

@GrailsCompileStatic
class UserRoleRepository implements Repository<UserRole, UserRoleRepository> {

    Boolean domainHasSoftDelete = false

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("userId")) {
                eq("user.id", Long.valueOf(search.userId as String))
            }

            if (search.containsKey("customerId")) {
                createAlias("user", "user")
                eq("user.customer.id", Long.valueOf(search.customerId as String))
            }

            if (search.containsKey("roleAuthority")) {
                createAlias("role", "role")
                eq("role.authority", search.roleAuthority)
            }
        }
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return UserRole.createCriteria()
    }

    @Override
    List<String> listAllowedFilters() {
        return [
            "userId",
            "customerId",
            "roleAuthority"
        ]
    }
}
