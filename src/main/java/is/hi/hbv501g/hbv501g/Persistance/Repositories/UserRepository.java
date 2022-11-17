package is.hi.hbv501g.hbv501g.Persistance.Repositories;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail  : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Repository for the User entity. Contains search methods
 *  as well as methods to save and delte a user.
 *****************************************************************************/

/**
 * The main repository for handling user data.
 */

public interface UserRepository extends JpaRepository<User, Long > {
    User save(User user);

    void delete(User user);

    List<User> findAll();

    User findByUsername(String username);

    User findByID(long id);
}

