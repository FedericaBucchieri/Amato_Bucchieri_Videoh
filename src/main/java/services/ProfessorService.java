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

    /**
     * This method checks the credential of a professor that wants to log in
     * @param usrn the username of the professor
     * @param pwd the password of the professor
     * @return the professor instance in case of correct login, null otherwise
     * @throws CredentialsException when the password is incorrect
     * @throws UserNotRegisteredException when the user is not registered yet so the username is not present in the database
     */
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

    /**
     * This method retrieves an instance of Professor from the database
     * @param username the username of the professor to be found
     * @return true if the professor exists, false otherwise
     * @throws UserNotRegisteredException when the user is not registered yet so the username is not present in the database
     */
    public boolean findProfessorByUsername(String username) throws UserNotRegisteredException {
        try {
            Professor p = em.createNamedQuery("Professor.findProfessorByUserame", Professor.class).setParameter("username", username)
                    .getSingleResult();
            return p!=null;
        } catch (PersistenceException e) {
            throw new UserNotRegisteredException("The user is not registered yet. Please register and retry");
        }
    }

    /**
     * This method updates the information related to a Professor instance inside the database
     * @param professor the professor instance to be updated
     * @param username the username to be updated
     * @param password the password to be updated
     */
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
