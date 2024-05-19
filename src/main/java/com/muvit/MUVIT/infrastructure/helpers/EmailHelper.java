package com.muvit.MUVIT.infrastructure.helpers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.muvit.MUVIT.util.enums.RolEnum;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class EmailHelper {

    @Autowired 
    private final JavaMailSender mailSender;

    // Enviar el EMAIL

    public void sendEmial(
        String destinity,
        String name,
        String username,
        RolEnum rolenum
    ){
        // Crear el mensaje de tipo HTML
        MimeMessage message = mailSender.createMimeMessage();

        // Leer el HTML
        String htmlString = this.readHTMLTemplate(name, username, rolenum);

        try {
            message.setFrom(new InternetAddress());
            message.setSubject("Confirmacion de cuenta MUVIT");

            message.setRecipients(MimeMessage.RecipientType.TO, destinity);
            message.setContent(htmlString, MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);
            System.out.println("Email enviado");
        } catch (Exception e) {
            System.out.println("ERROR  no se puedo enviar el email " + e.getMessage());

        }
    }

    // Leer el HTML
    private String readHTMLTemplate(String name, String username, RolEnum rolEnum){
        // Obtener la URL del html
        Path path;

        switch (rolEnum) {
            case Driver:
                path = Paths.get("src/main/resources/emails/email-driver.html");
                break;
            case User:
                path = Paths.get("src/main/resources/emails/email.html");
                break;
            default:
            throw new RuntimeException();    
        }
            try(var lines = Files.lines(path)){

            // Inyectar los valores (nombres) al HTML
            var html = lines.collect(Collectors.joining());

            return html
            .replace("{name}", name)
            .replace("{username}", username);


        }// En caso de caer, notificar y lanzar el error
        catch (Exception e) {
            System.out.println("No se logro leer el HTML");
            throw new RuntimeException();
        }
        
    }
}

