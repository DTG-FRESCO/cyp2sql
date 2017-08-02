package production;//package production;
//
//import database.DbUtil;
//import org.apache.commons.lang3.SystemUtils;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import java.io.File;
//import java.sql.SQLException;
//import java.util.Properties;
//
///**
// * Class for sending email with test results to user.
// */
//class SendResultsEmail {
//    /**
//     * Method for sending email with test results. Contains HTML summary in the email itself, and
//     * an attachment with the full results in .csv format. All emails currently sent to
//     * ojc37@cam.ac.uk.
//     *
//     * @param dbName        Database name test results related too.
//     * @param typeTranslate The type of translation the test results were performed under.
//     * @throws SQLException Obtaining the test results can throw an SQL error.
//     */
//    static void sendEmail(String dbName, String typeTranslate) throws SQLException {
//        String testResultContents = DbUtil.getTestResults(dbName, typeTranslate);
//        String email = "testneo4jcambridge@gmail.com";
//        final String username = email;
//        final String pw = "neo4j###73";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, pw);
//                    }
//                });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(email));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ojc37@cam.ac.uk"));
//            message.setSubject("Test Results Cyp2SQL -- " + dbName);
//
//            Multipart multipart = new MimeMultipart();
//
//            MimeBodyPart textBodyPart = new MimeBodyPart();
//            textBodyPart.setText(testResultContents, "utf-8", "html");
//
//            // file to attach
//            String file;
//            if (SystemUtils.IS_OS_LINUX) {
//                file = "/home/ojc37/props/testR.csv";
//            } else {
//                file = "C:/Users/ocraw/Desktop/testR.csv";
//            }
//
//            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(file); // ex : "C:\\test.pdf"
//            attachmentBodyPart.setDataHandler(new DataHandler(source));
//            attachmentBodyPart.setFileName(dbName + "_full_results.csv");
//
//            multipart.addBodyPart(textBodyPart);  // add the text part
//            multipart.addBodyPart(attachmentBodyPart); // add the attachment part
//
//            message.setContent(multipart);
//
//            System.out.println("Sending mail...");
//            Transport.send(message);
//            System.out.println("Sent!");
//            File f = new File(file);
//            f.delete();
//        } catch (MessagingException e) {
//            System.err.println("Failed to send...");
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * In the case (hopefully unlikely) of a SQL execution failure, send the failed SQL back to the
//     * user, so that it can be fixed.
//     *
//     * @param dbName Database the testing is being performed on.
//     * @param sql    The failed SQL.
//     */
//    static void sendFailEmail(String dbName, String sql) {
//        String email = "testneo4jcambridge@gmail.com";
//        final String username = email;
//        final String pw = "neo4j###73";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, pw);
//                    }
//                });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(email));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ojc37@cam.ac.uk"));
//            message.setSubject("Translation Failed Cyp2SQL -- " + dbName);
//
//            Multipart multipart = new MimeMultipart();
//
//            MimeBodyPart textBodyPart = new MimeBodyPart();
//            textBodyPart.setText("<html><body>The following query failed for some reason : </br> '" +
//                    sql + "' </br> </body> </html>", "utf-8", "html");
//
//            multipart.addBodyPart(textBodyPart);  // add the text part
//
//            message.setContent(multipart);
//
//            System.out.println("Sending fail message...");
//            Transport.send(message);
//            System.out.println("Sent!");
//        } catch (MessagingException e) {
//            System.err.println("Failed to send...");
//        }
//    }
//}
