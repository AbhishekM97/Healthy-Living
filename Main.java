import java.util.Scanner;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main{

  public static NutritionGuide makeFood(String searchFood, NutritionGuide ng) throws ParseException, IOException{
    
    /*Get response from EDAMAM api for different foods. There is a search parameter which is "searchFood" for purposes of testing right 
     * now. I first create a URL object with the "foodUrl". Create a HttpURLConnection object by typecasting the object the openConnection method returns
     * for the url object. Next the request is set to 'GET'. This allows to get information from a database.
     * The 'getResponseCode' method for 'con' allows me to check if we established a successful connection to the database/server. If the value is 200 
     * then a connection has been established.
     * 
     * Next the object the response returns is an inputstream. So, we create a inputStreamReader inside of a buffered reader in order to get the actual information.
     * Then we create a string that will be each line of the InputStream from the response until a null value is reached. While each line of the inputstream is being read
     * and 'inputLine' is being updated the StringBuffer object which allows to muttated strings and is a subclass of the String class is appened each line of the inputStream.
     * The bufferedReader is then closed. Afterwards I declare a JSON parser and a JSONObject which is assigned a typecasted value for a parsed object of the buffered string.
     * Then I create JSONArray which is assigned a value from the JSONObject. I get the JSONArray titled "hints" and access the food label and nutrients of each JSONobject in the JSONArray.
     * 
    */
    
    Scanner sc = new Scanner(System.in);
    
    HashMap<String, JSONObject> FoodList = new HashMap<String, JSONObject>();
    
    String foodUrl = "https://api.edamam.com/api/food-database/parser?ingr=" +searchFood + "&app_id=a2e1f841&app_key=8ffda76c642828b24d190f785c4e52cc";
    URL url = new URL(foodUrl);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setConnectTimeout(5000);
    int status = con.getResponseCode();
    
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while((inputLine = in.readLine()) != null){
      content.append(inputLine);
    }
    in.close();
    
    JSONParser parse = new JSONParser();
    JSONObject jobj = (JSONObject)parse.parse(content.toString());
    JSONArray jsonarr1 = (JSONArray) jobj.get("hints");
    
    boolean continueAdd;
    
    for(int i = 0; i <jsonarr1.size(); i++){
      System.out.println();
      JSONObject jsonObj_1 = (JSONObject)jsonarr1.get(i);
      JSONObject jsonObj_2 = (JSONObject)jsonObj_1.get("food");
      JSONObject jsonObj_3 = (JSONObject)jsonObj_2.get("nutrients");
      String foodName = (String)jsonObj_2.get("label");
      
      if(FoodList.containsKey(foodName) == true){
        foodName = foodName.concat(Integer.toString(i));
      }
      
      FoodList.put(foodName, jsonObj_3);
    }
    
    do{
      
      for(int i = 0; i <jsonarr1.size(); i++){
        System.out.println();
        JSONObject jsonObj_1 = (JSONObject)jsonarr1.get(i);
        JSONObject jsonObj_2 = (JSONObject)jsonObj_1.get("food");
        JSONObject jsonObj_3 = (JSONObject)jsonObj_2.get("nutrients");
        String foodName = (String)jsonObj_2.get("label");
        FoodList.put(foodName, jsonObj_3);
        System.out.println("Name: " +foodName);
        System.out.println("Nutrients: " + jsonObj_3);
      }
        System.out.println("Look through the results and type the name of the food you'd like to add to your foods list");
        String addFood = sc.nextLine();
        while(FoodList.containsKey(addFood) == false){
          System.out.println("Incorrect food name. Type the name exactly as it appears on the screen.\nFeel free to copy and paste if possible.");
          addFood = sc.nextLine();
        }
        
        JSONObject selectedFood = FoodList.get(addFood);
        
        int calories;
        int gOfPro;
        int gOfCar;
        int gOfFat;
        
        if(selectedFood.get("PROCNT") != null){
          gOfPro = (int)Math.round((double)selectedFood.get("PROCNT"));
        }
        
        else{ 
          gOfPro = 0;
        }
        
        if(selectedFood.get("CHOCDF") != null){
          gOfCar = (int)Math.round((double)selectedFood.get("CHOCDF"));
        }
        
        else{
          gOfCar = 0;
        }
        
        if(selectedFood.get("FAT") != null){
          gOfFat = (int)Math.round((double)selectedFood.get("FAT"));
        }
        
        else{
          gOfFat = 0;
        }
        
        if(selectedFood.get("ENERC_KCAL") != null){
          calories = (int)Math.round((double)selectedFood.get("ENERC_KCAL"));
        }
        
        else{
          calories = 9*gOfFat + 4*gOfCar + 4*gOfPro;
        }
        
        System.out.println("\n" + addFood + "\nCalories: " + calories + "\nGrams of protein: " +gOfPro +"\nGrams of carbohydrates: " + gOfCar +"\nGrams of fat: " + gOfFat);
        Food food = new Food(calories, gOfCar, gOfFat, gOfPro, addFood);
        
        ng.acceptableFoods.add(food);
        
        System.out.println("Do you want to continue adding food from this list? Enter 'true' or 'false'.");
        continueAdd = sc.nextBoolean();
      }while(continueAdd == true);
    
    con.disconnect();
    
    return ng;
  }

  public static ExerciseGuide makeExercise(ExerciseGuide eg, int daysOfActivity, double currentWeight) throws IOException{
    /* Scraped data from jefit.com to create a exercise database for each muscle group.
     * Users should be presented with data and be able to select and add exercises to their ExerciseGuide.
     * Declaring the hashmap which will help in showing data to users.
     * "musclegroups" is an arraylist of strings that are different muscle groups.
     * Jsoup.connect allows you to create an HTML document object.
     * Elements and doc.select lets me to select specific 'elements' or tag type from an HTML file and create 
     * a list of "Elements".
     * This process is carried out for the exercises but with an updating URL.
     * Then the keys (muscleGroups) and values (ArrayLists of exercises for the muscle group) are added to the hashmap 
     * "exercises".
     */ 
    Scanner sc = new Scanner(System.in);
    HashMap<String, ArrayList<String>> exercises = new HashMap<String, ArrayList<String>>();
    ArrayList<String> muscleGroups = new ArrayList<String>();
    
    muscleGroups.add("Abs");
    muscleGroups.add("Back");
    muscleGroups.add("Biceps");
    muscleGroups.add("Chest");
    muscleGroups.add("Forearms");
    muscleGroups.add("Glutes");
    muscleGroups.add("Shoulders");
    muscleGroups.add("Triceps");
    muscleGroups.add("Upper Legs");
    muscleGroups.add("Lower Legs");
    muscleGroups.add("Cardio");
    
    for(int i = 0; i<11; ++i){
      String Url = "https://www.jefit.com/exercises/bodypart.php?id=" + i + "&exercises="+muscleGroups.get(i);
      ArrayList<String> tempArr = new ArrayList<String>();
      Document doc = Jsoup.connect(Url).userAgent("mozilla/17.0").get();
      Elements temp = doc.select("h4 a");
      
      for (Element exerciseList: temp){
        tempArr.add(exerciseList.getElementsByTag("a").first().text());
      }
      
      exercises.put(muscleGroups.get(i), tempArr);
      //System.out.println(muscleGroups.get(i));
      //System.out.println(tempArr.toString());
    }
   
    boolean add = false;
    int kindOfWorkout;
    
    do{
      System.out.println("Enter a number between 1 to " + daysOfActivity + " on which day you'd like to add an exercise to.");
      int addDay = sc.nextInt();
      
      System.out.println("What kind of exercises would you like to perform?\n");
      System.out.println("Enter 1 for 'Aerobic exercises'.\nThe primary focus is to tone muscles and burn calories."+"\nSome examples of aerobic exercises include jogging, swimming, full-body, cycling, etc."+"\nOxygen is the main energy source in aerobic exercise.\n");
      System.out.println("Enter 2 for 'Anaerobic exercises'.\nThe primary focus is to build muscle and burn fat."+"\nSome examples of anaerobic exercises are weight lifitng, and sprinting."+"\nAnaerobic exercises involve short bursts of energy and use glucose as the primary energy source.\n");
      System.out.println("It is is not reccommended to do Anaerobic exercises if you haven't exercised in awhile \nand are overweight.");
      kindOfWorkout = sc.nextInt();
      
      if(kindOfWorkout == 2){
        System.out.println(muscleGroups.toString());
        System.out.println("Type a muscle group you'd like to work that day and select an exercise from the list.\nType anything but Cardio.");
        String tempMuscleGroup = sc.nextLine();
        tempMuscleGroup = sc.nextLine();
        while(tempMuscleGroup.equals("Cardio")){
          System.out.println("Incorrect input for anaerobic, type a different muscle group.");
          tempMuscleGroup = sc.nextLine();
        }
        
        ArrayList<String> tempExercises = exercises.get(tempMuscleGroup);
        System.out.println(tempExercises.toString());
        System.out.println("Type the position of the exercise you'd like to add with the first exercise listed as 0.");
        int exerciseNumber = sc.nextInt();
        
        String exerciseName = tempExercises.get(exerciseNumber);
        
        System.out.println("How many sets would you like to do for this exercises. 3-4 sets is reccommended.");
        int sets = sc.nextInt();
        
        System.out.println("How many repetitions would you like to do per set? 8-12 is reccommended for building muscle and burning fat.");
        int reps = sc.nextInt();
        
        double exerciseWeight = currentWeight*0.7;
        
        Exercise tempExercise = new Exercise(exerciseName, tempMuscleGroup, "Anaerobic", reps, sets, exerciseWeight, 0.00);
        eg.addExercise(addDay, tempExercise);
      }
      
      else{
        String tempMuscleGroup = "Cardio";
        ArrayList<String> tempExercises = exercises.get(tempMuscleGroup);
        System.out.println(tempExercises.toString());
        System.out.println("Type the position of the exercise you'd like to add with the first exercise listed as 0.");
        int exerciseNumber = sc.nextInt();
        
        String exerciseName = tempExercises.get(exerciseNumber);
        
        System.out.println("How long do you want to perform this exercise? Enter a number of minutes in the format of xxx.xx");
        double time = sc.nextDouble();
        
        Exercise tempExercise = new Exercise(exerciseName, tempMuscleGroup, "Aerobic", 0, 0, 0, time);
        eg.addExercise(addDay, tempExercise);
      }
      
      System.out.println("Do you want to continue adding exercises? Enter 'true' for yes and 'false' for no.");
      add = sc.nextBoolean();
    }while(add == true);
    return eg;
  }
  
  public static void main(String args[]) throws IOException, ParseException{
    
    //Decleration of essential variables to create objects of person, NutritionGuide and FitnessPlan
    
    Scanner sc = new Scanner(System.in);
    String activityLevel;
    String name;
    int age;
    int daysOfActivity;
    double currentWeight;
    double goalWeight;  
    double heightInches;
    
    //User interaction to get information to assign objects. 
    
    System.out.println("\nHello! This health app will ask you some questions about your daily activity level\nand about you.");
    System.out.println("\nWhat is your name?");
    name = sc.nextLine();
    
    System.out.println("How long have you lived "+name+"? Enter a whole number value greater than 0");
    age = sc.nextInt();
    while(age <= 0){
      System.out.println("Incorrect age input:" + age + "\n Enter a number greater than 0");
      age = sc.nextInt();
    }
    
    System.out.println("How much do you weigh?");
    currentWeight = sc.nextDouble();
    while(currentWeight <=0){
      System.out.println("Incorrect input for weight try again.");
      currentWeight = sc.nextDouble();
    }
    
    System.out.println("What is your goal weight?");
    goalWeight = sc.nextDouble();
    while(goalWeight <= 0){
      System.out.println("Incorrect input for weight try again.");
      goalWeight = sc.nextDouble();
    }
    
    System.out.println("What is your current activity level?\n'Sedentary',\n'Light' (1-2 days a week),\n'Moderate' (3-5 days a week),\n or 'Very Active' (Daily)");
    activityLevel = sc.nextLine();
    activityLevel = sc.nextLine();
    
    System.out.println("How tall are you in inches? enter a value greater than zero!");
    heightInches = sc.nextDouble();
    while(heightInches<=0){
      System.out.println("Incorrect input. Enter a value greater than 0.");
      heightInches = sc.nextDouble();
    }
    
    //User interaction to get the number of days a person would want to exercise. Also checks to make sure the input is valid.
    
    System.out.println("How many days a week do you want to workout? 0 - 7");
    daysOfActivity = sc.nextInt();
    while(daysOfActivity < 0 || daysOfActivity > 7){
      System.out.println("Incorrect input for number of days! Enter a number value from 0 to 7.");
      daysOfActivity = sc.nextInt();
    }
    
    ExerciseGuide eg = new ExerciseGuide(daysOfActivity);
    
    if(daysOfActivity > 0){
      eg = makeExercise(eg, daysOfActivity, currentWeight);
    }
    
    NutritionGuide ng = new NutritionGuide(currentWeight, age, heightInches, activityLevel);
    
    System.out.println("\nThe amout of calories you need to maintain your weight is " + ng.getMaintainenceCalories());
    System.out.println("\nThe amount of calories you can eat daily to lose is " + ng.getOneLBPerWeekLossCalories());
    System.out.println("\nType a key ingredient in a food you'd like to add to a list of foods you can eat.");
    String searchFood = sc.nextLine();
    ng = makeFood(searchFood = sc.nextLine(), ng);
    
    Person user = new Person(name, age, currentWeight, goalWeight, ng, eg);
    
    
    //System.exit(0);
  }
}