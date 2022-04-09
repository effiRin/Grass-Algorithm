
```java
package com.company;

public class Solution {

    public int findNumbers(int[] nums) {

        int output = 0;

        for (int i = 0; i < nums.length; i++) {
            Double num2 = (double)(nums[i]);     // 대문자 Double
            
            while(true) {
                num2 = num2 / 10;
                if (num2 < 10) break;
            }//while

            String[] splitedNum = num2.toString().split("\\."); // regex 조심!!!(suhong regular expression)

            if(splitedNum[1].length() % 2 == 1){output ++;}     //짝수일 경우

        }// for

        return output;
```


```java
package com.company;

public class Main {

    public static void main(String[] args) {

        int[] nums = {12,345,2,6,7896};

        Solution solution = new Solution();
        System.out.println(solution.findNumbers(nums));
    }
}
```
/* Suhong's comment
 *
 * 몇 자리수 인지 파악
 * '1778' -> 4자리 수
 * (기존 방식) 1.778
 * 1778 -> toString -> length
 * 1778 / 10 -> 177 -> 17 -> 1 -> 0 = 4자리수
 */
