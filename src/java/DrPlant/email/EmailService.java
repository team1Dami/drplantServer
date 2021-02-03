/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.email;

import DrPlant.encryption.PrivadaEmail;
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

    PrivadaEmail priv = new PrivadaEmail();
    //private static final ResourceBundle EmailFile = ResourceBundle.getBundle("DrPlant.email.email");
    // Server mail user & pass account

    private String user = /*priv.descifrarTexto("correo.txt")*/"2damigi1@gmail.com";
    private String pass = /*priv.descifrarTexto("contraseña.txt")*/"serv1doR";

    // DNS Host + SMTP Port
    private String smtp_host = "smtp.gmail.com";
    private int smtp_port = 465;

    
    @SuppressWarnings("unused")
    public EmailService() {
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
     * @param nuevaContraseña The new e-mail
     * @throws MessagingException Is something awry happens
     *
     */
    public void sendMail(String receiver, String nuevaContraseña) throws MessagingException {
        //user= priv.cifrarTexto(new String(priv.fileReader("Z:\\2DAMi\\Reto2\\SERVIDOR\\drplantServer\\src\\java\\DrPlant\\encryption\\RSA_Private.key")), "serv1doR");
        //System.out.println(priv.descifrarTexto("Z:\\2DAMi\\Reto2\\SERVIDOR\\drplantServer\\src\\java\\DrPlant\\email\\contraseña.txt")+priv.descifrarTexto("Z:\\2DAMi\\Reto2\\SERVIDOR\\drplantServer\\src\\java\\DrPlant\\email\\correo.txt"));
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
        message.setSubject("Cambio de contraseña"); // Asunto del mensaje

        // A mail can have several parts
        Multipart multipart = new MimeMultipart();

        // A message part (the message, but can be also a File, etc...)
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent("<!DOCTYPE html>\n"
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
                + "            <img alt=\"Logo Dr. Plant\" src=\"https://cdn.dribbble.com/users/2068059/screenshots/4216858/science_plant_logo.png?compress=1&resize=200x150\"/>\n"
                + "            <h1>Dr. Plant S.L. le informa de que su nueva contraseña es:    </h1>\n"
                + "            <h2>" + nuevaContraseña + "</h2>\n"
                + "            Gracias por confiar en nosotros :)\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n"
                + "", "text/html");
        multipart.addBodyPart(mimeBodyPart);

        // Adding up the parts to the MIME message
        message.setContent(multipart);

        // And here it goes...
        Transport.send(message);
    }

    
    public void sendConfirmationMail(String receiver) throws MessagingException {
        //user= priv.cifrarTexto(new String(priv.fileReader("Z:\\2DAMi\\Reto2\\SERVIDOR\\drplantServer\\src\\java\\DrPlant\\encryption\\RSA_Private.key")), "serv1doR");
        //System.out.println(priv.descifrarTexto("Z:\\2DAMi\\Reto2\\SERVIDOR\\drplantServer\\src\\java\\DrPlant\\email\\contraseña.txt")+priv.descifrarTexto("Z:\\2DAMi\\Reto2\\SERVIDOR\\drplantServer\\src\\java\\DrPlant\\email\\correo.txt"));
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
        message.setSubject("Cambio de contraseña"); // Asunto del mensaje

        // A mail can have several parts
        Multipart multipart = new MimeMultipart();

        // A message part (the message, but can be also a File, etc...)
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent("<!DOCTYPE html>\n"
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
                + "            <img alt=\"Logo Dr. Plant\" src=\"https://cdn.dribbble.com/users/2068059/screenshots/4216858/science_plant_logo.png?compress=1&resize=200x150\"/>\n"
                + "            <h1>Dr. Plant S.L. le informa de que se ha modificado su contraseña    </h1>\n"          
                + "            Gracias por confiar en nosotros :)\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n"
                + "", "text/html");
        multipart.addBodyPart(mimeBodyPart);

        // Adding up the parts to the MIME message
        message.setContent(multipart);

        // And here it goes...
        Transport.send(message);
    }
}
