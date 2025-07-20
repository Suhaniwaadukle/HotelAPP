/*package Mail;

import com.sendgrid.*;
import com.sendgrid.Email;
import com.sendgrid.Content;
import com.sendgrid.Mail;
import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.Method;


import java.io.IOException;

public class emailSender {
    private final String apiKey;

    public emailSender(String apiKey) {
        this.apiKey = apiKey;
    }

    public void emailConfigurations(String toEmail, String subject, String content) {
        Email from = new Email("rana228safi@gmail.com");
        Email to = new Email(toEmail);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, emailContent);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println("Status Code: " + response.getStatusCode());

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                System.out.println("Email sent successfully!");
            } else {
                System.out.println("Failed to send email. Body: " + response.getBody());
            }

        } catch (IOException ex) {
            System.err.println("Error sending email: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void sendEmail(String toEmail, String subject, String content) {
        String apiKey = System.getenv("SENDER_GRID_API_KEY");
        emailSender emailService = new emailSender(apiKey);
        emailService.emailConfigurations(toEmail, subject, content);
    }


}

 */



package Mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class emailSender {

    public static void sendEmail(String toEmail, String subject, String content) {
        final String fromEmail = "suhaniwadukle@gmail.com"; // sender email
        final String password = "1234";       // ⚠️ use app password (not Gmail password)

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
