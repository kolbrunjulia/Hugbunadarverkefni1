package is.hi.hbv501g.hbv501g.Services.Implementation;
import is.hi.hbv501g.hbv501g.Persistance.Entities.User;
import is.hi.hbv501g.hbv501g.Persistance.Entities.Workout;
import is.hi.hbv501g.hbv501g.Persistance.Repositories.WorkoutRepository;
import is.hi.hbv501g.hbv501g.Services.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/******************************************************************************
 *  Nafn    : Hópur 7
 *  T-póstur: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Lýsing  : Implementation á WorkoutService. Inniheldur leitaraðferðir
 *  ásamt því að hafa aðferðir sem eyða og vista workout. Inniheldur einnig
 *  aðferð sem listar öll workouts. Klasinn talar við WorkoutRepository og
 *  kallar á aðferðirnar sem eru þar.
 *
 *****************************************************************************/

@Service
public class WorkoutServiceImplementation implements WorkoutService {
    // Connection to the repo
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutServiceImplementation(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout findByTitle(String title) {
        // Only returns the first workout that matches the title
        return workoutRepository.findByTitle(title).get(0);
    }

    @Override
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public List<Workout> findWorkoutsByMadeByAdminIsTrue() {
        return workoutRepository.findWorkoutsByMadeByAdminIsTrue();
    }

    @Override
    public Workout findByID(long ID) {
       return workoutRepository.findByID(ID);
    }

    @Override
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public void delete(Workout workout) {
        workoutRepository.delete(workout);
    }

    @Override
    public List<Workout> listAll(String keyword) {
            if (keyword != null) {
                return workoutRepository.search(keyword);
            }
            return workoutRepository.findWorkoutsByMadeByAdminIsTrue();
    }

    @Override
    public void addUserToWorkout(User user,Workout workout) {
        List<User> userList = workout.getUser();
        workout.getUser().add(user);
    }
}
