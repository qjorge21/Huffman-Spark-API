/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author Michael
 */
public interface PrioridadPorMinimoCola <E extends Comparable<E>> {
    
  public boolean isEmpty(); // cola vacia ? 
 
  public void insert(E element); // inserto elemento a la cola
  
  public E minimum(); // devuelve el elemento con frecuencia mínima sin quitarlo de las lista. 
  
  public E extractMin(); // extraer elemento mínimo. 
    
}
