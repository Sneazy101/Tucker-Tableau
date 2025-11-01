public class Tucker {
    float[][] tab;
    int m , n;
    String[] vars;
    String[] slack;


    public Tucker(int m , int n) {
        tab = new float[m][n];
        this.m = m;
        this.n = n;
    }

    public Tucker(float[][] tableau) {
        tab = tableau;
        this.m = tab.length;
        this.n = tab[0].length;
        vars =  new String[n];
        slack = new String[m];
        for (int i = 0; i < n; i++) {
            vars[i] = "X" + String.valueOf(i+1);
        }
        for (int i = 0; i < m; i++) {
            slack[i] = "t" + String.valueOf(i+1);
        }
        vars[n-1] = "-1";
        slack[m-1] = " f";
    }

//    public void SimplexAlgorithm(){
//        boolean optimal = false;
//        while(true){
//            for(int i = 0 ; i < m ; i++){
//                if(tab[i][n-1] < 0){
//                    for
//                }
//            }
//
//
//                for(int i = 0; i < n-1; i++){
//                    if(tab[m-1][i] > 0) optimal = false;
//                }
//                if(optimal) {
//                    break;
//                }
//            }
//        }

    public void pivot(int x, int y){
        String tempStr = slack[y];
        slack[y] = vars[x];
        vars[x] = tempStr;
        float[][] temp = new float[m][n];
        temp[y][x] = 1/tab[y][x];
        for(int i = 0; i  < tab[0].length; i++){
            if (i == x){continue;}
            temp[y][i] = tab[y][i]/ tab[y][x];
        }
        for(int i = 0; i  < tab.length; i++){
            if (i == y){continue;}
            temp[i][x] = -1 * (tab[i][x]/tab[y][x]);
        }
        for(int i = 0; i  < tab.length; i++){
            for(int j = 0; j < tab[0].length; j++){
                if (i == y || j ==x){continue;}
                temp[i][j] = tab[i][j] - (((tab[i][x] * tab[y][j])/tab[y][x]));
            }
        }
        tab = temp;
    }

    public void print() {
        Printer p  = new Printer();
        p.print(tab, vars,slack, m ,n);
    }
}
