
public class Food{
  
  int calories;
  int gramsOfProtein;
  int gramsOfCarbs;
  int gramsOfFat;
  String name;
  
  public Food(int calories, int gramsOfFat, int gramsOfCarbs, int gramsOfProtein, String name){
    
    this.calories = calories;
    this.gramsOfProtein = gramsOfProtein;
    this.gramsOfCarbs = gramsOfCarbs;
    this.gramsOfFat = gramsOfFat;
    this.name = name;
    
  }
  
  public void setCalories(int calories){
    this.calories = calories;
  }
  
  public void setGramsOfProtein(int protein){
    this.gramsOfProtein = protein;
  }
  
  public void setGramsOfCarbs(int carbs){
    this.gramsOfCarbs = carbs;
  }
  
  public void setGramsOfFat(int fat){
    this.gramsOfFat = fat;
  }
  
  public int getCalories(){
    return this.calories;
  }
  
  public int getGramsOfProtein(){
    return this.gramsOfProtein;
  }
  
  public int getGramsOfCarbs(){
    return this.gramsOfCarbs;
  }
  
  public int getGramsOfFat(){
    return this.gramsOfFat;
  }
  
}
