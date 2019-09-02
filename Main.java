import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] budgets = {120, 110, 140, 150};
		int M = 485;	//	return 127
		
//		int[] budgets = {10,20,30,40};
//		int M = 90;	//	return 30
		
		System.out.println(new Solution().solution(budgets, M));
	}
}
class Solution {
    public int solution(int[] budgets, int M) {
        int answer = 0;
        Arrays.sort(budgets);
        
        int n = budgets.length;
        long sum = 0;
        int left = 0, right = M;
        int premid = 0, mid = 0;
        for (int i = 0; i < budgets.length; i++)
        	sum+=budgets[i];
        
        if (sum <= M)
        	return budgets[n-1];
        else {
            while(true) {
            	sum = 0;
            	mid = (left + right) / 2;
            	
            	if (premid == mid)
            		break;
            	for (int i = 0; i < n; i++) {
            		if (mid <= budgets[i]) {
            			sum += mid * (n-i);
            			break;
            		} else 
            			sum += budgets[i];
            	}
            	
            	if (sum <= M)
            		left = mid;
            	else
            		right = mid;
            	premid = mid;
            }
            answer = mid;
        }
        return answer;
    }
}

class Solution_failed {
	int[] budget;
    public int solution(int[] budgets, int M) {
        Arrays.sort(budgets);
        this.budget = budgets;
        int n = budgets.length;
        long totalBudget = 0;
        int target = M/n;
        int idx = binsearch(0, n - 1, target);
        
        int result = budgets[idx];
        if (idx == n - 1)
        	return result;
        for (int i = 0; i <= idx; i++) totalBudget += budget[i];
        int remains = n - (idx + 1);
        for (int i = budget[idx] + 1; i < budget[n-1]; i++) {
        	
        	if (totalBudget + remains*i > M) // i만큼 지원 가능불가시
        		break;
        	
        	result++;
        	if (i == budget[idx+1]) {
        		totalBudget += budget[idx];
        		idx++;
        		remains--;
        	}
        }
        
        return result;
    }
    private int binsearch(int left, int right, int target) {
    	if (left + 1 == right) return left;
    	int mid = (left + right) / 2;
    	if (budget[mid] == target)
    		return mid;
    	if (target < budget[mid]) {
    		return binsearch(left, mid, target);
    	} else {
    		return binsearch(mid, right, target);
    	}
    		
    }
}