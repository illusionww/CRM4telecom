package com.crm4telecom.mail;

import com.crm4telecom.ejb.GetManagerLocal;
import com.crm4telecom.jpa.Order;
import com.crm4telecom.jpa.OrderProcessing;
import java.io.StringWriter;
import java.util.*;
import javax.ejb.EJB;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class MailManager {

    private final String from = "crm4telecom@gmail.com";
    private final String password = "crm4telecom2Q";

    private final Boolean auth = true;
    private final Boolean startTLS = true;
    private final String host = "smtp.gmail.com";
    private final Integer port = 587;
            
    public void statusChangedEmail(Order order, List<OrderProcessing> steps) {
        String subject = "Order #" + order.getOrderId();

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("directive.set.null.allowed", true);
        ve.init();
        Template t = ve.getTemplate("templates/mailtemplate.vm");

        VelocityContext context = new VelocityContext();
        context.put("firstName", order.getCustomer().getFirstName());
        context.put("lastName", order.getCustomer().getLastName());
        context.put("orderId", order.getOrderId());
        context.put("status", order.getStatus());
        context.put("steps", steps);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        send(order.getCustomer().getEmail(), subject, writer.toString());
    }

    public void send(String to, String subject, String text) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.auth", auth.toString());
        properties.setProperty("mail.smtp.starttls.enable", startTLS.toString());
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port.toString());

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("illusionww@gmail.com"));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setContent(text, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message \"" + subject + "\" to " + to);
        } catch (MessagingException e) {
            System.err.println(e);
        }
    }
}
