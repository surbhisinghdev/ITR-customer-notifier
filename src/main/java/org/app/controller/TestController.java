//package org.app.controller;
//
//import org.app.entites.Lead;
//import org.app.events.LeadCreatedEvent;
//import org.app.listeners.AdminNotificationListener;
//import org.app.service.EmailService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/test")
//public class TestController {
//    private final AdminNotificationListener adminNotificationListener;
//
//    public TestController(AdminNotificationListener adminNotificationListener) {
//        this.adminNotificationListener = adminNotificationListener;
//    }
//
//    @GetMapping("/async")
//    public String testAsync() {
//        adminNotificationListener.onLeadCreated(new LeadCreatedEvent(new Lead()));
//        return "Request returned immediately!";
//    }
//}
