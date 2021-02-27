
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {

    public void printAverageRating()
    {
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        System.out.println("Rater : "+tr.getRaterSize());
         
        MovieDatabase.initialize(moviefile);
        System.out.println("Movies : "+MovieDatabase.size());
        
        ArrayList<Rating> rt = tr.getAverageRatings(1);
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
        }
    
    }
    
    public void printAverageRatingsByYear()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        YearAfterFilter y = new YearAfterFilter(2000);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(1,y);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
        }
    }
    
    public void printAverageRatingsByGenres()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        GenreFilter gf = new GenreFilter("Crime");
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(1,gf);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
            System.out.println("\t"+MovieDatabase.getGenres(id));
        }
    }
    
    public void printAverageRatingsByMinutes()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        MinuteFilter mf = new MinuteFilter(110,170);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(1,mf);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id) + MovieDatabase.getMinutes(id));
        }
    }
    
    public void printAverageRatingsByDirectors()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        DirectorsFilter df = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(1,df);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
            System.out.println("\t"+MovieDatabase.getDirector(id));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        AllFilters af = new AllFilters();
        YearAfterFilter yf = new YearAfterFilter(1980);
        GenreFilter gf = new GenreFilter("Romance");
        af.addFilter(yf);
        af.addFilter(gf);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(1,af);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(id)+MovieDatabase.getYear(id)+
                MovieDatabase.getGenres(id));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        AllFilters af = new AllFilters();
        DirectorsFilter df = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        MinuteFilter mf = new MinuteFilter(110,170);
        af.addFilter(df);
        af.addFilter(mf);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(1,af);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(id)+MovieDatabase.getMinutes(id)+
                MovieDatabase.getDirector(id));
        }
    }
}
