
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {

    public void printAverageRating()
    {
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        SecondRatings sr = new SecondRatings(moviefile,ratingsfile);
        
        System.out.println("Movies : "+sr.getMovieSize()+" Rater : "+sr.getRaterSize());
         
    
        ArrayList<Rating> rt = sr.getAverageRatings(3);
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + sr.getTitle(id));
        }
    
    }
    
    public void getAverageRatingOneMovie()
    {
       String moviefile = "data/ratedmovies_short.csv";
       String ratingsfile = "data/ratings_short.csv";
       SecondRatings sr = new SecondRatings(moviefile,ratingsfile); 
       
       String title = "The Godfather";
       String id = sr.getID(title);
       
       ArrayList<Rating> rt = sr.getAverageRatings(3);
       for(Rating r : rt)
       {
           if(r.getItem().equals(id))
           {
               System.out.println(r.getValue());
           }
       }
    }
}
