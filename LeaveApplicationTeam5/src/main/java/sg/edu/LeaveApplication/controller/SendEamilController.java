package sg.edu.LeaveApplication.controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sg.edu.LeaveApplication.model.Status;

import java.util.Properties;

public class SendEamilController{
	   public static void SendEmailSSL(String status,String userName,String about,String toEmail) {

			        final String username = "team5leavesystem.sa50@gmail.com";
			        final String password = "hupptktyehwzgwgb";
			        

			        
			        Properties prop = new Properties();
					prop.put("mail.smtp.host", "smtp.gmail.com");
			        prop.put("mail.smtp.port", "465");
			        prop.put("mail.smtp.auth", "true");
			        prop.put("mail.smtp.socketFactory.port", "465");
			        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			        Session session = Session.getInstance(prop,
			                new javax.mail.Authenticator() {
			                    protected PasswordAuthentication getPasswordAuthentication() {
			                        return new PasswordAuthentication(username, password);
			                    }
			                });

			        try {

			            Message message = new MimeMessage(session);
			            message.setFrom(new InternetAddress("team5leavesystem.sa50@gmail.com"));
			            message.setRecipients(
			                    Message.RecipientType.TO,
			                    InternetAddress.parse(toEmail)
			            );
			            message.setSubject(status+" Your Leave Request!");
			            message.setText("Dear "+userName+" ,"
			                    + "\n\n Your Leave request is"+status +"due to "+about);

			            Transport.send(message);

			            System.out.println("Done");

			        } catch (MessagingException e) {
			            e.printStackTrace();
			        }
			    }


}
