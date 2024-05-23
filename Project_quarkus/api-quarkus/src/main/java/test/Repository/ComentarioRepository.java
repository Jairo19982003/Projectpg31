package test.Repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import model.Comentario;
import model.Request;
import model.Usuario;
import test.Implementacion.IComentarioRepo;

import java.util.List;
import java.util.ServiceLoader;

@ApplicationScoped
public class ComentarioRepository implements IComentarioRepo {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    //hacer que se guarde en formato txt el comentario
    public void saveComment(Comentario com) {
        persist(com);
    }



    public Comentario getComentario(){
        return find("id", 1).firstResult();
    }

    @Override
    public List<Comentario> getComentarios() {
        return listAll();
    }


}
