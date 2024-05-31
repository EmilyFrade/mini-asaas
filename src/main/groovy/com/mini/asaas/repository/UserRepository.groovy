package com.mini.asaas.repository


import com.mini.asaas.user.User
import grails.gorm.transactions.Transactional

@Transactional
class UserRepository {

    public static User findById(Long id) {
        return User.findByIdAndDeleted(id, false)
    }

    public static User findByEmail(String email) {
        return User.findByEmailAndDeleted(email, false)
    }

}
