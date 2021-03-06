package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WSY on 4/11/15.
 */
public class TwoSatTest {
    public static void main(String[] args) throws IOException {
        String directory = "/Users/WangSiyuan/Desktop/2D/";
        String filename = directory+"2SatTest10000.txt";
        String line;
        int numOfVariables = 0;
        int numOfClauses = 0;
        int a = 0;
        int b = 0;
        List<Clause<Literal>> clauses = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(filename);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line=bufferedReader.readLine())!=null){
                //System.out.println(line);
                if(line.charAt(0)=='c') System.out.println("Comment:"+line);
                else if(line.charAt(0)=='p'){
                    String[] info = line.split(" ");
                    numOfClauses = Integer.parseInt(info[3]);
                    numOfVariables = Integer.parseInt(info[2]);

                }
                else{
                    String[] info = line.split(" ");
                    for(String s:info){
                        if(a==0) a=Integer.parseInt(s);
                        else if(a!=0 && b==0) b=Integer.parseInt(s);
                        else if(s.equals("0")){
                            Literal<Integer> la = new Literal(Math.abs(a),a>0);
                            Literal<Integer> lb = new Literal(Math.abs(b),b>0);
                            Clause clause = new Clause(la,lb);
                            clauses.add(clause);
                            a = 0;
                            b = 0;

                        }
                    }
                }
            }
            Literal la = new Literal(Math.abs(a),a>0);
            Literal lb = new Literal(Math.abs(b),b>0);
            Clause clause = new Clause(la,lb);
            clauses.add(clause);

        }
        catch (FileNotFoundException ex){
            System.out.println("Unable to open file"+filename);
        }
        catch(IOException ex){
            ex.printStackTrace();
             }

        System.out.println("SAT solver starts!!!");
        long started = System.nanoTime();

        System.out.println(TwoSat.isSatisfiable(clauses));

        long time = System.nanoTime();
        long timeTaken= time - started;
        System.out.println("Time:" + timeTaken/1000000.0 + "ms");





    }
}
