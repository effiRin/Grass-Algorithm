# 알고리즘 스터디 (LeetCode -> Explore -> Array101)

### 2022.04.03

## [Find numbers wit even number of digits](https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/)



### 문제 설명:
int의 배열 nums를 인수로 받아, 배열 안에 자릿수가 짝수인 수가 몇개 있는지를 반환하는 메소드 findNumbers(int[] nums)를 구현해야한다.
자릿수가 짝수인 수란 2자리 수 , 4자리 수 등 숫자를 썼을 때 짝수개의 숫자로 표현되는 숫자를 의미한다.

```bash
Input: nums = [12,345,2,6,7896]
Output: 2
// 12, 7896은 각각 2자리수, 4자리수로 짝수개의 자리수이다.
```

```bash
Input: nums = [555,901,482,1771]
Output: 1 
// 1771만 4자리수로 짝수개의 자리수를 가진다.
```

### 문제 풀이:
input으로 주어진 배열의 각 숫자가 짝수 자리수인지 홀수 자리 수인지 구분해야한다.
10진법을 따르는 숫자들은 10으로 나누면 자리수가 하나씩 줄어든다.

> 1771 / 10 = 177.1 (수학)

하지만 Java의 int와 int의 연산에서 발생한 소수점 이하의 수들은 버려지게 되므로 아래와 같이 될 것이다.

> Java에서의 int의 나눗셈  
> 1771 / 10 = 177 (1)  
> 177 / 10 = 17 (2)  
> 17 / 10 = 1 (3)  
> 1 / 10 = 0 (4)  

4자리 수의 경우 10으로 4번 나누면 0이 나오는 것을 알 수 있다.
따라서, 
1. while 루프를 통해 몇번 나누어야 0이 되는지를 확인한다. -> n번 나누어서 0이 되었다.
2. n이 짝수라면 짝수 자리수, 아니라면 홀수 자리수인 숫자라고 판단할 수 있다.

```java
class Solution {
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            // 짝수개의 자리수인 숫자가 등장할 때마다 반환할 값을 0부터 1씩 증가시킨다.
            if (isEvenDigits(num)) result++;
        }
        return result;
    }

    // 짝수개의 자리수라면 true를 반환
    private boolean isEvenDigits(int num) {
        int numOfDivision = 0;
        // 나눗셈 결과가 0이 될때까지 나누기 반복
        while (num != 0) {
            num = num / 10;
            numOfDivision++;
        }
        return numOfDivision % 2 == 0;
    }
}
```

## [Merge sorted array](https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3253/)

### 문제 설명:

각각 정렬되어 있는 두개의 int형 배열을 하나의 정렬된 하나의 배열로 합쳐야한다.
두개의 배열 중 첫번 째 배열은 두 배열이 갖고 있는 원소 수의 합만큼의 크기를 가지기 때문에 첫번째 배열 안에 두번째 배열을 합쳐 넣는 형태로 구현해야한다.

### 문제 풀이:

이 문제는 크기가 작은 수부터 정렬하려고 하면 1번 배열이 기존에 갖고 있던 원소가 덮어씌워지는 일이 발생할 수 있다. 반면에 1번 배열의 뒷부분은 2번 배열의 크기만큼 비워져있기 때문에 뒤에서부터 큰수를 채워 넣으면 덮어씌워지는 일 없이 정렬을 완료할 수 있다.

먼저 1번, 2번 배열의 마지막 원소(가장 큰 원소)의 위치를 가리키는 index를 준비한다.(아래 코드의 pointer1,2)

그리고 1번 배열의 맨 뒤에서부터 차례대로 내용물을 채워 넣는데 1,2의 포인터가 가리키는 원소중 더 큰 것을 집어 넣으면 된다.


```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 포인터 초기화
        int pointer1 = m - 1;
        int pointer2 = n - 1;

        // 맨 뒤에서부터 큰 숫자 순으로 집어 넣는다.
        for (int i = nums1.length - 1; i >= 0; i--) {
            // 1번 배열의 내용물 전부 다 정렬했을때
            if (pointer1 < 0) {
                nums1[i] = nums2[pointer2--];
                continue;
            }
            // 2번 배열의 내용물 전부 다 정렬했을때
            if (pointer2 < 0) {
                nums1[i] = nums1[pointer1--];
                continue;
            }
            // 1,2번 배열 모두 정렬할 원소가 남아있을때
            // 더 큰 원소를 넣는다.
            if(nums2[pointer2] < nums1[pointer1]) {
                nums1[i] = nums1[pointer1--];
                continue;
            }
            
            nums1[i] = nums2[pointer2--];
        }
    }
}
```