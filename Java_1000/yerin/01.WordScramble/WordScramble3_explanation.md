# WordScramble3.md

>  ### 출처 : 남궁성의 코드초보스터디 [Java1000제] - WordScramble3

<br>

## 문제 3.

- 문제2의 예제를 변경해서, 문제를 맞추더라도 프로그램이 종료되지 않고 다음문제를 보여주도록 하세요.
- hint : while문을 중첩해서(2개의 while문) 작성하세요.

```
[실행 결과]

Question :IWVE
Your answer is :ievw
ievw은/는 정답이 아닙니다. 다시 시도해보세요.
Question :IWVE
Your answer is :view
정답입니다.

Question :HOEP
Your answer is :hope
정답입니다.

Question :GNCAEH
Your answer is :change
정답입니다.

Question :HECNAG
Your answer is :q
```

<br>

## 풀이 방향

- while문 중첩 - 바깥 while엔 문제를 주는 게임 로직 반복  
안쪽 while엔 if문 활용해서 1) 게임 종료, 2) 정답 맞췄을 시, 3)틀렸을 시 -> 3가지 경우의 로직 만듦
- 대소문자 상관없이 문자열을 비교하는 equalsIgnoreCase 메소드 이용
반환타입 boolean이므로, true 반환 시 바깥 while문에 이름(Game)을 주어 사용자가 q 또는 Q를 입력할 시, ‘Game’ while문 break;를 통해 프로그램 종료

<br>

## 해설

// 추가...

### 다른 풀이

```java
while(true) {
                     System.out.println("Question :" + question);
                     System.out.print("Your answer is : "); 

                     String input = s.nextLine();

                     if(input.equalsIgnoreCase("q"))
                           System.exit(0);

                     if(input.equalsIgnoreCase(answer)) {
                           System.out.println("정답입니다.");
                           System.out.println();
                           break;
                           
                     } else {
                           System.out.println(input+"은/는 정답이 아닙니다. 다시 시도해보세요.");      
                    
```




### 추가 공부

- 검색해보니 다음 3가지 방법을 이용해서 풀 수 있다.
1. equalsIgnoreCase 이용
2. compareToIgnoreCase 이용 
3. UpperCase 또는 LowerCase이용하기

출처 : [[Java] 대소문자 구분없이 문자열 비교하기](https://hianna.tistory.com/557)

// 나중에 좀더 추가 공부하기