package org.suhong.sungjuk.ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SungJukEx2 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();

        // 이름, 반, 번호, 국어, 수학, 영어
        list.add(new Student("남궁성", 3,2,100,100,100));
        list.add(new Student("왕자바", 3,1,90,100,80));
        list.add(new Student("자바왕", 3,3,70,100,100));
        list.add(new Student("킹왕짱", 1,2,100,60,90));
        list.add(new Student("자바짱", 1,1,100,100,100));
        list.add(new Student("최고수", 1,3,100,80,60));
        list.add(new Student("홍길동", 2,1,50,80,100));
        list.add(new Student("일지매", 2,3,70,80,100));
        list.add(new Student("변강쇠", 2,4,80,80,85));
        list.add(new Student("이원구", 2,2,90,90,90));

        Collections.sort(list);
        printList(list);
    }

    public static void printList(List<Student> list) {
        System.out.println("이름\t학년\t반\t국어\t수학\t영어\t총점 ");
        System.out.println("====================================================");

        for(Student s : list) {
            System.out.println(s);
        }

        System.out.println("====================================================");
    }
}

class Student implements Comparable<Student> {
    String name = "";
    int classNo = 0;
    int studentNo = 0;
    int koreanScore = 0;
    int mathScore = 0;
    int englishScore = 0;
    int total = 0;

    Student(String name, int classNo, int studentNo, int koreanScore, int mathScore, int englishScore) {
        this.name = name;
        this.classNo = classNo;
        this.studentNo = studentNo;
        this.koreanScore = koreanScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;

        total = koreanScore + mathScore + englishScore;
    }

    public String toString() {
        return name + "\t"
                + classNo + "\t"
                + studentNo + "\t"
                + koreanScore + "\t"
                + mathScore + "\t"
                + englishScore + "\t"
                + total + "\t";
    }

    // 문제2
    public int compareTo(Student obj) {
        return obj.total - this.total;
    }
} // end of class Student


/*
[실행결과] - 이전 결과와는 달리, 총점이 높은순서에서 낮은 순서로 내림차순 정렬되어있음을 확인하세요.

        이름    반     번호    국어    수학    영어    총점
        ====================================================
        남궁성  3       2       100     100     100     300
        자바짱  1       1       100     100     100     300
        왕자바  3       1       90      100     80      270
        자바왕  3       3       70      100     100     270
        이원구  2       2       90      90      90      270
        킹왕짱  1       2       100     60      90      250
        일지매  2       3       70      80      100     250
        변강쇠  2       4       80      80      85      245
        최고수  1       3       100     80      60      240
        홍길동  2       1       50      80      100     230
        ====================================================
*/
