// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.usernamePropertyName = 'email'
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.mini.asaas.user.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.mini.asaas.user.UserRole'
grails.plugin.springsecurity.authority.className = 'com.mini.asaas.user.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.active = true
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.password.algorithm = 'bcrypt'
grails.plugin.springsecurity.auth.loginFormUrl = '/auth/login'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**', filters: 'JOINED_FILTERS']
]

