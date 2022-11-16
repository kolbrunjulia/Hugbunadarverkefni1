package is.hi.hbv501g.hbv501g.Services;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import java.util.List;

/******************************************************************************
 *  Nafn    : Hópur 7
 *  T-póstur: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Lýsing  : Service fyrir Workout. Inniheldur leitaraðferðir ásamt
 *  aðferðum til að vista og eyða workout. Inniheldur einnig aðferð
 *  sem segir listar allar æfingar sem eru til.
 *
 *****************************************************************************/

public interface WorkoutService {
        Workout findByTitle(String title);
        List<Workout> findAll();
        List<Workout> findWorkoutsByMadeByAdminIsTrue();

        Workout findByID(long ID);
        Workout save(Workout workout);
        void delete(Workout workout);
        List<Workout> listAll(String keyword);
        void addUserToWorkout(User user, Workout workout);

        //public List<User> getUser();
}
