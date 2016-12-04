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
public class Board {
       char board[][];
       int numRows;
       int numCols;
       char player1;
       char player2;
       char token;
       Boolean canPlay;
       
       
       Board() //default constructor
       {
           board=new char [6][7];
            numRows=6;
            numCols=7;
            player1='R';
            player2='W';
            canPlay=true;
            
           
           for (int i=0;i<numRows;++i)
           {
               for (int j=0;j<numCols;++j)
               {
                   board[i][j]=' ';
               }
           }
       }
       
       Board(int row, int col) //creates board of size row x col
       {
           boolean playableBoard=false;
           numRows=row;
           numCols=col;
           player1='R';
           player2='W';
           canPlay=true;
           
           
           while (!playableBoard)
           {
               if (numRows<=3 && numCols<=3 ||numRows>numCols)
               {
                   System.out.println("Not a valid board");
                   System.out.println("Try again? Enter (Y/N) ");
                   Scanner in=new Scanner(System.in);
                   char toContinue=in.next().charAt(0);
                   
                   if (toContinue=='Y')
                   {
                       System.out.println("Enter row size: ");
                       numRows=in.nextInt();
                       System.out.println("Enter coloumn size: ");
                       numCols=in.nextInt();
                   }
                   
                   if (toContinue=='N')
                   {
                       System.exit(0);
                   }
               }
               
               else
               {
                   playableBoard=true;
               }
           }
           
           board=new char[numRows][numCols];
           
           for (int i=0;i<numRows;++i)
           {
               for (int j=0;j<numCols;++j)
               {
                   board[i][j]=' ';
               }
           }
           
       }
       
       public void printBoard()
       {
           for (int i=0;i<numRows;++i)
           {
               for (int j=0;j<numCols;++j)
               {
                   System.out.print(board[i][j]+"|");
               }
               System.out.println();
               
           }
       }
       
       public int getNumRows()
       {
           return numRows;
       }
       
       public int getNumCols()
       {
           return numCols;
       }
       
       public char getPlayerOne()
       {
           return player1;
       }
       
       public char getPlayerTwo()
       {
           return player2;
       }
       
       public void setPlayerOne (char o)
       {
           player1=o;
       }
       
       public void setPlayerTwo (char t)
       {
           player2=t;
       }
       
       public char getToken(int row, int col)
       {
           if (row>numRows || col>numCols)
           {
               return '\0';
           }
           
           else
           {
               return board[row-1][col-1];
           }
       }
       
       public boolean canPlay()
       {
           canPlay=false;
           
           for (int i=0;i<numRows;++i)
           {
               for (int j=0;j<numCols;++j)
               {
                   if (board[i][j]==' ')
                   {
                       canPlay=true;
                       return canPlay;
                   }
               }
           }
           
           return canPlay;
       }
       
       public boolean play(int p, int c)
       {    
           boolean success=false;
           
           if (p==1)
           {
               token=player1;
           }
           
           else if (p==2)
           {
               token=player2;
           }
           
           for (int i=0;i<numRows;++i)
           {    
               int index=i+1;
               if (board[numRows-index][c-1]==' ')
               {
                   board[numRows-index][c-1]=token;
                   success=true;
                   return success;
               }
           }
           
           return success;
           
           
       }
       
       public int isFinished()
       {
           int finished=-1;
           
           for (int i=0;i<numRows;++i) //horizonal check
           {
               for (int j=0;j<numCols-3;++j)
               {
                   if (board[i][j]==player1&&board[i][j+1]==player1&&board[i][j+2]==player1&&board[i][j+3]==player1)
                   {
                       finished=1;
                       return finished;
                   }
                   
                   if(board[i][j]==player2&&board[i][j+1]==player2&&board[i][j+2]==player2&&board[i][j+3]==player2)
                   {
                       finished=2;
                       return finished;
                   }
               }
           }
           
           for (int j=0;j<numCols;++j) //vertical check
           {
               for (int i=0;i<numRows-3;++i)
               {
                   if (board[i][j]==player1&&board[i+1][j]==player1&&board[i+2][j]==player1&&board[i+3][j]==player1)
                   {
                       finished=1;
                       return finished;
                   }
                   
                   if(board[i][j]==player2&&board[i+1][j]==player2&&board[i+2][j]==player2&&board[i+3][j]==player2)
                   {
                       finished=2;
                       return finished;
                   }
               }
           }
           
           for (int i=3;i<numRows;++i) //Ascending diagnol check
           {
               for  (int j=0;j<numCols-3;++j)
               {
                   if (board[i][j]==player1&&board[i-1][j+1]==player1&&board[i-2][j+2]==player1&&board[i-3][j+3]==player1)
                   {
                       finished=1;
                       return finished;
                   }
                   
                   if (board[i][j]==player2&&board[i-1][j+1]==player2&&board[i-2][j+2]==player2&&board[i-3][j+3]==player2)
                   {
                       finished=2;
                       return finished;
                   }
                   
               }
           }
           
           for (int i=3;i<numRows;++i)//Descending diagnol check
           {
               for (int j=3;j<numCols;++j)
               {    
                   if (board[i][j]==player1&&board[i-1][j-1]==player1&&board[i-2][j-2]==player1&&board[i-3][j-3]==player1)
                   {
                       finished=1;
                       return finished;
                   }
                                      
                   if (board[i][j]==player2&&board[i-1][j-1]==player2&&board[i-2][j-2]==player2&&board[i-3][j-3]==player2)
                   {
                       finished=2;
                       return finished;
                   }
               }
           }
           
           for (int i=0;i<numRows;++i) //check for tie
           {
               for (int j=0;j<numCols;++j)
               {
                   if (board[i][j]!=' ')
                   {
                       finished=0;
                   }
                   
                   else
                   {
                       finished=-1;
                       return finished;
                   }
               }
           }
           
           return finished;
                   
            
       }
       
       
}
