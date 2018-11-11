/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Tanya This class is used to send an email with or without attachments
 * to the specified address and the contents sent in.
 */


public class Email {

    String recipient;
    String msg;
    String subject;
    String filepath; //e.g. C:\\Users\\Tanya\\Documents\\NetBeansProjects\\BC_Stationary_BLL\\Test.txt --> use \\

    public Email(String recipient, String msg, String subject, String attachmentPath) {
        this.recipient = recipient;
        this.msg = msg;
        this.subject = subject;
        this.filepath = attachmentPath;
    }

    public Email() {
    }

    public void sendEmail() {

        try {
            //Properties
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.auth", "true");

            //Session
            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(true);

            //Text
            MimeBodyPart text = new MimeBodyPart();
            text.setText(msg);

            MimeBodyPart attachment = new MimeBodyPart();
            Multipart content = new MimeMultipart();

            if (!filepath.equals("")) {
                //Attachment
                FileDataSource fds = new FileDataSource(filepath);
                attachment.setDataHandler(new DataHandler(fds));
                attachment.setFileName(fds.getName());
                //Create Multipart & Add Parts
                content.addBodyPart(text);
                content.addBodyPart(attachment);
            } else {
                content.addBodyPart(text);
            }

            //Compile Message
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);
            message.setContent(content);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            //Send Message
            Transport transport = mailSession.getTransport();
            transport.connect("smtp.gmail.com", 465, "additionaladdress.tanya@gmail.com", "AdditionalAddress1!");
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
