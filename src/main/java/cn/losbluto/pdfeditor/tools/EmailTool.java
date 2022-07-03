package cn.losbluto.pdfeditor.tools;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * @author LosBluto
 * @version 1.0.0
 * @ClassName EmailTool.java
 * @Description TODO
 * @createTime 2022年07月03日 09:00:00
 */
public class EmailTool {
    private static final String auth = "YVABDILIYXDVTGXH";
    private static final String HOST = "smtp.163.com";
    private static final String from = "Jiang18782180393@163.com";

    public static void send(String to,String filePath) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.host",HOST);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,auth);
            }
        });
        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("老师，pdf整完了 (<-_<-)");

            Multipart multipart = new MimeMultipart();      //设置消息体
            BodyPart body = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            body.setDataHandler(new DataHandler(source));
            body.setFileName(filePath);
            multipart.addBodyPart(body);

            message.setContent(multipart);

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        send("1181157997@qq.com","D:\\Project\\JAVA\\PDFEditor\\files\\test.zip");
    }
}
