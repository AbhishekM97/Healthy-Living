package app;

public class Exercise{
  
  String name;
  String muscleGroup;
  String kindOfExercise;
  int repetitions;
  int sets;
  double weight;
  double time;
  
  public Exercise(String name, String muscleGroup, String kindOfExercise, int repetitions, int sets, double weight, double time){
    
    this.name = name;
    this.muscleGroup = muscleGroup;
    this.kindOfExercise = kindOfExercise;
    this.repetitions = repetitions;
    this.sets = sets;
    this.weight = weight;
    this.time = time;
    
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public void setMuscleGroup(String mG){
    this.muscleGroup = mG;
  }
  
  public void setKindOfExercise(String KFE){
    this.kindOfExercise = KFE;
  }
  
  public void setRepetitions(int reps){
    this.repetitions = reps;
  }
  
  public void setSets(int sets){
    this.sets = sets;
  }
  
  public void setWeight(double weight){
    this.weight = weight;
  }
  
  public void setTime(double time){
    this.time = time;
  }
  
  public String getName(){
    return this.name;
  }
  
  public String getMuscleGroup(){
    return this.muscleGroup;
  }
  
  public String getKindOfExercise(){
    return this.kindOfExercise;
  }
  
  public int getRepetitions(){
    return this.repetitions;
  }
  
  public int getSets(){
    return this.sets;
  }
  
  public double getWeight(){
    return this.weight;
  }
  
  public double getTime(){
    return this.time;
  }

}
  
  
  
  