import java.util.Scanner;
 
class Board {
 
    int matrix[][];
    int soldiers;
 
    public Board(int soldiers) {
        matrix = new int[20][20];
        this.soldiers = soldiers;
    }
 
    void displayMatrix(){
        int i, j;
        for(i=0; i<soldiers; i++){
            for(j=0; j<soldiers; j++){
                if(matrix[i][j] == 1)
                    System.out.print(" 1 ");
                else
                    System.out.print(" 0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print("\n");
    }
 
    void reset(){
        for(int i=0; i<soldiers; i++){
            for(int j=0; j<soldiers; j++){
                matrix[i][j] = 0;
            }
        }
    }
 
    boolean isValidPlace(int row, int col){
        int i,j;
 
        //Checking horizontally
        for(i=col; i>=0; i--){
            if(matrix[row][i] == 1)
                return false;
        }
 
        //checking left diagonal
        for(i=row, j=col; i>=0 && j>=0; i--,j--){
            if(matrix[i][j] == 1)
                return false;
        }
 
        //checking right diagonal
        for(i=row, j=col; i<soldiers && j>=0; i++,j--){
            if(matrix[i][j] == 1)
                return false;
        }
 
        return true;
    }
}
 
 class Soldier {
 
    int soldiers;
    boolean flag;
    Board board;
 
    public Soldier(int soldiers) {
        this.flag = false;
        this.board = new Board(soldiers);;
        this.soldiers = soldiers;
    }
 
    void play(){
 
        int i;
        hasSolution(0,0);
        if(!flag)
            System.out.println("No Solution");
 
    }
 
    boolean hasSolution(int ctr, int colSoldier){ 
        if(colSoldier == soldiers){
            flag = true;
            board.displayMatrix();
            return false;
        }
 
        int i,j;
        for(i=ctr; i<soldiers; i++){
            if(board.isValidPlace(i,colSoldier)){
                board.matrix[i][colSoldier] = 1;
                if(hasSolution(0,colSoldier+1))
                    return true;
                board.matrix[i][colSoldier] = 0;
            }
 
        }
        return false;
    }
}
 
public class SoldierProblem {
 
    static Scanner in = new Scanner(System.in);
 
    public static void main(String args[]){
        System.out.println("Enter number of soldiers");
        int soldiers = in.nextInt();
 
        Soldier soldier = new Soldier(soldiers);
        soldier.play();
    }
}