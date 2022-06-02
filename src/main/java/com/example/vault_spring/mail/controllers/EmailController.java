package com.example.vault_spring.mail.controllers;

import com.example.vault_spring.mail.services.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

// TODO: Create template to send email from web app
@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/mail")
    public String send() {
        Context context = new Context();
        context.setVariable("header", "Header");
        context.setVariable("title", "Title");
        context.setVariable("description", "Description");
        String body = templateEngine.process("email_template", context);
        emailSender.sendEmail("bartekpacocha@gmail.com", "Test mail", body);

        return "email_template";
    }

}
