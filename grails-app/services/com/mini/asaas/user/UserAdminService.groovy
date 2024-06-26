package com.mini.asaas.user

import com.mini.asaas.exceptions.BusinessException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class UserAdminService {

    SpringSecurityService springSecurityService

    public List<User> list() {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        return UserRepository.query([customerId: customerId, includeDeleted: true]).list()
    }

    public void delete(Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        User user = UserRepository.query([id: id, customerId: customerId]).get()

        if (!user) throw new RuntimeException("Usuário não encontrado")

        if (user.isAdmin() && !user.isAdminButNotUniqueAdminOfCustomer()) {
            throw new BusinessException("É necessário ter pelo menos um usuário Admin")
        }

        user.deleted = true
        user.save(failOnError: true)
    }


    public void restore(Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        User user = UserRepository.query([deletedOnly: true, id: id, customerId: customerId]).get()
        if (!user) throw new RuntimeException("Usuário não encontrado")
        user.deleted = false

        user.save(failOnError: true)
    }

    public User getUserIfAdminOfCustomer(Long id, User user) {
        if (user.isAdmin()) user = UserRepository.query([customerId: user.customerId, id: id]).get()
        return user
    }
}
