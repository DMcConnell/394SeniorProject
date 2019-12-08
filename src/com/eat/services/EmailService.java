package com.eat.services;
import java.util.HashMap;
import java.util.Properties;  
import javax.mail.*;
import javax.mail.internet.*;
import com.eat.support.Support;
import com.eat.ui.LaunchStage;
  
public class EmailService {
	
    private Session session;
    private boolean authentication=true;
    private boolean smtpServerTTLSEnabled = true;
    private String host = "smtp.gmail.com";
    private String port = "587";
    private String username="";
    private String password="";
 
    //Initialize email setup and connection with admin user info from database
    public void init() throws Exception {
    	HashMap<String, String> admin = LaunchStage.getInstance().getContactService().getUser("Admin");
    	username = admin.get(IUser.EMAIL);
    	password = Support.decryptPass(admin.get(IUser.PASSWORD));
    	
        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(authentication));
        props.put("mail.smtp.starttls.enable",smtpServerTTLSEnabled);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
            }
        });
    }
 
    //Using provided address, send an email with given subject and message
    public void sendEmail(String toEmailAddress, String emailSubject, String emailMessage) {
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            String[] recipientList = toEmailAddress.split(",");
            InternetAddress[] recipientAddresses = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient: recipientList)
            {
                recipientAddresses[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);
            message.setSubject(emailSubject);
            message.setText(emailMessage);
            Transport.send(message);
        }
        catch (MessagingException e)
        {
        	throw new RuntimeException(e);
        }
    }
  
}  
