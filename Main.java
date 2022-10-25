import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    /*
		The number of the example is 6, but you will get any number from 1 to 9, if the Hash starts with a letter, please choose the first number, for example:

		HASH -> Selected Number 👎
		c20ad4d76fe97759aa27a0c99bff6710 -> 2
		ce9e8dc8a961356d7624f1f463edafb5 -> 9
		01a6ed5cb97b464996f7cd2c491f7431 -> 1

		This number will be mencion in the next two challenges as “S”. The challenges can be resolve in javascript, typescript, java or python programming language.
		In some cases you can get a hash with letters only, in this cases you can use this output as input of the hash algorithm (reapply).
	*/
    /* Gabriela León en https://www.md5hashgenerator.com
        8c197c235039fb32a1cdc0b7d8dab600 -> 8
     */

    public static final Integer S = 8;

    public static void main(String[] args){
        Integer[] code1 = new Integer[]{80, 8, 5, 4, 7, 2, 9, 9, 29, 1}; 
       System.out.println(invertAndRemove(Arrays.asList(code1)));
        Integer[] code2 = new Integer[]{6, -5, 0, 5, 8, 9, 10};
        //System.out.println(squaresAndSorted(Arrays.asList(code2)));
        Integer[] code3 = new Integer[]{1, 5, 1, 1, 1, 10, 15, 20, 100};
        //Integer[] code3 = new Integer[]{1, 1, 1, 1, 1};
        //System.out.println(minimunCantCreate2(Arrays.asList(code3)));
    }
    /*
		Code challenge one

		Having a list of n numbers with digits in range [0, S], where n <= 100, switch all list positions in O(n) time.
		If the input number contains a digit greater or equal than S, you will delete the digit from the number, for example with S=6, 61 becomes 1, and 6 will be deleted from the array. The result should be printed in console/terminal. Please, don’t use built-in sort of your language.

		Examples with S=6:
		> [1, 2, 3, 4, 5, 6] -> [5, 4, 3, 2, 1]
		> [10, 20, 30, 40] -> [40, 30, 20, 10]
		> [6] -> []
		> [66] -> []
		> [65] -> [5]
		> [6, 2, 1] -> [1 , 2]
		> [60, 6, 5, 4, 3, 2, 7, 7, 29, 1] -> [1, 2, 2, 3, 4, 5, 0]
	*/
    public static List<Integer> invertAndRemove(List<Integer> arr1){
        Integer insize = arr1.size();
        List<Integer> inverted = new ArrayList<Integer>(Collections.nCopies(insize,0));
        String RegexBase = "["+S.toString()+"-9]";
        for(int i = 0; i < insize; i++){
            Integer number = arr1.get(i);
            //Comparación
            String FixNum = number.toString().replaceAll(RegexBase, "");
            //Reorganización
            if(FixNum.isEmpty() || FixNum.isBlank()){
				inverted.remove(insize-i-1);
			}else{
				Integer StrNum = Integer.parseInt(FixNum);
				inverted.set(insize-i-1, StrNum);
			}
            
        }
       // System.out.println(Arrays.asList(inverted));
        return inverted;
    }

    /*
		Code challenge two

		Write a function that takes in a non-empty array of integers sorted in ascending order and returns a new array of the same length with the squares of the original integers also sorted in ascending order. If the output number is out of the range [0, SS] (for S=6 the range will be [0, 66]), you will delete it of the output array. Please, don’t use built-in sort of your language.

		Examples with S=6:
		> {"array": [1, 2, 3, 5, 6, 8, 9]} -> [1, 4, 9, 25, 36, 64]
		> {"array": [-2, -1]} -> [1, 4]
		> {"array": [-6, -5, 0, 5, 6]} -> [0, 25, 25, 36, 36]
		> {"array": [-10, 10]} -> []
	*/

    public static List<Integer> squaresAndSorted(List<Integer> arr2){
        //Cuadrado
        List<Integer> sqsorted = new ArrayList<Integer>();
        String sMaxValue = S.toString() + S.toString();
		Integer maxValue = Integer.parseInt(sMaxValue);
        arr2.forEach(number -> {
			Integer sqrNum = (int) Math.pow(number, 2);
			if(sqrNum < maxValue){
				sqsorted.add(sqrNum);
			}
		});
        //System.out.println(Arrays.asList(sqsorted));
        //Organización
        Integer insize2 = sqsorted.size();
        int index = -1;
		for(int i = 0; i<insize2; i++){
			index = i;
			for(int j = i; j<insize2-1; j++){
				if(sqsorted.get(j) < sqsorted.get(index)){
					index = j;
				}
			}
			int temporaryNum = sqsorted.get(i);
			sqsorted.set(i, sqsorted.get(index));
			sqsorted.set(index, temporaryNum);
		}

        return sqsorted;
    }
    
    /*
		Code challenge three (cambiarlo para que no sea googleable)

		Given an array of positive integers representing the values of coins in your possession, write a function that returns the minimum amount of change (the minimum sum of money) that you CANNOT give change. The given coins can have any positive integer value and aren't necessarily unique (i.e., you can have multiple coins of the same value). You can use built-in sort of your language.

		Hint 1
		One approach to solve this problem is to attempt to create every single amount of change, starting at 1 and going up until you eventually can’t create an amount. While this approach works, there is a better one.
		Hint 2
		Start by sorting the input array. Since you’re trying to find the minimum amount of change that you can’t create, it makes sense to consider the smallest coins first.
		Hint 3
		To understand the trick to this problem, consider the following example: coins = [1, 2, 4]. With this set of coins, we can create 1, 2, 3, 4, 5, 6, 7 cents worth of change. Now, if we were to add a coin of value 9 to this set, we would not be able to create 8 cents. However, if we were to add a coin of value 7, we would be able to create 8 cents, and we would also be able to create all values of change from 1 to 15. Why is this the case?

		Examples:
		> {"coins": [5, 7, 1, 1, 2, 3, 22]} -> 20
		> {"coins": [1, 1, 1, 1, 1]} -> 6
		> {"coins": [1, 5, 1, 1, 1, 10, 15, 20, 100]} -> 55
	*/

    public static Integer minimunCantCreate(List<Integer> arr3){
		List<Integer> mincoins = arr3;
		Integer intSize = mincoins.size();
		Collections.sort(mincoins);
		Integer minimun = 1;
		if(mincoins.get(0) > 1){
			return minimun;
		}
		Map<Integer,Integer> allSums = new HashMap<Integer, Integer>();
        for(int i=0; i<intSize; i++){
            Integer sumtotal = mincoins.get(i);
            Integer sumone = 0;
            for(int j=i; j<intSize; j++){
                if (i != j) {
                    sumone = mincoins.get(i) + mincoins.get(j);
                    sumtotal = sumtotal + mincoins.get(j);
                    allSums.put(sumone, sumone);
                    allSums.put(sumtotal, sumtotal);

                }    
            } 
        }
		
		System.out.println(allSums);
		List<Integer> allSumsList = new ArrayList<Integer>(allSums.values());
		Boolean condition = true;
		while(condition){
			minimun++;
			if(!(allSumsList.contains(minimun) || mincoins.contains(minimun))){
				condition = false;
			}
			
		}
		
		return minimun;
	}
		//INTENTO 2 CODIGO 3
		public static Integer minimunCantCreate2(List<Integer> arr3){
			List<Integer> mincoins = arr3;
			Integer intSize = mincoins.size();
			Collections.sort(mincoins);
			//System.out.println(mincoins);
			Integer minimun = 1;
			if(mincoins.get(0) > 1){
				return minimun;
			} 
			List<Integer>  primenums = Arrays.asList(1, 2, 3, 5, 7);
			Map<Integer,Integer> allSums = new HashMap<Integer, Integer>();
			Map<Integer,Integer> allPrimes = new HashMap<Integer, Integer>();
			Integer sumtotal2 = 0;
			for(int i=0; i<intSize; i++){
				Integer summay = mincoins.get(i);
				for(int j=1; j<intSize; j++){
					Integer sumprimes = 0;
					if (mincoins.get(i)<6 && mincoins.get(j)<6){
						sumprimes = mincoins.get(i) + mincoins.get(j);
						summay = summay + mincoins.get(j);
						if (summay<6) {
							allPrimes.put(summay, summay);	
						}
						if (sumprimes<6) {
							allPrimes.put(sumprimes, sumprimes);	
						}							
					}	   
				} 
				sumtotal2 = sumtotal2 + mincoins.get(i);
				allSums.put(sumtotal2, sumtotal2);
			}
			List<Integer> allSumsList = new ArrayList<Integer>(allSums.values());
			Collections.sort(allSumsList);
			Integer Sumsize = allSumsList.size();
			System.out.println(allSumsList);
			Integer minim = 0;
			//System.out.println(minim);
			if (Prime(mincoins.get(intSize)) || mincoins.get(intSize) == 1) {
				minim = allSumsList.get(Sumsize) + 1;
			} else {
				minim = allSumsList.get(Sumsize-1) + 1;
			}
			//System.out.println(allPrimes);
			//System.out.println(allSums);
			//List<Integer> allSumsList = new ArrayList<Integer>(allSums.values());
			for(int i=0; i<primenums.size(); i++){
				minimun = primenums.get(i);
				if(!(allSumsList.contains(minimun) || mincoins.contains(minimun))){
					break;
				}
			}
			if (minimun == 7) {
				minimun = minim;
			}
			return minimun;

	}

	public static boolean Prime(int num) {
		// El 0, 1 y 4 no son primos
		if (num == 0 || num == 1 || num == 4) {
			return false;
		}
		for (int x = 2; x < num / 2; x++) {
		  // Si es divisible por cualquiera de estos números, no
		  // es primo
		if (num % x == 0)
			return false;
		}
		// Si no se pudo dividir por ninguno de los de arriba, sí es primo
		return true;
	}
}
