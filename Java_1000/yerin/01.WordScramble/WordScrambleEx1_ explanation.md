
> ## 출처 : 남궁성의 코드 초보스터디 [Java1000제] - Word Scramble 1 
<br>

### 문제 1. 다음의 예제를 완성하시오.
getAnswer(String[] strArr)는 배열strArr의 요소중의 하나를 임의로 골라서 반환한다.(Math.random()사용)  
getScrambledWord(String str)는 주어진 문자열 str의 각 문자의 순서를 뒤섞은 다음, 새로운 문자열로 반환한다.
<br>

### 풀이 방향
Math.random()으로 배열 strArr의 랜덤한 인덱스 번호를 구하여 getAnswer 메소드를 만들고,  
무작위로 섞는 Collection의 shuffle 메소드를 이용하여 getScrambledWord 메소드를 만들기
<br>

### 해설

#### 1. Math.random

```
 int idx = (int)((Math.random()*strArr.length));

```
- Math.random은 0~1.0 사이의 랜덤한 숫자(double형)를 주는 함수다. 만약 특정한 범위 사이의 랜덤한 값을 원한다면, 뒤에 원하는 범위의 값을 곱하면 된다.  
- 예를 들어, 0~20 사이의 랜덤한 숫자를 원한다면 `Math.random()*20`을 주면 된다.  
<br>

- 위 문제의 경우엔, strArr 배열 중 무작위로 한 단어가 뽑혀야 한다. 따라서 단어가 들어있는 strArr의 배열 크기만큼 랜덤한 숫자를 줘야한다.  
따라서 `Math.random()*strArr.length`가 된다.  
<br>

- 그러나 Math.random은 double 값을 출력한다. 배열의 인덱스 번호는 int이므로, int로 casting을 해줘야 한다. 따라서 `(int)`를 앞에 붙여준다.  
<br>
<br>


```
List<Character> splitedWords = new ArrayList<Character>();

   	 for(char c : str.toCharArray()){
   		 splitedWords.add(c);
   		 }

```

#### 2. toCharArray 메소드
- getAnswer() 메소드를 통해서 strArr 배열에서 한 단어를 랜덤하게 뽑았다면, 그 단어를 한 글자씩 쪼개줘야 한다. 이를 위해서 toCharArray() 메소드를 쓰고자 했다.
- toCharArray는 문자열을 한 글자씩 나눠서 char형 배열로 담아 준다. (이때, 반환되는 배열의 길이는 문자열의 길이와 같으며, 주의할 점은 공백 또한 인덱스에 포함된다는 것.)
- 예를 들어, 랜덤으로 골라진 단어가 "HOPE"라면, 'H', 'O', 'P', 'E' 로 쪼개져 배열에 4개의 요소로 담겨질 것이다.
- 그런데...!!! Collections.shuffle()은 List에서만 사용이 가능하다.
그러나 toCharArray는 char[] 배열로 나오기 때문에, List로 전환하는 과정이 필요하다. 따라서...
<br>

#### 3. List<> = new ArrayList 선언
- `List<Character> splitedWords = new ArrayList<Character>();` 형태로 선언해준다.
- <> 안에는 어떤 타입의 레퍼런스를 담을 것인지 명시해준다.
<br>

#### 4. for each문 + add() 메소드 
- 각 배열의 요소에 순서대로 접근하면서 List 배열인 splitedWords에 char[] 배열의 요소를 하나씩 담아줄 것이기 때문에 for each문을 이용한다. 
- 그리고 List의 add 메소드를 통해 배열에 하나씩 담아준다.
- 이렇게 해서 char[]을 List로 만들었다. 여기까지가 Collection.shuffle()을 쓰기 위한 밑밥이었다.
<br>

#### 5. Collections.shuffle();

```
 Collections.shuffle(splitedWords);

```
- shuffle 메소드를 써서 쪼개진 글자가 담긴 배열을 섞어주었다.
<br>

#### 6. StringBuilder와 append() 메소드

```
StringBuilder builder = new StringBuilder();

```
- String 대신에 StringBuilder를 쓴 이유
String은 immutable(불변) 클래스로, 내용의 변경 시 새로운 객체를 만드는 식으로 처리해야 한다.  
반면, StringBuilder는 mutable(변경 가능한) 클래스. 따라서 내용의 변경 시에 새로운 객체가 만들어지지 않고 같은 객체가 계속해서 사용된다.  
따라서 객체를 매번 생성하는 String의 처리보다 StringBuilder의 처리 속도가 더 빠를 수밖에 없다.

```
   	 for(char c : splitedWords) {
   		 builder.append(c);
   		 }

   	 String shuffled = builder.toString();

```

- 그리고 다시 하나의 문자로 합쳐주는 과정이 필요하다... append() 메소드 이용
현재 splitedWords 배열의 요소는 shuffle이 이루어진 한 글자이다. 예) 'O','P','H','E'  
이를 합쳐줘야 한 단어로 출력이 가능할 것.  
이때 append()라는 메소드를 이용한다.  
(append() : 모든 기본형 자료와 문자열, 객체들을 현재의 StringBuilder에 추가하는 메소드.)  

- [참고] append() 메소드 예시 
```
		StringBuilder builder = new StringBuilder();

		builder.append('a');
		System.out.println(builder);

		builder.append('b');
		System.out.println(builder);

		builder.append('c');
		System.out.println(builder);

       // 결과
       a
       ab
       abc

```

#### 7. toString으로 마무리하면 끝!  

