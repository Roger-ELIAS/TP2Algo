import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<String> corriger(String mot, DicoTrig dicoTrig){
        List<String> result = new ArrayList<String>();
        List<String> trigs = new ArrayList<String>();
        PriorityQueue<String> motsTrigsCommuns = new PriorityQueue<String>(new TrigCommunComparator(mot));
        PriorityQueue<String> motsTriesDistance = new PriorityQueue<String>(new DistanceLevenshteinComparator(mot));
        if(dicoTrig.dico.contains(mot)){
            result.add(mot);
            return result;
        }
        trigs = DicoTrig.trigrames(mot);
        for(String trig: trigs) {
            if(!dicoTrig.trigcomm.containsKey(trig))
                continue;
            motsTrigsCommuns.addAll(dicoTrig.trigcomm.get(trig));
        }

        for(int i=0; i<100; ++i){
            if(motsTrigsCommuns.isEmpty()) break;
            motsTriesDistance.add(motsTrigsCommuns.poll());
        }

        while(result.size()!=5){
            if(motsTriesDistance.isEmpty())
                break;
            if(result.contains(motsTriesDistance.peek()))
                motsTriesDistance.poll();
            else
                result.add(motsTriesDistance.poll());
        }
        return result;
    }


    public static void main(String[] args) {

        DicoTrig bob = new DicoTrig();
        //System.out.println(bob.trigcomm.get("uji"));
        //System.out.println(DicoTrig.trigrammesCommuns("apiculteur","apication"));
        //System.out.println(corriger("chienz",bob));
        Scanner sc = null;
        try {
            sc = new Scanner(new File("fautes.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNext()) {
            String mot = sc.next();
            System.out.println(corriger(mot,bob));
        }
    }
}
