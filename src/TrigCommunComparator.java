import java.util.Comparator;

public class TrigCommunComparator implements Comparator<String>
{
    private String mot;
    public TrigCommunComparator(String mot){
        this.mot=mot;
    }
    @Override
    public int compare(String x, String y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (DicoTrig.trigrammesCommuns(mot,x) < DicoTrig.trigrammesCommuns(mot,y))
        {
            return 1;
        }
        if (DicoTrig.trigrammesCommuns(mot,x) > DicoTrig.trigrammesCommuns(mot,y))
        {
            return -1;
        }
        return 0;
    }
}