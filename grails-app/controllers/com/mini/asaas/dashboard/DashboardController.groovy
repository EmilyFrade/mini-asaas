package com.mini.asaas.dashboard


import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_SELLER", "ROLE_ADMIN"])
class DashboardController {

    DashboardService dashboardService

    def index() {
        return dashboardService.getSummary()
    }

}
