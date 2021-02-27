
/**
 * Write a description of MuniteFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinuteFilter implements Filter{

    private int Min;
    private int Max;
    public MinuteFilter(int min, int max)
    {
        Min = min;
        Max = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        int minute = MovieDatabase.getMinutes(id);
        return (minute>=Min && minute<=Max);
    }
}
