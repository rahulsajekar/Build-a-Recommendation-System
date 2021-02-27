
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    
    // Hashmap of director and no.of movies directed by that director
    private HashMap<String, Integer> dir = new HashMap<>();
    // return an ArrayList of type Movie with all of the movie data from the file.
    public ArrayList<Movie> loadMovies(String filename)
    {
        FileResource fr = new FileResource(filename); 
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> Movies = new ArrayList<Movie>();
        for(CSVRecord record : parser)
        {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie curr = new Movie (id,title,year,genre,director,country,poster,minutes);
            Movies.add(curr);
        }
        return Movies;
    }
    
    public void testLoadMovies()
    {
        String filename = "data/ratedmoviesfull.csv";
        //String filename = "data/ratedmovies_short.csv";
        ArrayList<Movie> myMovies = loadMovies(filename);
        
        
         System.out.println("Total Movies : "+myMovies.size());
        /*for(Movie m : myMovies)
        {
            System.out.println(m.toString());
        }*/
        
       
        // Number of Movies with Comedy Genre
        int count = 0;
        for(Movie m : myMovies)
        {
            String genre = m.getGenres();
            if(genre.indexOf("Comedy") != -1)
            {
                count++;
            }
        }
        System.out.println("No.of Movies with Comedy Genres: "+count);
        
        //  Number of Movies greater than 150 minutes
        int count1 = 0;
        for(Movie m : myMovies)
        {
            int minutes = m.getMinutes();
            if(minutes > 150)
            {
                count1++;
            }
        }
        System.out.println("Number of movies greater than 150 minutese: "+count1);
        
        //maximum number of movies by any director
        for(Movie m : myMovies)
        {
            String director = m.getDirector();
            for(int i=0; i<director.length(); )
            {
                int k = director.indexOf(",",i);
                if(k < 0)
                {
                    String d = director.substring(i,director.length());
                    addDirector(d);
                    break;
                }
                else
                {
                    String d = director.substring(i,k);
                    addDirector(d);
                    i=k;
                }
            }
        }
        printMaxDirector();
    }
    public void addDirector(String d)//Add Director to the hashmap
    {
        if(dir.containsKey(d))
        {
           dir.put(d,(dir.get(d)+1));
        }
        else
        {
            dir.put(d,1);
        }
    }
    public void printMaxDirector()// Print the director name and no.of movies directed
    {
        int max=0;
        for(String s : dir.keySet())
        {
            int curr = dir.get(s);
            if(curr > max){
                max = curr;
            }
        }
        System.out.println("Maximum movies directed by any director : "+max);
        for(String s : dir.keySet()){
            if(dir.get(s) == max)
            {
                System.out.println(s);
            }
        }
    }
    
    // Load Rater info from csv file into arraylist
    public ArrayList<EfficientRater> loadRater(String filename)
    {
        ArrayList<EfficientRater> rater = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser)
        {
            String id = record.get("rater_id");
            String item = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if(rater.size() == 0 )
            {
                EfficientRater curr = new EfficientRater(id);
                rater.add(curr);
                curr.addRating(item,rating);
            }
            int flag=0;
            for(EfficientRater r: rater)
            {
                if(r.getID().equals(id))
                {
                    if(!r.hasRating(item))
                    {
                        r.addRating(item,rating);
                        flag=0;
                        break;
                    }
                }
                else{
                    flag=1;
                }
            }
            if(flag == 1)
            {
                EfficientRater curr = new EfficientRater(id);
                rater.add(curr);
                curr.addRating(item,rating);
            }
        }
        return rater;
    }
    
    public void testLoadRaters()
    {
        //String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        ArrayList<EfficientRater> myRater = loadRater(filename);
        
        System.out.println("Number of raters are : "+myRater.size());
        
        // Number of ratings per rater
        System.out.println("Rater ID\tTotal Rating");
        for(EfficientRater rater : myRater)
        {
            System.out.println(rater.getID() +"\t"+ rater.numRatings());
        }
        
        //Maximum ratings by any rater
        int max=0;
        for(EfficientRater rater : myRater)
        {
            int curr = rater.numRatings();
            if(curr > max){
                max = curr;
            }
        }
        System.out.println("Max Number of rating : "+max);
        for(EfficientRater rater : myRater)
        {
            int n = rater.numRatings();
            if(n == max){
                System.out.println(rater.getID());
            }
        }
        
        //Maximum rating a particular movie has
        int ratedBy=0;
        for(EfficientRater rater : myRater)
        {
            
            if(rater.hasRating("1798709"))
            {
                ratedBy++;
            }
        }
        System.out.println(ratedBy + " raters rated the movie 1798709");
        
        //How many movies are rated by these raters
        ArrayList<String> moviesRated = new ArrayList<String>();
        for(EfficientRater rater : myRater){
            ArrayList<String> movies = rater.getItemsRated();
            for(String s : movies)
            {
                if(!moviesRated.contains(s))
                {
                    moviesRated.add(s);
                }
            }
        }
        System.out.println("Number of movies rated : "+moviesRated.size());
    }
}
