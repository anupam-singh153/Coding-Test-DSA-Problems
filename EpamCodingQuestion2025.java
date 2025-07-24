
/*
🧾 Problem Statement: Longest Sorted Subarray with Parity-Swap Constraint

You are given an array of integers. You are allowed to swap only those pairs of 
elements where one element is even and the other is odd (i.e., they differ in 
parity).

Your task is to:

Perform any number of valid swaps (i.e., swaps between elements of different 
parity) to rearrange the array.

After performing swaps, return the longest possible sorted (non-decreasing)
contiguous subarray that can be formed from the final array.

Print the entire modified array after all allowed swaps.
*/

import java.util.*;
import java.io.*;

public class Main
{
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static void solve(int[] arr, int n){
        
        while(true){
            boolean flag = false;
        
            for(int i = 0; i < n; i += 2){
                
                if(i < n-1 && arr[i] % 2 != arr[i+1] % 2 && arr[i] > arr[i+1] ){
                    swap(arr,i,i+1);
                    flag = true;
                }
            }
            
            
            for(int i = 1; i < n; i += 2){
                
                if(i < n-1 && arr[i] % 2 != arr[i+1] % 2 && arr[i] > arr[i+1] ){
                    swap(arr,i,i+1);
                    flag = true;
                }
            }
            
            if(!flag)
                break;
           
        }
    }
	public static void main(String[] args) throws IOException{
	
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        
        for(int i = 0; i < n; ++i){
            
            arr[i] = sc.nextInt();
        }
        
        solve(arr, n);
        
        for(int x : arr)
            System.out.print(x+" ");
	}
}
