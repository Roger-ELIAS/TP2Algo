import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<String> trigrames(String mot){
        List<String> list = new ArrayList<String>();

        String motchevrons = '<'+mot+'>';
        for (int i=1;i<motchevrons.length()-1;++i){
            list.add(motchevrons.substring(i-1,i+2));
        }
        //System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        String [] deuxmots = {"chien", "schium"};
        System.out.println(DistanceLevenshtein.distance("honda","hyundai"));
        trigrames("coucou");

        ArrayList<String> dico = new ArrayList<String>();
        HashSet<String> trigs = new HashSet<String>();
        HashMap<String,List<String>> trigcomm = new HashMap<String,List<String>>();

        Scanner sc = null;
        try {
            sc = new Scanner(new File("dico.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String str = sc.next();
            dico.add(str);
            trigs.addAll(trigrames(str));
        }

        for(String trig: trigs){
            List<String> motconttrig = new ArrayList<String>();
            for(String mot: dico){
                if(trigrames(mot).contains(trig))
                    motconttrig.add(mot);
            }
            trigcomm.put(trig,motconttrig);
        }


        //dico.put(trigrames("coucou"),"coucou");
        System.out.println(dico);
        System.out.println(trigcomm);
    }
}
