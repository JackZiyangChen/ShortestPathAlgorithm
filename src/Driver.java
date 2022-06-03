
import java.io.File;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String[][] grid = getGridFromTxtFile("grid.txt");
//        Dijkstra d = new Dijkstra(grid.length);
//        
//        for(int i=0;i<grid.length;i++){
//            for(int j=0;j<grid.length;j++){
//                String c = grid[i][j];
//                if(c.equals("S")){
//                    d.setStart(i, j);
//                }
//                if(c.equals("E")){
//                    d.setEnd(i, j);
//                }
//                if(c.equals("W")){
//                    d.insertWall(i, j);
//                }
//            }
//        }
        

        cmdGame();
//        Dijkstra d = new Dijkstra(6,6);

//        System.out.println(d.distance(0, 0, 1, 0));
//        d.setStart(0, 0);
//        d.setEnd(5,5);
//        d.insertWall(1, 1);
//        d.insertWall(1, 2);
//        d.insertWall(1, 3);
//        d.insertWall(1, 4);
//        d.insertWall(1, 5);
//        d.insertWall(2, 2);
//        d.insertWall(3, 2);
//        d.insertWall(3, 3);
//        d.insertWall(3, 4);
//        d.insertWall(4, 4);
//        
//        d.insertWall(4, 1);
//        d.insertWall(4, 2);
//        d.insertWall(4, 3);
//        d.insertWall(4, 5);

//        
////        d.insertWall(0, 0);
//        
////        d.insertWall(5, 4);
////        d.insertWall(6, 4);
////        d.insertWall(6, 5);
//
////        d.insertWall(1,1);
////        d.insertWall(1,0);
////        d.insertWall(1,2);
////        d.insertWall(1,3);
//        System.out.println(d.run());
//        System.out.println(d.tracePath());
//        d.printGrid();
    }
    
    
    public static void cmdGame(){
        System.out.println("-------------------");
        System.out.println("Welcome to the shortest path demo");
        System.out.print("Would you like to begin? (Y/N) ");
        Scanner sc = new Scanner(System.in);
        String input = "";
        try{
            input = sc.next();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        
        
        if(!input.equalsIgnoreCase("y")){
            System.exit(0);
        }
        
        System.out.println("OK let's begin");
        System.out.println("-------------------------");
        
        int row, col;
        boolean confirmed = false;
        Dijkstra d = new Dijkstra(1);
        
        //setting grid
        while(!confirmed){
            System.out.println();
            System.out.println("grid setting: ");
            System.out.print("Enter number of rows: ");
            try{
                input = sc.next();
                row = Integer.parseInt(input);
                System.out.print("Enter number of columns: ");
                input = sc.next();
                col = Integer.parseInt(input);
                
                d = new Dijkstra(row, col);
                
                
                System.out.println();
                d.printGrid();
                System.out.println();
                System.out.println("Does this look right? (Y/N)");
                input = sc.next();
                confirmed = input.equalsIgnoreCase("y");
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
            
        }
        
        
        //
        confirmed = false;
        while(!confirmed){
            d.resetGame();
            System.out.println();
            System.out.println("-------------------------");
            System.out.println("setting start and end: ");
            System.out.println("\n\n");
            System.out.println("keep in mind of the following rules:");
            System.out.println("1. The coordinate grid used by the computer is different than that of a cartesian grid.\n "
                    + "Whereas the cartesian grid has the origin (0,0) at the bottom left and the Y-axis grows upward, "
                    + "the computer grid has the origin at (0,0) and its Y-axis grows downwards");
            System.out.println("2. I deliberatedly used (row, column) instead of (x,y), since it makes the most sense computationally.\n "
                    + "Please be aware that (row,column) refers to # of rows and columns counting out from origin. It is the opposite of (x,y)"
                    + " so think of (y,x) for (row,column).");
            
            try{
                System.out.println("");
                System.out.print("Enter starting point (row, column): ");
                input = sc.next();
                input = input.replace(" ", "");
                row = Integer.parseInt(input.split(",")[0]); col = Integer.parseInt(input.split(",")[1]);
                d.setStart(row,col);
                
                System.out.print("Enter ending point (row, column): ");
                input = sc.next();
                input = input.replace(" ", "");
                row = Integer.parseInt(input.split(",")[0]); col = Integer.parseInt(input.split(",")[1]);
                d.setEnd(row, col);
                
                
                
                System.out.println();
                d.printGrid();
                System.out.println();
                System.out.println("Does this look right? (Y/N)");
                input = sc.next();
                confirmed = input.equalsIgnoreCase("y");
            }catch(Exception e){
                if(e instanceof ArrayIndexOutOfBoundsException){
                    System.out.println("Please enter a valid coordinate");
                    System.out.println("----------------------");
                }else{
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            
        }
        
        
        
    }
    
    
    public static String[][] getGridFromTxtFile(String name){
        File f = new File(name);
        String read = "";
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                read += s.next();
//                System.out.println(read);
            }
            s.close();
        }catch(Exception e){
            
        }
        
        String[][] out = new String[(int)Math.sqrt(read.length())][(int)Math.sqrt(read.length())];
        
        for(int i=0;i<out.length;i++){
            for(int j=0;j<out.length;j++){
                out[i][j] = "" + read.charAt(i*out.length+j);
            }
        }
        
        return out;
    }
    
}
