package is.hi.hbv501g.hbv501g.Persistance.Repositories;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/******************************************************************************
 *  Name    : Group 7
 *  E-mail  : sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Description  : Repository for the Workout entity. Contains search methods
 *  as well as methods to save and delete a workout.
 *****************************************************************************/

/**
 * The main repository for handling workouts.
 */

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Workout save(Workout workout);

    void delete(Workout workout);

    List<Workout> findAll();

    List<Workout> findByTitle(String title);

    Workout findByID(long ID);

    List<Workout> findWorkoutsByMadeByAdminIsTrue();

    @Query("SELECT w FROM Workout w WHERE w.madeByAdmin = TRUE AND w IN (SELECT w FROM Workout w WHERE CONCAT(w.title, w.description) LIKE %?1%)")
    public List<Workout> search(String keyword);

    //@Query("SELECT w FROM Workout w where w.madeByAdmin = TRUE AND w in (select w from Workout w where w.duration > 0 and w.duration < 20)")
    public List<Workout> searchWorkoutByDurationIsLessThanEqualAndDurationGreaterThanEqualAndMadeByAdminIsTrue(int duration, int duration2);

    List<Workout> findByUser(User user);
}
