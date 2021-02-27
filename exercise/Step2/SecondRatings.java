
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRater(ratingsfile);
    }
    
    // Return Number of movies read from the file
    public int getMovieSize()
    {
        return myMovies.size();
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
        for(Rater rater : myRaters)
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
        }
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRating)
    {
        ArrayList<Rating> myRating = new ArrayList<>();
        
        for(Movie m : myMovies)
        {
            String id = m.getID();
            double value = getAverageById(id,minimalRating);
            if(value > 0){
            Rating r = new Rating(id,value);
            myRating.add(r);}
        }
        
        return myRating;
    }
    
    //return title of the movie from its id
    public String getTitle(String id)
    {
        for(Movie m : myMovies)
        {
            if(m.getID().equals(id))
            {
                return m.getTitle();
            }
        }
        return "ID was not found";
    }
    
    //return id of the movie from the title
    public String getID(String title)
    {
        
        for(Movie m : myMovies)
        {
            String currTitle = m.getTitle();
            if(currTitle.equals(title))
            {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}



