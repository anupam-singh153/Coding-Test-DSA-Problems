
/*
🧾 Problem Statement: Rotate the String by k the position 

You are given a string. You have to create a new String 
which is a combination of firstly (k to n-1) and secondly 
(0 - k).

return a newString as a result;
*/

import java.util.*;
import java.io.*;

public class Main
{
    private static void solve(String s, int k){
        String firstPart = s.substring(0,k);
        String secondPart = s.substring(k);
        
        System.out.println(secondPart + firstPart);
    }
	public static void main(String[] args) throws IOException{
	
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        
        solve(s, k);
	}
}
