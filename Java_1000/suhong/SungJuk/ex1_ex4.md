# 성적 처리 문제 1~4 해설
> 문제 출처 : '남궁성으 코드초보스터디'(네이버 카페)의 자바1000제 카테고리

## 문제1: Student 클래스 구현

1. 인스턴스 변수 정의
2. 생성자 만들기
3. toString() overriding

그냥 기초 연습

## 문제2: compareTo() 메소드 구현

### Collections.sort() 와 compareTo 그리고 Comparable에 대해서

어떤 배열을 정렬하기 위해서는 배열의 구성물들을 어떤 순서로 정렬할지 그 기준을 정해줘야합니다.  
그렇다면 `Student`의 `List`를 총점 순으로 정렬하기 위해서는 어떤 `Student`가 더 총점이 높거나 낮은지를 판별할 수 있는 방법을 마련해 줘야합니다.  

`Collections.sort()`는 기준을 알 수 없는 타입의 `List`를 받지 않기 위해서 `Comparable` 인터페이스를 `implement`한 타입만을 받아들이도록 되어있습니다.  
즉, `Comparable` 인터페이스는 타입의 대소를 구분할 수 있는 메소드를 구현하도록 강제하는 역할을 하는 것입니다.  
그렇기 때문에 sort를 사용할 `List<Student>`의 `Student`는 반드시 `Comparable`이라는 인터페이스를 `implement` 해야만 합니다.  
`Comparable` 인터페이스에는 `compareTo()`라는 메소드가 있어 `implement`하는 클래스에서 반드시 구현해줘야합니다.  
두 Student를 비교했을 떄 어떤 Student가 더 큰지 작은지를 int 값으로 return 해주는 `compareTo`라는 메소드를 구현해보도록 하겠습니다.

### compareTo의 구현

`Collections.sort()`는 `List`의 원소들을 작은것부터 큰것 순으로 나열합니다.  
숫자를 나열한다면 `1 2 3 4 5`와 같은 순으로 정령됩니다.  
현재 저희의 목표는 총점이 높은 것이 먼저 나오도록 하는 것이므로 점수가 더 높은 학생이 더 작은 것으로 인식하도록 해야합니다.  

`compareTo(obj)`는 객체 자신과 obj를 비교해  
자신이 더 작다면 음수를  
자신이 더 크다면 양수를  
서로 같다면 0을  

반환하는 메소드 입니다.

> 총점이 더 클수록 작은 것이다 => 총점이 더 크면 음수를 반환
```java
public int compareTo(Student obj) {
    return obj.total - this.total;
}
```
위와 같이 구현하면 `Collections.sort()`에 의해총점이 큰 학생부터 차례로 정렬됩니다.

## 문제3: Comparator 인터페이스와 인터페이스 구현

### Comparator 인터페이스
`Collections.sort()`는 `List`만 받는 방식과 `List` 그리고 `Comparator`를 받는 방식 두가지가 있습니다.  
`List`만 받는 방식은 `Student`에서 정의된 `compareTo`를 기준으로 대소를 판별하지만  
`List`와 `Comparator`를 같이 받는 방식은 `List`를 `Comparator`의 `compare(obj1, obj2)`의 기준에 따라 대소를 판별하게 됩니다.  

### 인터페이스 구현
> 1. 총점에 따라 대소 판별
```java
class ClassTotalComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s2.total - s1.total;
    }
}
```
총점에 따라 대소를 판별하는 것은 2번 문제와 사실상 동일합니다.

> 2. 반 그리고 학생 번호에 따라 대소 판별
```java
class ClassStudentNoComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        int classNoCompare = s1.classNo - s2.classNo;
        if (classNoCompare != 0) {
            return classNoCompare;
        }
        int studentNoCompare = s1.studentNo - s2.studentNo;
        return studentNoCompare;
    }
}
```
먼제 반에 따라 대소를 판별하고 반이 같다면 학생 번호를 기준으로 다시 한번 판별하는 방법입니다.

## 문제4: calculateSchoolRank() 메소드 구현

문제에서 학생들을 총점 순으로 나열을 해줬습니다.  
즉 3번째 학생은 위의 학생과 동점일 경우 3보다 낮은 등수를 받을 수는 있지만 그렇지 않다면 자연스럽게 3등이 됩니다.  
그렇다면 앞의 학생과 총점을 비교해  

- 총점이 같다면 앞의 학생과 같은 등수를  
- 다르다면(낮다면) 나열된 순서의 번호를  

등수로 준다면 될 것입니다.

```java
for (int i = 0 ; i < length; i++) {
    Student currentStudent = list.get(i);
    int currentTotal = currentStudent.total;
    if (currentTotal == prevTotal) { // 총점이 같은 경우 -> 등수도 이전 학생 등수와 같다.
        currentStudent.schoolRank = prevRank;
    } else { // 총점이 다르다(더 낮다) -> 등수는 i + 1, 등수 및 총점 기준 변경
        currentStudent.schoolRank = i + 1;
        prevRank = i + 1;
        prevTotal = currentStudent.total;
    }
}
```

