/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.emailService;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Builds an Email Service capable of sending normal email to a given SMTP Host.
 * Currently <b>send()</b> can only works with text.
 */
public class EmailService {

    // Server mail user & pass account
    private String user = "2damigi1@gmail.com";
    private String pass = "serv1doR";

    // DNS Host + SMTP Port
    private String smtp_host = null;
    private int smtp_port = 0;

    @SuppressWarnings("unused")
    private EmailService() {
    }

    /**
     * Builds the EmailService.
     *
     * @param user User account login
     * @param pass User account password
     * @param host The Server DNS
     * @param port The Port
     */
    public EmailService(String user, String pass, String host, int port) {
        this.user = user;
        this.pass = pass;
        this.smtp_host = host;
        this.smtp_port = port;
    }

    /**
     * Sends the given <b>text</b> from the <b>sender</b> to the
     * <b>receiver</b>. In any case, both the <b>sender</b> and <b>receiver</b>
     * must exist and be valid mail addresses. The sender, mail's FROM part, is
     * taken from this.user by default<br/>
     * <br/>
     *
     * Note the <b>user</b> and <b>pass</b> for the authentication is provided
     * in the class constructor. Ideally, the <b>sender</b> and the <b>user</b>
     * coincide.
     *
     * @param receiver The mail's TO part
     * @param subject The mail's SUBJECT
     * @param text The proper MESSAGE
     * @throws MessagingException Is something awry happens
     *
     */
    public void sendMail(String receiver, String subject, String text) throws MessagingException {

        // Mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtp_host);
        properties.put("mail.smtp.port", smtp_port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", smtp_host);
        properties.put("mail.imap.partialfetch", false);

        // Authenticator knows how to obtain authentication for a network connection.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        // MIME message to be sent
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver)); // Ej: receptor@gmail.com
        message.setSubject(subject); // Asunto del mensaje

        // A mail can have several parts
        Multipart multipart = new MimeMultipart();

        // A message part (the message, but can be also a File, etc...)
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(text, "text/html");
        multipart.addBodyPart(mimeBodyPart);

        // Adding up the parts to the MIME message
        message.setContent(multipart);

        // And here it goes...
        Transport.send(message);
    }

    /**
     * Method that send to thge user e-mail his/her new password.
     *
     * @param nuevaContraseña The new password of the user.
     * @param mail The user e-mail where the password would be elivered.
     */
    public static void mandarEmail(String nuevaContraseña, String mail) {
        //String nuevaContraseña = contraseña;
        EmailService emailService = new EmailService("2damigi1@gmail.com",
                "serv1doR", "smtp.gmail.com", 465);
        try {
            emailService.sendMail(mail, "DrPlant S.L.",
                    "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <title>TODO supply a title</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n "
                    + "        <style type=\"text/css\">\n"
                    + "           #contenedor { \n"
                    + "                background-color: lightgray;               \n"
                    + "                padding: 5% 5% 5% 5%;\n"
                    + "                text-align: center;\n"
                    + "                display: block;"
                    + "            }\n"
                    + "            body{\n"
                    + "                padding-left: 5%;\n"
                    + "                padding-right: 5%;\n"
                    + "            }\n"
                    + "        </style>"
                    + "    </head>\n"
                    + "    <body> \n"
                    + "        <div id=\"contenedor\">\n"
                    + "            <img alt=\"No funciono\" src=\"https://cdn.dribbble.com/users/2068059/screenshots/4216858/science_plant_logo.png?compress=1&resize=200x150\"/>\n"
                    + "            <h1>Dr. Plant S.L. le informa de que su nueva contraseña es:    </h1>\n"
                    + "            <h2>" + nuevaContraseña + "</h2>\n"
                    + "            Gracias por confiar en nosotros :)\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "");
            System.out.println("Ok, mail sent!");
        } catch (MessagingException e) {
            System.out.println("Doh! " + e.getMessage());
        }
    }
}
