import java.util.Scanner;
import java.util.*;

/* Scraped data from jefit.com to create a exercise database for each muscle group.
 * Users should be presented with data and be able to select and add exercises to their ExerciseGuide.
 */

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main{
  public static void main(String args[]) throws IOException{
    
    /* Declaring the hashmap which will help in showing data to users.
     * "musclegroups" is an arraylist of strings that are different muscle groups.
     * Jsoup.connect allows you to create an HTML document object.
     * Elements and doc.select lets me to select specific 'elements' or tag type from an HTML file and create 
     * a list of "Elements".
     * This process is carried out for the exercises but with an updating URL.
     * Then the keys (muscleGroups) and values (ArrayLists of exercises for the muscle group) are added to the hashmap 
     * "exercises".
     */ 
    
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
    
    /*Next we'll scrape from a website to get a list of foods a person can select foods from and add to their nutritionGuide.
     *Couldn't find an API for exercises but if their is an api for foods I'll try to implement Java based request to the api.
    */
    
    ArrayList<String> food = new ArrayList<String>();
    
    //Decleration of essential variables to create objects of person, NutritionGuide and FitnessPlan
    
    Scanner sc = new Scanner(System.in);
    String activityLevel;
    String name;
    int age;
    int daysOfActivity;
    double currentWeight;
    double goalWeight;
    
    //User interaction to get information to assign objects. 
    
    System.out.println("Hello! This health app will ask you some questions about your daily activity level\nand about you.");
    System.out.println("What is your name?");
    name = sc.nextLine();
    
    System.out.println("How long have you lived? Enter a whole number value greater than 0");
    age = sc.nextInt();
    while(age <= 0){
      System.out.println("Incorrect age input:" + age + "\n Enter a number greater than 0");
      age = sc.nextInt();
    }
    
    System.out.println("How much do you weigh?");
    currentWeight = sc.nextDouble();
    while(currentWeight <=0){
      System.out.println("Incorrect Input for weight try again.");
      currentWeight = sc.nextDouble();
    }
    
    System.out.println("What is your goal weight?");
    goalWeight = sc.nextDouble();
    while(goalWeight <= 0){
      System.out.println("Incorrect Input for weight try again.");
      goalWeight = sc.nextDouble();
    }
    
    System.out.println("What is your current activity level? Sedentary, Light (1-2 days a week),\nModerate (3-5 days a week), or Very Active (Daily)");
    activityLevel = sc.nextLine();
    activityLevel = sc.nextLine();
    
    //User interaction to get the number of days a person would want to exercise. Also checks to make sure the input is valid.
    
    System.out.println("How many days a week do you want to workout? 0 - 7");
    daysOfActivity = sc.nextInt();
    while(daysOfActivity < 0 || daysOfActivity > 7){
      System.out.println("Incorrect input for number of days! Enter a number value from 0 to 7.");
      daysOfActivity = sc.nextInt();
    }
    
    
    if(daysOfActivity > 0){
      
      ExerciseGuide eg = new ExerciseGuide(daysOfActivity);
      boolean add = true;
      int kindOfWorkout;
      
      while((add)){
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
        
        System.out.println("Do you want to continue to add exercises to your exercise guide? enter 'true' for yes and 'false' for no.");
        add = sc.nextBoolean();
        
      }
      
    }
  }
}