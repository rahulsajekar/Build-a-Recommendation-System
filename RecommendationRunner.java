
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate()
    {
        ArrayList<String> moviesToRate = new ArrayList<String>();
        String[] movies = {"1065073","2084970","2582846","1980929","1690953","2395427","1951264","1453405","1790864","2017038"};
        for(int i=0; i<10; i++){
            moviesToRate.add(movies[i]);
        }
        return moviesToRate;
    }
    
    public void printRecommendationsFor (String webRaterID)
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        
        ArrayList<Rating> recommendedMovies = fr.getSimilarRating(webRaterID,5,3);
        
        if(recommendedMovies.size()==0)
        {
            System.out.println("No movies to recommend");
        }
        else{
        
            System.out.println("<h2>Recommended Movies:</h2>");
            System.out.println("<table><tr><th>Title</th><th>Year</th><th>URL</th></tr>");
            
            for(Rating r : recommendedMovies)
            {
                String mID = r.getItem();
                String title = MovieDatabase.getTitle(mID);
                int year = MovieDatabase.getYear(mID);
                String url = MovieDatabase.getPoster(mID);
                
                System.out.println("<tr><td>"+title+"</td><td>"+year+"</td><td>"+url+"</td></tr>");
            }
            
            System.out.println("</table>");
        }
    }
}
