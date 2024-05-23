package test.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Usuario;
import test.Implementacion.IUsuarioRepo;

@ApplicationScoped
public class UsuarioRepository implements IUsuarioRepo {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void save(Usuario usuario) {em.persist(usuario);}
}
