
import DataStructures.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Dijkstra {

    /**
     * @param args the command line arguments
     */
    static class Point implements Comparable{
        private int x;
        private int y;
        private double shortestPath;
        private Point via;
        private boolean visited;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
            this.shortestPath = 99999;
            this.via = null;
            this.visited = false;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
        
        

        public double getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(double shortestPath) {
            this.shortestPath = shortestPath;
        }

        public Point getVia() {
            return via;
        }

        public void setVia(Point via) {
            this.via = via;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
        
        @Override
        public int compareTo(Object t) {
            if(shortestPath < ((Point)t).getShortestPath()){
                return -1;
            }else if(shortestPath > ((Point)t).getShortestPath()){
                return 1;
            }else{
                return 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Point && ((Point)o).getX() == x && ((Point)o).getY() == y;
        }

        @Override
        public String toString() {
            return "(" +  x + ", " + y + ')';
        }
        
        
        
    }
    
    private Point[][] grid;
    private Point start;
    private Point end;
    
    public Dijkstra(int gridSize){
        grid = new Point[gridSize][gridSize];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                grid[i][j] = new Point(i,j);
            }
        }
    }
    
    public Dijkstra(int xSize, int ySize){
        grid = new Point[xSize][ySize];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                grid[i][j] = new Point(i,j);
            }
        }
//        System.out.println("{"+grid.length+", "+grid[0].length+"}");
    }

    public void setStart(int x, int y) {
        this.start = grid[x][y];
        start.setShortestPath(0);
        start.setVia(start);
    }

    public void setEnd(int x, int y) {
        this.end = grid[x][y];
    }
    
    public void insertWall(int x, int y){
        grid[x][y] = null;
    }
    
    private ArrayList<Point> getVisitableNeighbors(int r, int c){
        ArrayList<Point> out = new ArrayList<Point>();
        for(int i = r-1; i<=r+1; i++){
            for(int j=c-1; j<=c+1; j++){
                if(!(i==r && j==c) && isValidLocation(i,j)){
                    if(grid[i][j] != null && !grid[i][j].isVisited()){
                        out.add(grid[i][j]);
                    }
                }
            }
        }
        return out;
    }
    
    private boolean isValidLocation(int r, int c){
        return r<grid.length && c<grid[0].length && r>=0 && c>=0;
    }
    
    public double run(){
        PriorityQueue<Point> list = new PriorityQueue<Point>(9999);
        list.add(start);
        while(!list.isEmpty()){
//            System.out.println("curr: " + curr);
//            System.out.println("list pre-processing: " + list);
            Point curr = list.poll();
            
            curr.setVisited(true);
            ArrayList<Point> neighbors = getVisitableNeighbors(curr.getX(),curr.getY());
            
            for(int i=0;i<neighbors.size();i++){
                Point p = neighbors.get(i);
                double attemptedPath = curr.getShortestPath() + distance(p.getX(),p.getY(),curr.getX(),curr.getY());
//                System.out.println(attemptedPath);
                if(attemptedPath<p.getShortestPath()){
                    p.setShortestPath(attemptedPath);
                    p.setVia(curr);
                }
                if(!list.contains(p)){
                    list.add(p);
                }
            }
//            Collections.sort(list); //NOTE: the original design was to use a priority queue, but the built-in priority queue is not quite working for our purposes
//            System.out.println("list post-processing: " + list);
//            System.out.println("list: " + list);
//            printGrid();
//            if(list.isEmpty())
//                return -1;
        }
        return end.getShortestPath();
    }
    
    private ArrayList<Point> pointPathway(){
        ArrayList<Point> out = new ArrayList<Point>();
        Point p = end;
        if(end.getVia() == null){
            out.add(new Point(-1,-1));
            return out;
        }
        while(!p.equals(start)){
            out.add(0, p);
            p = p.getVia();
        }
        out.add(0, start);
        return out;
    }
    
    public String tracePath(){
        if(pointPathway().contains(new Point(-1,-1)))
            return "No eligible path found";
        String out = "{";
        for(Point p:pointPathway()){
            out += p + "->";
        }
        return out.substring(0, out.length()-2)+"}";
    }
    
    public ArrayList<int[]> getRawPath(){
        if(pointPathway().contains(new Point(-1,-1)))
            return new ArrayList<int[]>();
        ArrayList<int[]> out = new ArrayList<int[]>();
        ArrayList<Point> coordinates = pointPathway();
        for(int i=0;i<coordinates.size();i++){
            out.add(convertCoordinate(coordinates.get(i)));
        }
        return out;
    }
    
    private int[] convertCoordinate(Point P){
        return new int[]{P.getX(),P.getY()};
    }
    
    public double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }
    
    private void debugPrintGrid(){
        System.out.print("{");
        for(Point[] pl:grid){
            for(Point P:pl){
                System.out.print(P.getShortestPath() + ", ");
            }
            System.out.println();
        }
        System.out.print("}");
    }
    
    public void printGrid(){
        System.out.println("{");
        for(Point[] pl:grid){
            for(Point P:pl){
                if(P==null){ 
                    System.out.print("W ");
                }
                else{
                    if(P.equals(start)) System.out.print("S ");
                    else if(P.equals(end)) System.out.print("E ");
                    else if(pointPathway().contains(P)) System.out.print("✓ ");
                    else System.out.print(". ");
                }
                //System.out.print(P.getShortestPath() + ", ");
            }
            System.out.println();
        }
        System.out.print("}");
    }
    
}
