
import java.util.Scanner;
import java.util.*;

/* Scraped data from jefit.com to create a exercise database for each muscle group.
 * Users should be presented with data and be able to select and add exercises to their ExerciseGuide.
 * 
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
     * Elements and doc.select allow me to select specific 'elements' or tag type from an HTML file and create 
     * a list of "Elements".
     * Then the program iterates through the list, grabs the text of each element and stores it in the muscleGroup
     * ArrayList.
     * The 'muscleGroups' Array is then sorted so the scraping of the list of exercises is in order with the muscleGroups
     * ArrayList.
     * Next the same process is carried out for the exercises but with an updating URL.
     * Then the keys (muscleGroups) and values (ArrayLists of exercises for the muscle group) are added to the hashmap 
     * "exercises".
     */ 
    
    
    HashMap<String, ArrayList<String>> exercises = new HashMap<String, ArrayList<String>>();
    ArrayList<String> muscleGroups = new ArrayList<String>();
    Document doc = Jsoup.connect("https://www.jefit.com/exercises/").userAgent("mozilla/17.0").get();
    Elements temp = doc.select("a.exerciseblueButton");
    
    for(Element muscleList:temp)
    {
     muscleGroups.add(muscleList.getElementsByTag("a").first().text());
     //System.out.println(i+ " " + exerciseList.getElementsByTag("a").first().text() );
    }
    muscleGroups.remove("View All Exercises");
    Collections.sort(muscleGroups);
    
    System.out.println(muscleGroups.toString());

    for(int i = 0; i<11; ++i)
    {
      String Url = "https://www.jefit.com/exercises/bodypart.php?id=" + i + "&exercises="+muscleGroups.get(i);
      ArrayList<String> tempArr = new ArrayList<String>();
      doc = Jsoup.connect(Url).userAgent("mozilla/17.0").get();
      temp = doc.select("h4 a");
     
      for (Element exerciseList: temp)
      {
        tempArr.add(exerciseList.getElementsByTag("a").first().text());
      }
      
      exercises.put(muscleGroups.get(i), tempArr);

    }
    
    //Decleration of essential variables to create objects of person, NutritionGuide and FitnessPlan
    
    Scanner sc = new Scanner(System.in);
    int age;
    int daysOfActivity;
    int kindOfWorkout;
    double currentWeight;
    double goalWeight;
    String activityLevel;
    String name;
    
    //User interaction to get information in order to instantiate objects. 
    
    System.out.println("Hello! This health app will ask you some questions about your daily activity level\n and about you.");
    System.out.println("What is your name?");
    name = sc.next();
    System.out.println("How long have you lived? Enter a whole number value greater than 0");
    age = sc.nextInt();
    System.out.println("What is your current activity level? Sedentary, Light (1-2 days a week), \nModerate (3-5 days a week), or Very Active (Daily)");
    activityLevel = sc.next();
    
    //Making sure that a valid input was entered for the age variable.
    
    while(age <= 0){
      System.out.println("Incorrect age input:" + age + "\n Enter a number greater than 0");
      age = sc.nextInt();
    }
    
   //User interaction to get the number of days a person would want to exercise. Also checks to make sure the input is valid.
    
    System.out.println("How many days a week do you want to workout? 0 - 7");
    daysOfActivity = sc.nextInt();
    while(daysOfActivity < 0 || daysOfActivity > 7){
      System.out.println("Incorrect input for number of days! Enter a number value from 0 to 7.");
      daysOfActivity = sc.nextInt();
    }
    
    
    if(daysOfActivity > 0){
      System.out.println("What kind of exercises would you like to perform?");
      System.out.println("Enter 1 for 'Aerobic exercises'.\nThe primary focus is to tone muscles and burn calories."+"\nSome examples of aerobic exercises include jogging, swimming, full-body, cycling, etc."+"\nOxygen is the main energy source in aerobic exercise.");
      System.out.println("Enter 2 for 'Anaerobic exercises'.\nThe primary focus is to build muscle and burn fat."+"\nSome examples of anaerobic exercises are weight lifitng, and sprinting."+"\nAnaerobic exercises involve short bursts of energy and use glucose as the primary energy source.");
      System.out.println("It is is not reccommended to do Anaerobic exercises if you haven't exercised in awhile \nand are overweight.");
      kindOfWorkout = sc.nextInt();
    }
    
    ExerciseGUide ng = new ExerciseGuide(daysOfActivity);
    boolean add = true;
    
    while(daysOfActivity > 0 && (add)){
      System.out.println("Enter a number between 1 to " + daysOfActivity + " on which day you'd like to add an exercise to.");
      int addDay = sc.nextInt();
      System.out.println(muscleGroups.toString());
      System.out.println("Type a muscle group you'd like to work that day and select an exercise from the list.");
      String tempMuscleGroup = sc.next();
      ArrayList<String> tempExercises = exercises.get(tempMuscleGroup);
      System.out.println(tempExercises.toString());
      System.out.println("Type the position of the exercise you'd like to add with the first exercise listed as 0.");
      int exerciseNunmber = sc.next();
    }
    
    System.out.println("How much do you weigh?");
    currentWeight = sc.next();
    
    System.out.println("What is your goal weight?");
    goalWeight = sc.next();
    
    
  }
}