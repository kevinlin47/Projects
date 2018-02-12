/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashmap;

/**
 *
 * @author Kevin Lin
 */
public class HashEntry {
    
    String key;
    int value;
    
    HashEntry(String key,int value)
    {
        this.key=key;
        this.value=value;
    }
    
    public String getKey()
    {
        return this.key;
    }
    
    public int getValue()
    {
        return this.value;
    }
}
