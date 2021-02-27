
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {

    ArrayList<EfficientRater> myRaters = RaterDatabase.getRaters();
    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
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
    
    private int dotProduct(EfficientRater me, EfficientRater r)
    {
        int product=0;
        ArrayList<String> meRated = me.getItemsRated();
        for(String item : meRated)
        {
            if(r.hasRating(item))
            {
                product += ((me.getRating(item)-5)*(r.getRating(item)-5));
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> answer = new ArrayList<Rating>();
        EfficientRater me = RaterDatabase.getRater(id);
        for(EfficientRater r : RaterDatabase.getRaters())
        {
            if(!me.getID().equals(r.getID()))
            {
                int p = dotProduct(me,r);
                if(p > 0)
                {
                    Rating current = new Rating(r.getID(),p);
                    answer.add(current);
                }
            }
        }
        
        Collections.sort(answer,Collections.reverseOrder());
        return answer;
    }
    
    public ArrayList<Rating> getSimilarRating(String id, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> answer = new ArrayList<>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movieID : movies)
        {
            double wAverage = 0;
            double sum = 0;
            int countRaters = 0;
            
            for(int i=0; i<numSimilarRaters; i++)
            {
                Rating r = list.get(i);
                double weight = r.getValue();
                String raterID = r.getItem();
                EfficientRater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(movieID)){
                    countRaters++;
                    sum += weight * myRater.getRating(movieID);
                }
            }
            
            if(countRaters >= minimalRaters)
            {
                wAverage = sum / countRaters;
                answer.add(new Rating(movieID,wAverage));
            }
        }
        Collections.sort(answer, Collections.reverseOrder() );
        return answer;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter
     filterCriteria)
    {
        ArrayList<Rating> answer = new ArrayList<>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String movieID : movies)
        {
            double wAverage = 0;
            double sum = 0;
            int countRaters = 0;
            
            for(int i=0; i<numSimilarRaters; i++)
            {
                Rating r = list.get(i);
                double weight = r.getValue();
                String raterID = r.getItem();
                EfficientRater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(movieID)){
                    countRaters++;
                    sum += weight * myRater.getRating(movieID);
                }
            }
            
            if(countRaters >= minimalRaters)
            {
                wAverage = sum / countRaters;
                answer.add(new Rating(movieID,wAverage));
            }
        }
        Collections.sort(answer, Collections.reverseOrder() );
        return answer;
    }
}
