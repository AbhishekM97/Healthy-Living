package app;

import java.util.*;

public class NutritionGuide{
  protected ArrayList<Food> acceptableFoods;
  protected double maintainenceCalories; 
  protected double oneLBPerWeekLossCalories;
  protected double goalWeight;
  protected Scanner sc;
  
  public NutritionGuide(double weight, int age, double heightInches, String activityLevel, double goalWeight){
    //To calculate the daily maintainence calories, I referred to "Howcast".
    
    this.goalWeight = goalWeight;
    
    this.sc = new Scanner(System.in);
    
    this.maintainenceCalories = (weight*6.23 + heightInches*12.7 - age*6.8 + 66);
    
    if(activityLevel == "Sedentary"){
      this.maintainenceCalories = this.maintainenceCalories * 1.2;
    }
    else if(activityLevel == "Light"){
      this.maintainenceCalories = this.maintainenceCalories * 1.375;
    }
    else if(activityLevel == "Moderate"){
      this.maintainenceCalories = this.maintainenceCalories * 1.55;
    }
    //else statement for daily exercise
    else{
      this.maintainenceCalories = this.maintainenceCalories * 1.725;
    }
    this.oneLBPerWeekLossCalories = this.maintainenceCalories - 500;
    
    this.acceptableFoods = new ArrayList<Food>();
  }
  
  public double getMaintainenceCalories(){
    return this.maintainenceCalories;
  }
  
  public double getOneLBPerWeekLossCalories(){
    return this.oneLBPerWeekLossCalories;
  }
  
  public void setMaintainenceCalories(double weight, int age, double heightInches, String activityLevel){
    if(activityLevel == "Sedentary"){
      this.maintainenceCalories = this.maintainenceCalories * 1.2;
    }
    else if(activityLevel == "Light"){
      this.maintainenceCalories = this.maintainenceCalories * 1.375;
    }
    else if(activityLevel == "Moderate"){
      this.maintainenceCalories = this.maintainenceCalories * 1.55;
    }
    //else statement for daily exercise
    else{
      this.maintainenceCalories = this.maintainenceCalories * 1.725;
    }
  }
  
  public void setOneLBPerWeekLossCalories(){
    this.oneLBPerWeekLossCalories = this.maintainenceCalories - 500;
  }
  
  public ArrayList<Food> getAcceptableFoods(){
    return this.acceptableFoods;
  }
  
  public void addFood(Food food){
    this.acceptableFoods.add(food);
  }
  
  public void removeFood(Food food){
    this.acceptableFoods.remove(food);
  }
  
 /* public void calculateBodyFat(){
    System.out.println("What is your waste size");
    int waste = this.sc.nextInt();
    
    //double BFP = 86.70*
  }
  */
  
}