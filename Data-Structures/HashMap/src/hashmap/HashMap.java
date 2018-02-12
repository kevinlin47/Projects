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
public class HashMap {
    
    private final int table_size=128;
    
    HashEntry [] table;
    
    HashMap()
    {
        table=new HashEntry[table_size];
        
        for (int i=0;i<table_size;++i)
        {
            table[i]=null;
        }
    }
    
    public int get(String key)
    {
        int hash=Math.abs(key.hashCode())%table_size;
        while(table[hash]!=null && table[hash].getKey()!=key)
        {
            hash=(hash+1)%table_size;           
        }
        
        if (table[hash]==null)
        {
            return -1;
        }
        else
        {
            return table[hash].getValue();
        }
        
    }
    
    public void put(String key, int value)
    {
        HashEntry newEntry=new HashEntry(key,value);
        
        int hash=Math.abs(key.hashCode())%table_size;
        
        while(table[hash]!=null && table[hash].getKey()!=key)
        {
            hash=(hash+1)%table_size;
        }
        
        table[hash]=newEntry;
    }
    
}
