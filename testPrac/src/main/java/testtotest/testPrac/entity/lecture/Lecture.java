package testtotest.testPrac.entity.lecture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testtotest.testPrac.entity.application.Application;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lectureName;

    private LocalDateTime startLectureDate;

    private LocalDateTime endLectureDate;

    private LocalDateTime applyStart;

    private LocalDateTime applyEnd;

    @OneToMany(mappedBy = "lecture")
    private List<Application> applicationList = new ArrayList<>();

    //수업 3개를 만들어보자.


    @Builder
    public Lecture(String lectureName, LocalDateTime startLectureDate, LocalDateTime endLectureDate, LocalDateTime applyStart, LocalDateTime applyEnd, List<Application> applicationList) {
        this.lectureName = lectureName;
        this.startLectureDate = startLectureDate;
        this.endLectureDate = endLectureDate;
        this.applyStart = applyStart;
        this.applyEnd = applyEnd;
        this.applicationList = applicationList;
    }
}
