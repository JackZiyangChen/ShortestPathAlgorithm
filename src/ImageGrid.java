
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ImageGrid {
    
    private BufferedImage img;
    private int width, height;
    private int[][] pixelGrid;
    private BufferedImage imgNew;
    private String name;
    private String format;

    public ImageGrid(String fileName) {
        try {
            File f = new File(fileName);
            name = fileName;
            img = ImageIO.read(f);
            height = img.getHeight();
            width = img.getWidth();
            pixelGrid = new int[height][width];
//            System.out.println(pixelGrid.length);
//            System.out.println(height);
//            System.out.println(pixelGrid[0].length);
//            System.out.println(width);
//            System.out.println(img.getRGB(647,1151));
            generatePixelGrid();
            imgNew = copyImage(img);
//            imgNew = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void generatePixelGrid(){
        for(int i=0;i<pixelGrid.length;i++){
            for(int j=0;j<pixelGrid[i].length;j++){
                pixelGrid[i][j] = img.getRGB(j, i);
            }
        }
    }
    
    
    public ArrayList<int[]> getBlackSpots(){
        ArrayList<int[]> out = new ArrayList<int[]>();
        for(int i=0;i<pixelGrid.length;i++){
            for(int j=0;j<pixelGrid[0].length;j++){
                if(pixelGrid[i][j]<=Color.decode("#323330").getRGB()){
                    out.add(new int[]{i,j});
                }
            }
        }
        return out;
    }
    
    public void paintPixels(boolean original, ArrayList<int[]> pixels, Color color, int thickness){
        for(int[]p:pixels){
            paintPixel(original,p[1],p[0],color, thickness);
        }
        System.out.println("finished");
    }
    
    public void paintPixel(boolean original, int width, int height, Color color, int thickness){
        BufferedImage targetImage = original?img:imgNew;
        for(int i=width-thickness;i<=width+thickness;i++){
            for(int j=height-thickness;j<=height+thickness;j++){
                if(i<width && i>=0 && j<height && j>=0)
                    targetImage.setRGB(i, j, color.getRGB());
            }
        }
    }
    
    public void exportNewImage(){
        File f = new File("new " + name);
        try{
            ImageIO.write(imgNew, name.substring(name.indexOf(".")+1), f);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static BufferedImage copyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
}
