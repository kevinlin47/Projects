/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfourgame;
import java.util.Scanner;

/**
 *
 * @author Kevin Lin
 */
public class ConnectFourGame {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        
        boolean inProgress=true;
        Scanner in=new Scanner(System.in);
        char boardChoice;
        int turn=1;
        
        System.out.println("Would you like to use the default Board size or a custome Board size?");
        System.out.print("Enter D for default C for custom:  ");
        boardChoice=in.next().charAt(0);
        
        if (boardChoice=='D')
        {   
            System.out.println("Player 1 = R and Player 2 = W");
            Board gameBoard=new Board();
            while(inProgress)
            {       
              
              int player;
              
              if (turn%2!=0)
              {
                  player=1;
              }
              
              else
              {
                  player=2;
              }
              
              int coloumnChoice;
              gameBoard.printBoard();
              System.out.println("Player "+player);
              System.out.print("Enter coloumn to place token: ");
              coloumnChoice=in.nextInt();
              while(!gameBoard.play(player,coloumnChoice))
              {
                  System.out.print("Invalid placement try again: ");
                  int c=in.nextInt();
                  gameBoard.play(player,c);
              }
              ++turn;
              
              if (gameBoard.isFinished()!=-1)
              {
                  inProgress=false;
              }
              
            }
            
            if(gameBoard.isFinished()==1)
            {   
                gameBoard.printBoard();
                System.out.println("Player 1 wins!");
                
            }
            
            else if(gameBoard.isFinished()==2)
            {   
                gameBoard.printBoard();
                System.out.println("Player 2 wins!");
                
            }
            
            else if (gameBoard.isFinished()==0)
            {   
                gameBoard.printBoard();
                System.out.println("Game is a tie");
                
            }
            
            System.exit(0);
        }
            
        else if (boardChoice=='C')
        {   
            int rowSize;
            int colSize;
                
            System.out.println("Enter row size: ");
            rowSize=in.nextInt();
            System.out.println("Enter coloumn size: ");
            colSize=in.nextInt();
                
            Board gameBoard=new Board(rowSize,colSize);
            System.out.println("Player 1 = R and Player 2 = W");
            
            while(inProgress)
            {       
              
              int player;
              
              if (turn%2!=0)
              {
                  player=1;
              }
              
              else
              {
                  player=2;
              }
              
              int coloumnChoice;
              gameBoard.printBoard();
              System.out.println("Player "+player);
              System.out.print("Enter coloumn to place token: ");
              coloumnChoice=in.nextInt();
              while(!gameBoard.play(player,coloumnChoice))
              {
                  System.out.print("Invalid placement try again: ");
                  int c=in.nextInt();
                  gameBoard.play(player,c);
              }
              ++turn;
              
              if (gameBoard.isFinished()!=-1)
              {
                  inProgress=false;
              }
              
            }
            
            if(gameBoard.isFinished()==1)
            {   
                gameBoard.printBoard();
                System.out.println("Player 1 wins!");
                
            }
            
            else if(gameBoard.isFinished()==2)
            {   
                gameBoard.printBoard();
                System.out.println("Player 2 wins!");
                
            }
            
            else if (gameBoard.isFinished()==0)
            {   
                gameBoard.printBoard();
                System.out.println("Game is a tie");
                
            }
            
            System.exit(0);
                
        }
        
        else if (boardChoice!='C' || boardChoice !='D')
        {
            System.out.println("Did not enter a valid choice session terminated");
            System.exit(0);
        }
        
        
        

        
        
    }
    
}
