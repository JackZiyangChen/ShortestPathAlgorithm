
import java.awt.Color;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ImgPathFinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageGrid grid = new ImageGrid("maze3.jpg");
        Dijkstra d = new Dijkstra(grid.getHeight(),grid.getWidth());
        
        //starting and ending
//        d.setStart(71,55);//maze2
//        d.setEnd(624,936);//maze2
        
        d.setStart(24,345);//maze3
        d.setEnd(590,240);//maze3

//        d.setStart(1239, 1299);
//        d.setEnd(400, 1299);
        
        //adding walls
        ArrayList<int[]> walls = grid.getBlackSpots();
        for(int[] w : walls){
            d.insertWall(w[0], w[1]);
        }
        System.out.println(walls.size());
        System.out.println(d.run());
        grid.paintPixels(false, d.getRawPath(), Color.decode("#ff2444"), 3);
        grid.exportNewImage();
        
    }
    
}
