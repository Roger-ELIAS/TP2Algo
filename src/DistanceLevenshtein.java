public class DistanceLevenshtein {

    public static int distance(String mot, String mot2 ) {
        int [] opt = new int[mot.length()];

        for(int j = 0; j<opt.length;j++)
            opt[j]=j;

        for(int i =1; i<mot.length();i++){
            opt[0]=1;
            for(int k=i; k< mot2.length();k++){
                int resultat;
            }

        }


        return 0;
    }

}