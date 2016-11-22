package project.backend.pkg;

/*
 * Class to list all the grouped sprinklers
 */


import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author shilpita_roy
 */
public class Group {
    ArrayList<Sprinkler> sprinklerList;

    public Group() {
        this.sprinklerList = new ArrayList<Sprinkler>();
    }
    
    public void setSprinklerList(String filename){
        FileInputStream fstream = null;
        BufferedReader br		= null;
        String strLine;
        String delimiter = ",";
        try {
                        fstream  = new FileInputStream(filename.toLowerCase());
                        br 		 = new BufferedReader(new InputStreamReader(fstream));
                        //Read File Line By Line
                        while ((strLine = br.readLine()) != null)   {
                                        String[] tokens = strLine.split(delimiter);
                                        this.sprinklerList.add(new Sprinkler(tokens[0]
                                                                            ,tokens[1]
                                                                            ,Boolean.parseBoolean(tokens[2])
                                                                            ,Boolean.parseBoolean(tokens[3])
                                                                            ,tokens[4]));
                        }
                } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                } catch (IOException e) {
                                        e.printStackTrace();
                }finally{
                        //Close the input stream
                        if (br != null )
                                        try {
                                                        br.close();
                                        } catch (IOException e) {
                                                        e.printStackTrace();
                                        }
                        if(fstream != null)
                                        try {
                                                        fstream.close();
                                        } catch (IOException e) {
                                                        e.printStackTrace();
                                        }
                }
    }
    
    
    public ArrayList<Sprinkler> getSprinklerList() {
        return sprinklerList;
    }   

    @Override
    public String toString() {
        return "Group{" + "sprinklerList=" + sprinklerList + '}';
    }
    
    
    
}

