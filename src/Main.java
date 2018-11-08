import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<String> corriger(String mot, DicoTrig dicoTrig){
        List<String> result = new ArrayList<String>();
        List<String> trigs = new ArrayList<String>();
        List<Set<String>> motsTrigsCommuns = new ArrayList<Set<String>>();
        PriorityQueue<String> motsTriesDistance = new PriorityQueue<String>(new DistanceLevenshteinComparator(mot));
        if(dicoTrig.dico.contains(mot)){
            result.add(mot);
            return result;
        }
        trigs = DicoTrig.trigrames(mot);
        
        motsTrigsCommuns.add(new HashSet<String>());
        for(String trig: trigs) {
        	if(!dicoTrig.trigcomm.containsKey(trig))
        		continue;
        	for(String mottrig: dicoTrig.trigcomm.get(trig)){
        		boolean contenu = false;
        		for(int i=0; i<motsTrigsCommuns.size();++i){
        			if(motsTrigsCommuns.get(i).contains(mottrig)){
        				contenu = true;
        				motsTrigsCommuns.get(i).remove(mottrig);
        				if(i+1>=motsTrigsCommuns.size()-1)
        					motsTrigsCommuns.add(new HashSet<String>());
        				motsTrigsCommuns.get(i+1).add(mottrig);
        				break;
        			}
        		}
        		if(!contenu)
        			motsTrigsCommuns.get(0).add(mottrig);
        	}
        }
        
        for(int ii=motsTrigsCommuns.size()-1; ii>=0;--ii){
        	motsTriesDistance.addAll(motsTrigsCommuns.get(ii));
        	if(motsTriesDistance.size()>100) break;
        }
        //System.out.println(motsTriesDistance.size());
        

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
        long currentTime = System.nanoTime();
        while (sc.hasNext()) {
            String mot = sc.next();
            corriger(mot,bob);
            //System.out.println(mot+"=>"+corriger(mot,bob));
        }
        long elapsedTime = System.nanoTime()-currentTime;
        System.out.println("Temps de correction : "+ (double)(elapsedTime/1000000)+"ms");
        /*
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String line = scanner.nextLine();
                System.out.println(corriger(line, bob));
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        }*/
    }
}
