package app;


public class Person{
  
 protected String name;
 protected int age;
 protected double currentWeight;
 protected double goalWeight;
 protected NutritionGuide nG;
 protected ExerciseGuide eG;
 
 public Person(String name, int age, double currentWeight, double goalWeight, NutritionGuide ng, ExerciseGuide eg){
   this.name = name;
   this.age = age;
   this.currentWeight = currentWeight;
   this.goalWeight = goalWeight;
   this.nG = ng;
   this.eG = eg;
 }
 
 public String getName(){
   return this.name;
 }
 
 public void setName(String name){
   this.name = name;
 }
 
 public int getAge(){
   return this.age;
 }
 
 public void setAge(int age){
   this.age = age;
 }
 
 public double getCurrentWeight(){
   return this.currentWeight;
 }
 
 public void setCurrentWeight(double cw){
   this.currentWeight = cw;
 }
 
 public double getGoalWeight(){
   return this.goalWeight;
 }
 
 public NutritionGuide getNG(){
   return this.nG;
 }
 
 public ExerciseGuide getEG(){
   return this.eG;
 }
 
 
 /* Finish person class.
  * Getter and Setter Methods
  * Setter methods (Only for name, age, currentWeight, and goalWeight)
  */
 
}
  