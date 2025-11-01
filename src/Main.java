//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        float[][] matrix = new float[][]{{1,3,2},{-1,2,-1},{1,-1,2},{-1,5,-2}};
        Tucker t = new Tucker(matrix);

        t.print();

        t.pivot(0,2);
        t.print();

        t.pivot(1,0);
        t.print();

        t.pivot(0,1);
        t.print();
    }
}