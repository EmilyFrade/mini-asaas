package spring

import com.mini.asaas.email.SendGridSendEmailService
import com.mini.asaas.user.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    sendEmailService(SendGridSendEmailService)
}
