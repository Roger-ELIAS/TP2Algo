public class DistanceLevenshtein {

    public static int distance(String mot, String mot2 ) {
        int [][] opt = new int[mot.length()+1][mot2.length()+1];

        for(int j = 0; j<opt.length;j++)
            opt[j][0]=j;
        for(int j = 0; j<opt[0].length;j++)
            opt[0][j]=j;

        for(int i =0; i<mot.length();i++){
            for(int k=0; k< mot2.length();k++){
                int cpt = 1;
                if (mot.substring(i,i+1).equals(mot2.substring(k,k+1))){
                    cpt = 0;
                }
                //System.out.println((mot.substring(i,i+1) +"=="+ mot2.substring(k,k+1)));
                //System.out.println("cpt"+cpt);
                //System.out.println(Math.min(Math.min(opt[i][k],opt[i][k+1]),opt[i+1][k]));
                opt[i+1][k+1]=Math.min(Math.min(opt[i][k],opt[i][k+1]),opt[i+1][k])+cpt;


            }

        }
        /*
        for(int j = 0; j<mot.length()+1;j++){
            for(int i = 0; i<mot2.length()+1;i++)
                System.out.print("|"+opt[j][i]);
            System.out.println("|");
        }
        */
        return opt[mot.length()][mot2.length()];
    }

}