package testtotest.testPrac;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import testtotest.testPrac.entity.lecture.Lecture;
import testtotest.testPrac.entity.member.Member;
import testtotest.testPrac.entity.member.Role;
import testtotest.testPrac.repository.LectureRepository;
import testtotest.testPrac.repository.MemberRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInit {

    private final LectureRepository lectureRepository;

    private final MemberRepository memberRepository;


    @PostConstruct
    void init(){
        Member lee = Member.builder()
                .role(Role.USER)
                .email("asd@naver.com")
                .number("010-2323-2323")
                .applyCnt(0)
                .password("12312312123213")
                .userName("이태민")
                .build();
        memberRepository.save(lee);

        Member jung = Member.builder()
                .role(Role.USER)
                .email("qwe@kakao.com")
                .number("010-1242-5674")
                .password("12312312123213")
                .applyCnt(0)
                .userName("정상윤")
                .build();
        memberRepository.save(jung);

        Member hong = Member.builder()
                .role(Role.USER)
                .email("somefood@naver.com")
                .number("010-3992-5832")
                .applyCnt(0)
                .userName("홍석주")
                .password("12312312123213")
                .build();
        memberRepository.save(hong);

        Lecture lecture1 = Lecture.builder()
                .lectureName("정상기간")
                .applyStart(LocalDateTime.now().minusDays(10))
                .applyEnd(LocalDateTime.now().plusDays(20))
                .startLectureDate(LocalDateTime.now().plusDays(40))
                .endLectureDate(LocalDateTime.now().plusDays(70))
                .build();
        lectureRepository.save(lecture1);

        Lecture lecture2 = Lecture.builder()
                .lectureName("지난기간")
                .applyStart(LocalDateTime.now().minusDays(40))
                .applyEnd(LocalDateTime.now().minusDays(20))
                .startLectureDate(LocalDateTime.now())
                .endLectureDate(LocalDateTime.now().plusDays(70))
                .build();
        lectureRepository.save(lecture2);

        Lecture lecture3 = Lecture.builder()
                .lectureName("아직오지않은 기간")
                .applyStart(LocalDateTime.now().plusDays(10))
                .applyEnd(LocalDateTime.now().plusDays(30))
                .startLectureDate(LocalDateTime.now().plusDays(80))
                .endLectureDate(LocalDateTime.now().plusDays(100))
                .build();
        lectureRepository.save(lecture3);




    }
}
