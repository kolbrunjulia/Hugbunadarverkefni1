package is.hi.hbv501g.hbv501g.Persistance.Entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *  Nafn    : Hópur 7
 *  T-póstur: sns25@hi.is, kjg18@hi.is, hrj53@hi.is, mmo15@hi.is
 *
 *  Lýsing  : Þetta er klasi sem býr til Entity-ið workouts,
 *  Forritið autogenerate-ar ID fyrir hvert og eitt workout,
 *  Það er OneToMany tenging frá workouts yfir í exercise_combos Entity-ið og
 *  ManyToOne tenging frá workouts til users.
 *
 *****************************************************************************/

@Entity
@Table(name= "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String title;
    private int duration;
    private String description;
    @OneToMany(mappedBy = "workout",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseCombo> exerciseCombo = new ArrayList<>();
    // Setja inn ManyToMany
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> user = new ArrayList<>();

    private boolean madeByAdmin;


    public Workout() {
    }

    public boolean isMadeByAdmin() {
        return madeByAdmin;
    }

    public Workout(String title, int duration, String description) {
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.madeByAdmin = false;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getDescription() {return description;}
    public List<ExerciseCombo> getExerciseCombo() {
        return exerciseCombo;
    }
    public List<User> getUser() {
        return  user;
    }
    public void setExerciseCombo(List<ExerciseCombo> exerciseCombo) {
        this.exerciseCombo = exerciseCombo;
    }
}



