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
public class Driver {
    
    public static void main(String [] args)
    {
        HashMap hashMap=new HashMap();
        
        
        hashMap.put("Kevin",22);
        hashMap.put("Stanley",20);
        hashMap.put("Bum",28);
        hashMap.put("Alex",21);
        
        System.out.println(hashMap.get("Kevin"));
        System.out.println(hashMap.get("Stanley"));
        System.out.println(hashMap.get("Bum"));
        System.out.println(hashMap.get("Alex"));
        
        
    }
    
}
