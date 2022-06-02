package DataStructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jackchen
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;


public class PriorityQueue<E> implements Queue<E>
{
    
    private E[] data;
    private int size;
    
    
    public PriorityQueue(int capacity){
        data = (E[])new Comparable[capacity];
        size = 0;
    }
    

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return binaryLocate((E)o, 1, size);
    }
    
    private boolean binaryLocate(E e, int start, int end){
//        System.out.println(size);
//        System.out.println("start "+start+", end: "+end);
        int mid = (end+start)/2;
        if(start>end){
            return false;
        }
        else if(data[mid].equals(e)){
            return true; 
        }else if( ((Comparable)e).compareTo(data[mid]) > 0 ){
            return binaryLocate(e, mid+1, end);
        }else{
            return binaryLocate(e, start, mid-1);
        }
    }


    @Override
    public Object[] toArray() {
        return (Object[])data;
    }


    @Override
    public boolean add(E e) {
        if(size+1==data.length)
        {
            return false;
        }
        
        
        int opening = ++size;
        
        for(;opening>1 && ((Comparable)e).compareTo(data[opening/2])<0;opening/=2){
            data[opening] = data[opening/2];
        }
        data[opening] = e;
        return true;
    }
    
    @Override
    public E peek(){
        if(isEmpty()) return null;
        
        return data[1];
    }




    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        
        E out = data[1];
        
        
        //percolate down
        data[1] = data[size--];
        
        percolateDown(1);

        
        return out;
        
    }
    
    private void percolateDown(int index){
        if(index*2<=size){
            int left = index * 2, right = index * 2+1;
            E temp = data[index];
            if(((Comparable)data[right]).compareTo(data[left])<0){
                data[index] = data[right];
                data[right] = temp;
            }else{
                data[index] = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
            percolateDown(right);
        }
             
    }
    
    //NOTE: the following methods are irrelevant to the problem and are therefore not implemented

    @Override
    public E element() {
        return peek();
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    
    
}
