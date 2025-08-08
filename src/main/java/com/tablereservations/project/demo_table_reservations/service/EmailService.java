package com.tablereservations.project.demo_table_reservations.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;


@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void enviarCorreoPlano(String para, String asunto, String contenido) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);
        mailSender.send(mensaje);
    }

    public void enviarEmail(String emailDestino, String mensaje) throws MessagingException {
        final String asunto = "Sabor Noble - Creación de Usuario";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String[] toArray = {emailDestino};
        helper.setTo(toArray);
        helper.setSubject(asunto);
        Context context = new Context();
        context.setVariable("subject", asunto);
        context.setVariable("message", mensaje);
        String htmlContent = templateEngine.process("email-template", context);
        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }
}

