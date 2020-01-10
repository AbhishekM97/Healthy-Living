

import java.util.Scanner;
import java.util.*;

/*Try to implement API requests to a exercise and nutrition database so user can select different exercises and
 *foods to eat. Also possible to scrape a webpage related to exercise and nutrition and store the HTML items as a list.
*/

//Needed to create an API request

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main{
  public static void main(String args[]) throws IOException{
    
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
      /* Sucessfully scraped exercises to match the muscle groups that they work from jefit.com
       * Next step is to put the exercises in the hashmap according to the muscle groups they work as the keys.
       * From there once users give certain input they should be presented with the exercises array which was scraped.
       * Then begins the steps to work on the users input to add exercises to their exerciseGuide.
      */
      
      exercises.put(muscleGroups.get(i), tempArr);
     // System.out.println(tempArr.toString());
    }
    
    
    

   System.exit(0);
    /*
     * Scraped muscle groups and stored into exercises array.
     * System.exit(0);
     */
    
    //Decleration of essential variables to create objects of person, NutritionGuide and FitnessPlan
    
    Scanner sc = new Scanner(System.in);
    int age;
    int daysOfActivity;
    int kindOfWorkout;
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
      System.out.println("Enter 1 for 'Aerobic exercises'.\nThe primary focus is to tone muscles and burn calories.");
      System.out.println("Enter 2 for 'Anaerobic exercises'.\nThe primary focus is to build muscle and burn fat.");
      System.out.println("It is is not reccommended to do Anaerobic exercises if you haven't exercised in awhile \nand are overweight.");
      kindOfWorkout = sc.nextInt();
    }
    
  }
}