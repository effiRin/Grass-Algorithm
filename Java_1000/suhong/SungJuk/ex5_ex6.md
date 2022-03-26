# 성적 처리 문제 5,6 해설
> 문제 출처 : '남궁성으 코드초보스터디'(네이버 카페)의 자바1000제 카테고리

## 문제5: 반별 등수 메기기

문제4에서 했던 전체 등수 메기기는 전체를 점수순으로 나열한 뒤 맨 위에서부터 차례로 등수를 주면 되는 문제였습니다.  

이번 문제는 조금 더 복잡합니다.

먼저 반별로 학생들을 정렬하고 그 안에서 점수순으로 정렬한 뒤 학생들이 각 반에서 몇 등인지를 메겨야하는 문제 입니다.

### 알고리즘 설명

우선 같은 반 학생이라면 위에 있는 학생은 아래 있는 학생과 점수가 같거나 더 높습니다.  

그렇기 때문에 각 반에서 등수를 메기는 방법은 문제4의 전체 등수를 메기는 알고리즘과 동일 합니다.

주의 해야할 점은 한 반의 학생에 대한 등수 메기기가 끝나고 그 다음 학생, 즉 다른 반 학생으로 넘어갈 때 기존에 하던 작업을 초기화 해줘야한다는 점 입니다.

- 현재 학생과 이전 학생의 반이 다를 때 :  
    - 이전 반, 이전 등수, 이전 총점 변수를 초기화 해준다.
- 현재 학생과 이전 학생의 반이 같을 때 : 
    - 1. 현재 학생과 이전 학생의 총점이 다를 때 -> 현재 학생이 몇번 쨰 학생인지를 확인하고 반 등수를 메긴다. 이전 총점 변수를 최신화
    - 2. 현재 학생과 이전 학생의 총점이 같을 때 -> 이전 학생의 등수를 그대로 사용한다.

```java
public static void calculateClassRank(List<Student> list) {
        Collections.sort(list, new ClassTotalComparator()); // 먼저 반별 총점기준 내림차순으로 정렬한다.

        int prevClassNo = -1;
        int prevRank = -1;
        int currentRank = -1;
        int prevTotal = -1;
        int length = list.size();

        for (Student student : list) {
            if (student.classNo != prevClassNo) {
                prevClassNo = student.classNo;
                student.classRank = 1;
                prevTotal = -1;
                prevRank = 1;
                currentRank = 1;
                continue;
            }

            if (student.total == prevTotal) {
                student.classRank = prevRank;
                currentRank++;
                continue;
            }

            student.classRank = ++currentRank;
            prevRank = currentRank;
            prevTotal = student.total;
        }
    }
```

## 문제6: 문자열 예쁘게 만들기

솔직히 이 문제는 왜 만들었는지 잘 모르겠지만, 알고리즘스러운 부분이 약간 있어서 그냥 풀기로 했습니다.(궁성궁성)

이 문제에서 요구하는 기능은 문자열을 왼쪽에 정렬할지 가운데에 정렬할지 오른쪽에 정렬할지를 고를 수 있도록 하는 기능 입니다.

왼쪽, 오른쪽 정렬은 비교적 간단한 알고리즘으로 구현할 수 있습니다.

맨 왼쪽부터 혹은 오른쪽부터 문자열을 채워나가면 되죠.

가운데 정렬은 문자열을 넣을 문자열 그리고 내용물로 들어갈 문자열의 길이 차를 구하고 그 절반 만큼의 여유를 양쪽 끝에 주어야 합니다.

```java
public static String format(String str, int length, int alignment) {

        if(str==null) str = "";
        int diff = length - str.length();

        // 주어진 문자열(str)의 길이보다 length의 값이 작으면 str를 length만큼만 남기고 잘라낸다.

        // 예를 들어, str이 "012345"이고, length가 3이면 결과는 "012"가 된다.
        if(diff < 0) return str.substring(0, length);

        char[] source = str.toCharArray();
        char[] result = new char[length];

        // 배열 result를 공백으로 채운다.
        for(int i=0; i < result.length; i++) {
            result[i] = ' ';
        }

        switch (alignment) {
            case 1: // CENTER
                for (int i = diff / 2, j = 0; i < length - diff / 2 && j < source.length; i++, j++) {
                    result[i] = source[j];
                }
                break;
            case 2: // RIGHT
                for (int i = diff, j = 0; i < length && j < source.length; i++, j++) {
                    result[i] = source[j];
                }
                break;
            default: // LEFT and else
                for (int i = 0; i <source.length; i++) {
                    result[i] = source[i];
                }
                break;
        }
        
        return new String(result);
    }
```

