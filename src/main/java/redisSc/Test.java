package redisSc;


/**
 * Created by liuyang on 2017/4/20.
 */
public class Test {
  /*  public static void main(String[] args) {
    	ServiceB service = new ServiceB();
        for (int i = 0; i < 500; i++) {
            ThreadB threadA = new ThreadB(service);
            threadA.start();
        }
    }*/
	  public static void main(String[] args) {
		  int[] nums = {2, 7, 11, 15};
				int  target = 9;
				int[] results =	twoSum(nums, target);
				for(int i = 0;i<results.length;i++){
					System.out.println(results[i]);
				}
	    }
	  
	  public static int[] twoSum(int[] nums, int target) {
	        int[] results = new int[2] ;
	        int j = 0;
	        int[] aa = {10,10,0,10,10,10,10,1,10,10,-1,2,10,10,10,3};
	        for(int i = 0;i<nums.length;i++){
	           int c = target-nums[i];
	            if(c>=0){
	              if(aa[c]!=10){
	               results[j] = i;
	                  j++;
	           }    
	               
	            }
	          
	        }
	         return results;
	        
	    }
}
