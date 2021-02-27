
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {

    public void printAverageRating()
    {
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        
        System.out.println("Rater : "+RaterDatabase.size());
        System.out.println("Movies : "+MovieDatabase.size());
        
        ArrayList<Rating> rt = fr.getAverageRatings(1);
        Collections.sort(rt);
        for(Rating r : rt)
        {
            String id = r.getItem();
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(id));
        }
    
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        
        AllFilters af = new AllFilters();
        YearAfterFilter yf = new YearAfterFilter(1980);
        GenreFilter gf = new GenreFilter("Romance");
        af.addFilter(yf);
        af.addFilter(gf);
        ArrayList<Rating> rt = fr.getAverageRatingsByFilter(1,af);
        
        System.out.println("Raters : "+RaterDatabase.size());
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
    
    public void printSimilarRatings()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Raters : "+RaterDatabase.size());
        System.out.println("Movies : "+MovieDatabase.size());
        ArrayList<Rating> sr = fr.getSimilarRating("71",20,5);
        for(Rating r : sr)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID));System.out.print("\t"+mID);
        }
    }
    
    public void printSimilarRatingsByGenre()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Raters : "+RaterDatabase.size());
        System.out.println("Movies : "+MovieDatabase.size());
        GenreFilter gf = new GenreFilter("Mystery");
        ArrayList<Rating> sr = fr.getSimilarRatingsByFilter("964",20,5,gf);
        for(Rating r : sr)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID)+"\t"+r.getValue()+"\t"+MovieDatabase.getGenres(mID));
        }
    }
    
    public void printSimilarRatingsByDirector()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Raters : "+RaterDatabase.size());
        System.out.println("Movies : "+MovieDatabase.size());
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh,");
        ArrayList<Rating> sr = fr.getSimilarRatingsByFilter("120",10,3,df);
        for(Rating r : sr)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID)+"\t"+r.getValue()+"\t"+MovieDatabase.getDirector(mID));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinute()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Raters : "+RaterDatabase.size());
        System.out.println("Movies : "+MovieDatabase.size());
        
        GenreFilter gf = new GenreFilter("Drama");
        MinuteFilter mf = new MinuteFilter(80,160);
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        ArrayList<Rating> sr = fr.getSimilarRatingsByFilter("168",10,3,af);
        for(Rating r : sr)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID)+"\t"+r.getValue()+"\t"+MovieDatabase.getMinutes(mID));
            System.out.println(MovieDatabase.getGenres(mID));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes()
    {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Raters : "+RaterDatabase.size());
        System.out.println("Movies : "+MovieDatabase.size());
        
        MinuteFilter mf = new MinuteFilter(70,200);
        YearAfterFilter yf = new YearAfterFilter(1975);
        AllFilters af = new AllFilters();
        af.addFilter(mf);
        af.addFilter(yf);
        
        ArrayList<Rating> sr = fr.getSimilarRatingsByFilter("314",10,5,af);
        for(Rating r : sr)
        {
            String mID = r.getItem();
            System.out.println(MovieDatabase.getTitle(mID)+"\t"+r.getValue()+"\t"+MovieDatabase.getMinutes(mID));
            System.out.print(MovieDatabase.getYear(mID));
        }
    }
}
