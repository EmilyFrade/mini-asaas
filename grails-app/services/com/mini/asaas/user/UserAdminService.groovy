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

        if (!hasOtherAdmins(customerId, id)) {
            throw new BusinessException("É necessário ter pelo menos um usuário Admin")
        }

        user.deleted = true
        user.save(failOnError: true)
    }


    public void restore(Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        User user = UserRepository.query([deletedOnly: true, id: id, customerId: customerId]).get()
        if (!user) throw new RuntimeException("Cobrança não encontrada")
        user.deleted = false

        user.save(failOnError: true)
    }

    private boolean hasOtherAdmins(Long customerId, Long userIdToExclude) {
        List<User> userList = UserRepository.query([customerId: customerId]).list()
        return userList.any { it.isAdmin() && it.id != userIdToExclude }
    }
}
