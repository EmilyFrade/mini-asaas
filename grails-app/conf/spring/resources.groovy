package spring

import com.mini.asaas.email.SendGridEmailService
import com.mini.asaas.user.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    emailService(SendGridEmailService)
}
