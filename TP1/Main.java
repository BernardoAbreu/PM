
public class Main{

    public static void main(String[] args) {

        Monopoly monopoly = new Monopoly(3,100);

        monopoly.printStatements();
        monopoly.printPlayerPositions();
        System.out.println();

        monopoly.play(1,1);
        monopoly.printStatements();
        monopoly.printPlayerPositions();
        System.out.println();

        monopoly.play(2,2);
        monopoly.printStatements();
        monopoly.printPlayerPositions();
        System.out.println();

        monopoly.play(3,3);
        monopoly.printStatements();
        monopoly.printPlayerPositions();
        System.out.println();


        monopoly.play(1,2);
        monopoly.printStatements();
        monopoly.printPlayerPositions();
        System.out.println();

        monopoly.play(1,3);
        monopoly.printStatements();
        monopoly.printPlayerPositions();
        System.out.println();
//
//        monopoly.play(1,3);
//        monopoly.printStatements();
//        monopoly.printPlayerPositions();
//        System.out.println();
//
//        monopoly.play(1,3);
//        System.out.println();
//        monopoly.printStatements();
//        System.out.println();
//        monopoly.printPlayerPositions();
//        System.out.println();

    }
}
