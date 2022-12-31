package testtotest.testPrac.entity.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import testtotest.testPrac.entity.lecture.Lecture;
import testtotest.testPrac.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Lecture lecture;

    @Enumerated(EnumType.STRING)
    private ApplyStatus registStatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime applyDate;

    private boolean isModify;

    private String applyMotivation;

    private String title;

    private String content;

}
