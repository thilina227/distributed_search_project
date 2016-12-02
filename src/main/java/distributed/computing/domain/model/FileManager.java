/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributed.computing.domain.model;

import java.util.*;

/**
 *
 * @author yasitham
 */
public class FileManager {
    
    private final static List<String> fileStorage = Collections.synchronizedList(new ArrayList<String>());
    private Iterator<String> itr;
    
    public void addFile(String fileName){
        fileStorage.add(fileName);
    }
    
    public void removeFile(String fileName){
        fileStorage.remove(fileName);
    }
    
    public List<String> searchFile(String keyword){
        List<String> searchResult = new ArrayList<>();
        itr = fileStorage.iterator();
         
        for (String key : keyword.split(" ")){
            while(itr.hasNext()){
                String file = itr.next();
                if(file.toLowerCase().contains(key.toLowerCase())){
                    if(!searchResult.contains(file)){
                        searchResult.add(file);
                    }
                }
            }
        }
        return searchResult;
    }
}
