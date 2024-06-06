package com.mini.asaas.payment

import org.quartz.JobBuilder
import org.quartz.SimpleScheduleBuilder
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory

import java.util.concurrent.TimeUnit

class JobScheduler {
    static void startScheduler() {
        def scheduler = StdSchedulerFactory.getDefaultScheduler()
        scheduler.start()

        def job = JobBuilder.newJob(BillingJob)
                .withIdentity("billingJob", "group1")
                .build()

        def trigger = TriggerBuilder.newTrigger()
                .withIdentity("billingTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(TimeUnit.DAYS.toSeconds(1) as Integer)
                        .repeatForever())
                .build()

        scheduler.scheduleJob(job, trigger)
    }
}
