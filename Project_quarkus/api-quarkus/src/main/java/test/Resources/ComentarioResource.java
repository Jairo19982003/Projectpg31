package test.Resources;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Comentario;
import model.Request;
import test.Services.Implementacion.ComentarioService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;


@Path("/Comentario")
public class ComentarioResource {

    @Inject
    ComentarioService comentario;

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    @Path("/comentarioId")
    //se utiliza para obtener un comentario
    public Comentario getComentario() {
        System.out.println("Comentario: " + comentario.getComentario());
        return comentario.getComentario();
    }

    @GET
    public List<Comentario> getComentarios(){
        return comentario.getComentarios();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    //se utiliza para guardar un comentario
    public Response createComentario(Request comentarioRequest) {
        try{
            System.out.printf("Comentario: %s\n", comentarioRequest);
                Comentario comentarior = new Comentario();
                comentarior.setComentario(comentarioRequest.getComentario());
                comentarior.setFecha(comentarioRequest.getFecha());
                comentarior.setUsuario(comentarioRequest.getUsuario());
                comentarior.setProject(comentarioRequest.getProject());
            System.out.println("S1: " + comentarior);
                comentario.save(comentarior);
                comentario.saveFile(comentarioRequest);
//falta agregar funcionalidad para que se guarde en un archivo txt

        return Response.ok("Comentario guardado"+ comentarior).build();
        }catch (PersistenceException e){
            throw e;
//            return Response.status(Response.Status.BAD_REQUEST).entity("Error al guardar el comentario").build();
        }
    }

    @GET
    @Path("/paginated")
    //http://localhost:8080/Comentario/paginated?page=2
    public PaginatedReponse<Comentario> list(@QueryParam("page")@DefaultValue("1") int page) throws MessagingException {
        sendEmail();
        return comentario.paginated(page);
    }

//    @GET
//    @Path("/reactive")
//    public Uni<Void> sendEmailUsingReactiveMailer() {
//        return reactiveMailer.send(
//                Mail.withText("jcastillog33@miumg.edu.gt",
//                        "Correo de prueba",
//                        "Hola mundo, prueba..."
//                )
//        );
//    }

//    private void ComentarioRsend(){
//        Resend resend = new Resend("re_syyKq8jV_GaabjwwNRuqRWhb4PYxQ1Eqq");
//        CreateEmailOptions params = CreateEmailOptions.builder()
//                .from("Acme <onboarding@resend.dev>")
//                .to("jcastillog33@miumg.edu.gt")
//                .subject("it works!")
//                .html("<strong>hello world</strong>")
//                .build();
//        try {
//            CreateEmailResponse data = resend.emails().send(params);
//        } catch (ResendException e) {
//            e.printStackTrace();
//        }
//    }




    public static void sendEmail() throws MessagingException {
        final String username = "castillo.jairo99930@gmail.com"; // Tu dirección de correo
        final String password = "gsop eknz bhos rgpf"; // Tu contraseña

        // Propiedades de conexión SMTP
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        // Autenticación
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("castillo.jairo99930@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("stevgtpayes@gmail.com")
            );
            message.setSubject("prueba");
            message.setText("Hola, este es un correo de prueba");

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Mensaje enviado exitosamente");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
