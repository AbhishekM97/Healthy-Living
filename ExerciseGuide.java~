

/* The exercise guide should have exercises for the number of days a user wants to workout.
 * To create values the user should be able to select or create exercises. 
 * A possibility is to have an API request for exercises and maybe foods that a user can look at and select from. 
 * 
*/

import java.util.*;

public class ExerciseGuide{
  HashMap<Integer, ArrayList<Exercise>> exerciseRoutine;
  
  public ExerciseGuide(int days){
    this.exerciseRoutine = new HashMap<Integer, ArrayList<Exercise>>();
    for(int i = 1; i <= days; ++i){
      this.exerciseRoutine.put(i, new ArrayList<Exercise>());
    }
  }
  
  //gets the exercises needed to be performed in a specific day.
  
  public ArrayList<Exercise> getExerciseRoutine(int day){
    return this.exerciseRoutine.get(day);
  }
  
  //Method which adds exercises to the ArrayList which represents the exercises to be performed for a specific day.
  
  public void addExercise(int day, Exercise e){
    ArrayList<Exercise> temp = this.exerciseRoutine.get(day);
    temp.add(e);
    this.exerciseRoutine.replace(day, temp);
  }
  
  //Method which removes exercises from the ArrayList that represents the exercises to be performed in a day.
  
  public void removeExercise(int day, Exercise e){
    ArrayList<Exercise> temp = this.exerciseRoutine.get(day);
    temp.remove(e);
    this.exerciseRoutine.replace(day, temp);
  }
  
}