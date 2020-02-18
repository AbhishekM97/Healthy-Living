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
  protected double gramPro;
  protected double gramCar;
  protected double gramFat;
  
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
  
  public void removeFood(int Index){
    this.acceptableFoods.remove(Index);
  }
  
  public double calculateMaintainenceCal(double weight, int age, double heightInches, String activityLevel) {
	  double mainCal = (weight*6.23 + heightInches*12.7 - age*6.8 + 66);
	  if(activityLevel == "Sedentary"){
		  mainCal = mainCal * 1.2;
	  }
	  else if(activityLevel == "Light"){
		  mainCal = mainCal * 1.375;
	  }
	  else if(activityLevel == "Moderate"){
		  mainCal = mainCal * 1.55;
	  }
		      //else statement for daily exercise
	  else{
		  mainCal = mainCal * 1.725;
	  }
	  return mainCal;
  }
  
  public double calculateBodyFat(){
	int hips;
	System.out.println("This is a way to calculate your bodyfat at home if you have no idea what it is or don't have access to fancy equipment at the moment."
			+ "\nWhat you will need is MEASURING TAPE to accurately calculate your body-fat.");
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
	  leanWeight += (this.lbsOfFat*10.0)/this.BFP;
	  System.out.println("\nIf you lose "+(this.weight-leanWeight)+" LBS of fat your body-fat percentage should be at 10%.\nAt 10% you should aim to be this weight: "+leanWeight);
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
	  this.gramCar = (this.oneLBPerWeekLossCalories*0.30)/4;
	  this.gramFat = (this.oneLBPerWeekLossCalories*0.35)/9;
	  this.gramPro = (this.oneLBPerWeekLossCalories*0.35)/4;
	  
	  System.out.println("\nMacronutrients\nGrams of Protein: " + this.gramPro + "\nGrams of Carbs: " + this.gramCar + "\nGrams of Fat: " + this.gramFat + "\n");
  }
  
  public void setPersonMacros2() {
	/* Defaults to setting the macros to minimal calories, lower carbs, maintainence proteins, and moderate fat.
	 */
	  this.gramPro = (this.lowestCalPerDay*0.35)/4;
	  this.gramCar = (this.lowestCalPerDay*0.35)/4;
	  this.gramFat = (this.lowestCalPerDay*0.30)/9;
	  
	  System.out.println("\nMacronutrients\nGrams of Protein: " + this.gramPro + "\nGrams of Carbs: " + this.gramCar + "\nGrams of Fat: " + this.gramFat + "\n");
	  
  }

  public void setPersonMacros(double proteins, double carbs, double fats) {
	  this.gramCar = carbs;
	  this.gramFat = fats;
	  this.gramPro = proteins;
	  System.out.println("\nMacronutrients\nGrams of Protein: " + this.gramPro + "\nGrams of Carbs: " + this.gramCar + "\nGrams of Fat: " + this.gramFat + "\n");
  }
  
  public double[] getPersonMacros() {
	  double[] macros = {this.gramPro, this.gramCar, this.gramFat};
	  return macros;
  }
  
  /* Method that calculates how many pounds of weight you will lose,
   * given an input of a number of months. There is 3500 calories in a pound of fat.
   * Hence why if you decrease your calories to 500 below your maintainence youll lose a lb of fat in a week.
   */
  public double MonthDeadLine(int months) {
	  double adjCurWeight = this.weight;
	  int calPerLB = 3500;
	  //int counter = 0;
	  double calBurned = 0;
	  double tempMainCal = this.maintainenceCalories;
	  for(int i = 0; i < (30*months); i++) {
		  double caloricDeficit = tempMainCal - this.lowestCalPerDay;
		  
		  System.out.println(tempMainCal);
		  
		  calBurned += caloricDeficit;
		  if(calBurned >= calPerLB) {
			  calBurned -= calPerLB;
			  adjCurWeight -= 1.00;
			  tempMainCal = this.calculateMaintainenceCal(adjCurWeight, this.age, this.height, this.activityLevel);
		  }
	  }
	  
	  double weightLoss = this.weight - adjCurWeight;
	  return weightLoss;
  }
  
}