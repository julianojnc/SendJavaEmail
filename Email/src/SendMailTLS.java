
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

    public static void main(String[] args) {
        
        /*|LEIA ESSA MENSAGEM|
        *ESSE CODIGO SÓ FUNCIONA COM A ADD DAS BIBLIOTECAS "MAIL-1.4.7.JAR" E "COMMONS-EMAIL-1.5.JAR"
        *PARA SE OBTER A SENHA DE APP SERÁ NECESSÁRIO COLOCAR SUA CONTA DO EMAIL COM VERIFICAÇÃO EM DUAS ETAPAS
        *APÓS A VERIFICAÇÃO EM DUAS ETAPAS, NO CASO DO GOOGLE NO MESMO LOCAL SERÁ MOSTRADO UM NOVO CAMPO CHAMADO "SENHAS DE APP"
        *NO CAMPO MENCIONADO SERÁ POSSÍVEL ESTAR GERANDO UMA SENHA DE APP, APÓS GERAR ADD NA VARIAVEL >PASSWORD<
        *LEMBRANDO DE ADD O EMAIL NA VARIAVEL >USERNAME<
        *ALTERE TAMBEM O EMAIL QUE IRA RECEBER A MENSAGEM E A MENSAGEM
         */

        String username = "ADD EMAIL AQUI";//Declarando variavel username com o email que envia as mensagens
        String password = "ADD SENHA DE APP AQUI";//Declarando variavel password com a senha de acesso de App

        Properties props = new Properties();//Objeto criado para ser atribuido metodos das bibliotecas
        props.put("mail.smtp.auth", "true");//Configuração que permite o protocolo de autentificação seja usado
        props.put("mail.smtp.starttls.enable", "true");//Configuração que instrui o servidor do Identity Manager a usar SSL para se comunicar com o SMTP
        props.put("mail.smtp.host", "smtp.gmail.com");//Configuração que indica quem é o host no caso gmail.com
        props.put("mail.smtp.port", "465");//Configuração que indica a porta a ser utilizada no caso a do google
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");//Especifica os protocolos SSL que serão habilitados para conexões SSL e utilizado o protocolo TLSv1.2 = Configuration Manager comunicação segura
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Maneira simples de capturar uma variedade de políticas relacionadas aos socket que estão sendo construídos, javax.net.ssl.SSLSocketFactory = atua como uma Factory para criar Socket seguros. Esta classe é uma subclasse abstrata de javax.

        //Objeto Session criado com um metodo getInstance, objeto props com uma classe de Autentificação
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {//Autentificando a senha
                return new PasswordAuthentication(username, password);//Retornando a Autentificação para a variavel username e password
            }
        });

        try {

            Message message = new MimeMessage(session);//Declarando um objeto message no qual contem uma Classe MimeMesssage representa uma mensagem de e-mail no estilo MIME e dentro dela temos o objeto session referente a autentificação da senha
            message.setFrom(new InternetAddress("ADD EMAIL DE QUEM VAI RECEBER AQUI"));//Dizendo para quem a mensagem será enviada
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ADD EMAIL DE QUEM VAI RECEBER AQUI"));
            message.setSubject("DIGITE O TITULO AQUI");//Titulo do Email
            message.setText("ADD TEXTO 1," + "\n\n ADD TEXTO 2!");//Texto contido dentro do email

            Transport.send(message);//Responsavel por enviar a mensagem

            System.out.println("Enviado");//Texto apresentado na janela debug se a mensagem foi enviada

        } catch (MessagingException e) {//erro
            throw new RuntimeException(e);
        }
    }
}
