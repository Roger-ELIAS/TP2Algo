import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DicoTrig {

    public ArrayList<String> dico;
    public HashMap<String,List<String>> trigcomm;

    public static int trigrammesCommuns(String mot1, String mot2){
        List<String> trigs1 = trigrames(mot1);
        List<String> trigs2 = trigrames(mot2);
        int cpt = 0;
        for(String trig1: trigs1){
            for(String trig2: trigs2){
                if(trig1.equals(trig2))
                    cpt++;
            }
        }
        return cpt;
    }

    public static List<String> trigrames(String mot){
        List<String> list = new ArrayList<String>();

        String motchevrons = '<'+mot+'>';
        for (int i=1;i<motchevrons.length()-1;++i){
            list.add(motchevrons.substring(i-1,i+2));
        }
        //System.out.println(list);
        return list;
    }

    public DicoTrig(){
        dico = new ArrayList<String>();
        trigcomm = new HashMap<String,List<String>>();

        Scanner sc = null;
        try {
            sc = new Scanner(new File("dico.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNext()) {
            String mot = sc.next();
            dico.add(mot);
            List<String> trigs = new ArrayList<String>();
            trigs.addAll(trigrames(mot));
            for(String trig: trigs) {
                List<String> tab;
                if (trigcomm.containsKey(trig))
                    tab = trigcomm.get(trig);
                else
                    tab = new ArrayList<String>();
                tab.add(mot);
                trigcomm.put(trig,tab);
            }
        }


    }
}
