package testtotest.testPrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import testtotest.testPrac.entity.application.Application;
import testtotest.testPrac.entity.application.ApplyStatus;
import testtotest.testPrac.entity.lecture.Lecture;
import testtotest.testPrac.entity.member.Member;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean findByMember_IdAndLecture_Id(Long memberId, Long lectureId);


//    @Query("select a from application a join fetch a")
    List<Application> findAllByMember(Member member);

    List<Application> findAllByLectureId(Long lectureId);

//    Boolean findByMember_IdAndRegistStatus(Long memberId, ApplyStatus applyStatus);

    Boolean existsByMemberIdAndRegistStatusAndLectureId(Long memberId, ApplyStatus applyStatus, Long lectureId);
}
