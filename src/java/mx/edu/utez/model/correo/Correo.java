/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.correo;

/**
 *
 * @author alexl
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Reynel Flores
 */
public class Correo {

    /**
     * @param args the command line arguments
     */
    public static String email = "GUIA.Integradora123@gmail.com";
    public static String contrasena = "GUIA123-Integradora";
    public String asunto = "";

    public void enviarMail(String correoDestino, String mensaje, String asunto) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

       Session sesion = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, contrasena);
                    }
                });
        try {

            Message message = new MimeMessage(sesion);

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correoDestino));

            message.setSubject(asunto);

            message.setText(mensaje);

            Transport.send(message);
            System.out.println("Su mensaje ha sido enviado");
//            JOptionPane.showMessageDialog(null, "Su mensaje ha sido enviado");

        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
//        String correoDestino = null;
//        String mensaje = null;
        Correo e = new Correo();
        e.enviarMail("aldo.hernandez3105@gmail.com", "llego???","asesoría cancelada");


    }

}