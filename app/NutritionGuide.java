package app;

import java.util.*;

public class NutritionGuide{
  protected ArrayList<Food> acceptableFoods;
  protected double maintainenceCalories; 
  protected double oneLBPerWeekLossCalories;
  protected double goalWeight;
  protected double BFP;
  protected double height;
  protected double lbsOfFat;
  protected double lowestCalPerDay;
  protected double weight;
  protected int age;
  protected String activityLevel;
  protected String gender;
  protected Scanner sc;
  protected double MyGramPro;
  protected double MyGramCar;
  protected double MyGramFat;
  
  public NutritionGuide(double weight, int age, double heightInches, String activityLevel, double goalWeight){
    //To calculate the daily maintainence calories, I referred to "Howcast".
    
    this.goalWeight = goalWeight;
    
    this.weight = weight;
    
    this.activityLevel = activityLevel;
    
    this.sc = new Scanner(System.in);
    
    this.height = heightInches;
    
    this.BFP = 0.0;
    
    this.age = age;
    
    System.out.println("What sex are you (Gender)");
    this.gender = this.sc.nextLine();
    
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
  
  public double calculateBodyFat(){
	int hips;
    System.out.println("\nWhat is your waist size in inches. \nMeasure the circumference of the ring that overlaps your belly button for men. \nMeasure the smallest width ring around the abdomen for women. ");
    int waist = this.sc.nextInt();
    System.out.println("What is the circumference of your neck in inches. \nMeasure with tape placed right below the larynx. \nAvoid flaring your neck outwards and keep it upright.");
    int neck = this.sc.nextInt();
    if(this.gender.equals("female")) {
    	System.out.println("Measure your hips at the largest horizontal length.");
    	hips = this.sc.nextInt();
    	this.BFP =  163.205 * Math.log10((waist + hips) - neck) - 97.684 * (Math.log10(this.height) + 36.76);
    }
    else {
    	this.BFP = 86.010 * Math.log10(waist - neck) - 70.041 * Math.log10(this.height) + 36.76;
    }
    System.out.println("Body fat percentage: " + this.BFP);
    return this.BFP;
  }
  
  public double getLowestCalPerDay() {
	  
	  this.lbsOfFat = this.BFP/100 * this.weight; 
	  double leanWeight = this.weight - this.lbsOfFat;
	  this.lowestCalPerDay = (leanWeight*6.23 + this.height*12.7 - this.age*6.8 + 66);
	  
	  if(this.activityLevel.equals("Sedentary")) {
		  this.lowestCalPerDay *= 1.2;
	  }
	  if(this.activityLevel.equals("Light")) {
		  this.lowestCalPerDay *= 1.375;
	  }	  
	  if(this.activityLevel.equals("Moderate")) {
		  this.lowestCalPerDay *= 1.55;
	  }
	  if(this.activityLevel.equals("Very Active")) {
		  this.lowestCalPerDay *= 1.725;
	  }
	  return this.lowestCalPerDay;
  }
  
  public void setPersonMacros() {
	/* Give users option to modify their macros.
	 * The lean weight is calculated with absolute zero body fat percentage.
	 * That is unrealistic and needs to be adjusted. The goal for users should be to be about 10% BF;
	 * An increase in body fat means a increase in the users calories. (You're welcome)
	 */
  }
}