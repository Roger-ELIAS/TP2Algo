import java.util.Comparator;

public class DistanceLevenshteinComparator implements Comparator<String> {

    private String mot;
    public DistanceLevenshteinComparator(String mot){
        this.mot=mot;
    }
    @Override
    public int compare(String x, String y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (DistanceLevenshtein.distance(mot,x) > DistanceLevenshtein.distance(mot,y))
        {
            return 1;
        }
        if (DistanceLevenshtein.distance(mot,x) < DistanceLevenshtein.distance(mot,y))
        {
            return -1;
        }
        return 0;
    }
}