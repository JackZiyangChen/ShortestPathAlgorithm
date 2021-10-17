
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
        String[][] grid = getGridFromTxtFile("grid.txt");
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
        
        Dijkstra d = new Dijkstra(2,3);

//        System.out.println(d.distance(0, 0, 1, 0));
        d.setStart(0, 0);
        d.setEnd(1, 2);
//        d.insertWall(1, 1);
//        d.insertWall(2, 1);
//        d.insertWall(2, 2);
//        d.insertWall(3, 2);
//        d.insertWall(3, 3);
//        d.insertWall(3, 4);
//        d.insertWall(4, 4);
//        d.insertWall(5, 4);
//        d.insertWall(6, 4);
//        d.insertWall(6, 5);

//        d.insertWall(1,1);
//        d.insertWall(1,0);
//        d.insertWall(1,2);
//        d.insertWall(1,3);
        System.out.println(d.run());
        System.out.println(d.tracePath());
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
