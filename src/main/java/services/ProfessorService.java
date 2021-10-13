package services;

import entities.Professor;
import exceptions.CredentialsException;
import exceptions.UserNotRegisteredException;
import jakarta.persistence.*;

import java.util.List;

public class ProfessorService {
    @PersistenceContext
    private EntityManager em;
    private  EntityManagerFactory entityManagerFactory;

    public ProfessorService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.em = entityManagerFactory.createEntityManager();
    }

    public Professor checkCredentials(String usrn, String pwd) throws CredentialsException, UserNotRegisteredException {
        List<Professor> professorList = null;

        professorList = em.createNamedQuery("Professor.checkCredentials", Professor.class).setParameter(1, usrn).setParameter(2, pwd)
                .getResultList();

        if (professorList.isEmpty()) {
            if (findProfessorByUsername(usrn))
                throw new CredentialsException("Password incorrect. Please retry");
            else
                throw new UserNotRegisteredException("Invalid username. Please register first");
        } else if (professorList.size() == 1)
            return professorList.get(0);

        return null;
    }

    public void createProfessor(String username, String password){
        Professor professor = new Professor(username, password);
        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction().commit();
    }

    public boolean findProfessorByUsername(String username) throws UserNotRegisteredException {
        try {
            Professor p = em.createNamedQuery("Professor.findProfessorByUserame", Professor.class).setParameter("username", username)
                    .getSingleResult();
            return p!=null;
        } catch (PersistenceException e) {
            throw new UserNotRegisteredException("The user is not registered yet. Please register and retry");
        }
    }

    public void updateProfessor(Professor professor, String username, String password){
        if(username != null)
            professor.setUsername(username);

        if(password != null)
            professor.setPassword(password);

        em.getTransaction().begin();
        em.merge(professor);
        em.getTransaction().commit();
    }


}
