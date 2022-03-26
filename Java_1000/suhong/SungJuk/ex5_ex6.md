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



