
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {

    private ArrayList<EfficientRater> myRaters;
    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        //myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRater(ratingsfile);
    }
    
    // Return number of raters
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    private double getAverageById(String id, int minimalRating)
    {
        double avg = 0.0;
        int raters = 0;
        double sum = 0.0;
        // determine number of raters rate the movie 
        for(EfficientRater rater : myRaters)
        {
            if(rater.hasRating(id))
            {
                raters++;
                sum += rater.getRating(id);
            }
        }
        //check for minimal raters condition
        if(raters >= minimalRating)
        {
            avg = sum / raters;
            return avg;
        }
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRating)
    {
        ArrayList<Rating> myRating = new ArrayList<>();
        
        for(String i : movies)
        {
            Movie m = MovieDatabase.getMovie(i);
            String id = m.getID();
            double value = getAverageById(id,minimalRating);
            if(value > 0){
                Rating r = new Rating(id,value);
                myRating.add(r);
            }
        }
        
        return myRating;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRating, Filter filterCriteria)
    {
        ArrayList<Rating> rating = new ArrayList<>();
        ArrayList<String> ans = MovieDatabase.filterBy(filterCriteria);
        for(String id : ans)
        {
            double value = getAverageById(id,minimalRating);
            Rating r = new Rating(id,value);
            rating.add(r);
        }
        return rating;
    }
}
