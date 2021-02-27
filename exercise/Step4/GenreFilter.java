
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter{

    private String myGenre;
    public GenreFilter(String genre)
    {
        myGenre = genre;
    }
    @Override
    public boolean satisfies(String id) {
        String g = MovieDatabase.getGenres(id);
        return g.contains(myGenre);
    }
}
