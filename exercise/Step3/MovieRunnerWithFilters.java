
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
        
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        System.out.println("Rater : "+tr.getRaterSize());
         
        MovieDatabase.initialize(moviefile);
        System.out.println("Movies : "+MovieDatabase.size());
        
        ArrayList<Rating> rt = tr.getAverageRatings(35);
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
        }
    
    }
    
    public void printAverageRatingsByYear()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        YearAfterFilter y = new YearAfterFilter(2000);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(20,y);
        
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
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(20,gf);
        
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
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        MinuteFilter mf = new MinuteFilter(105,135);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(5,mf);
        
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
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(4,df);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            if(r.getValue() < 1){continue;}
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
            System.out.println("\t"+MovieDatabase.getDirector(id));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        AllFilters af = new AllFilters();
        YearAfterFilter yf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(yf);
        af.addFilter(gf);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(8,af);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        int c =0;
        for(Rating r : rt)
        {
            String id = r.getItem();
            if(r.getValue() < 1) { c++;continue;}
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(id)+MovieDatabase.getYear(id)+
                MovieDatabase.getGenres(id));
        }
        System.out.println(rt.size()-c);
    }
    
    public void printAverageRatingsByDirectorsAndMinutes()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        AllFilters af = new AllFilters();
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinuteFilter mf = new MinuteFilter(90,180);
        af.addFilter(df);
        af.addFilter(mf);
        ArrayList<Rating> rt = tr.getAverageRatingsByFilter(3,af);
        
        System.out.println("Raters : "+tr.getRaterSize());
        System.out.println("Movies : "+MovieDatabase.size());
        System.out.println("Found "+rt.size()+" Movies");
        Collections.sort(rt);
        int c =0;
        for(Rating r : rt)
        {
            String id = r.getItem();
            if(r.getValue() < 1) { c++;continue;}
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(id)+MovieDatabase.getMinutes(id)+
                MovieDatabase.getDirector(id));
            }
            System.out.println(rt.size()-c);
    }
}
