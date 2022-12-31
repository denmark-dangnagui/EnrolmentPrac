package testtotest.testPrac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtotest.testPrac.dto.ApplyDto;
import testtotest.testPrac.dto.TemporalSaveDto;
import testtotest.testPrac.entity.application.Application;
import testtotest.testPrac.entity.application.ApplyStatus;
import testtotest.testPrac.entity.lecture.Lecture;
import testtotest.testPrac.entity.member.Member;
import testtotest.testPrac.exception.DoneException;
import testtotest.testPrac.exception.OverApplyException;
import testtotest.testPrac.exception.OverDateException;
import testtotest.testPrac.repository.ApplicationRepository;
import testtotest.testPrac.repository.LectureRepository;
import testtotest.testPrac.repository.MemberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final ApplicationRepository applicationRepository;

    private final MemberRepository memberRepository;

    private final LectureRepository lectureRepository;

    //임시저장
    @Transactional
    public void temporal(ApplyDto applyDto) {
        Application application = new Application();
        LocalDateTime now = LocalDateTime.now();
        Member member = memberRepository.findById(applyDto.getMemberId()).orElseThrow();
        Lecture lecture = lectureRepository.findById(applyDto.getLectureId()).orElseThrow();
        overApply(member);
        application.setMember(member);
        application.setLecture(lecture);
        application.setApplyDate(now);
        application.setApplyMotivation(applyDto.getMotivation());
        application.setRegistStatus(ApplyStatus.ING);
        application.setTitle(applyDto.getTitle());
        application.setContent(applyDto.getContent());
        application.setModify(true);
        overDate(application, lecture);
        applicationRepository.save(application);
        member.setApplyCnt(member.getApplyCnt() + 1);
    }

    //저장한 지원서 리스트 보기
    public List<TemporalSaveDto> getAllTemp(Member member) {
        List<TemporalSaveDto> result = new ArrayList<>();
        List<Application> applicationList = applicationRepository.findAllByMember(member);
        for (Application a : applicationList) {
            TemporalSaveDto temporalSaveDto = new TemporalSaveDto();
            temporalSaveDto.setApplyStatus(a.getRegistStatus());
            temporalSaveDto.setApplyId(a.getId());
            temporalSaveDto.setTitle(a.getTitle());
            temporalSaveDto.setLectureName(a.getLecture().getLectureName());
            result.add(temporalSaveDto);
        }
        return result;
    }

    // 지원자가 지원한 글 리스트 보기
    public List<TemporalSaveDto> getAllApplyList(Member member) {
        List<TemporalSaveDto> result = new ArrayList<>();
        List<Application> applicationList = applicationRepository.findAllByMember(member);
        for (Application a : applicationList) {
            TemporalSaveDto temporalSaveDto = new TemporalSaveDto();
            temporalSaveDto.setTitle(a.getTitle());
            temporalSaveDto.setLectureName(a.getLecture().getLectureName());
            result.add(temporalSaveDto);
        }
        return result;
    }

    //    //임시저장한 지원서 단건 조회
    public ApplyDto getOneApplication(Long applyId) {
        ApplyDto dto = new ApplyDto();
        Application findApplication = applicationRepository.findById(applyId).orElseThrow();
        dto.setApplyId(findApplication.getId());
        dto.setMemberId(findApplication.getMember().getId());
        dto.setTitle(findApplication.getTitle());
        dto.setContent(findApplication.getContent());
        dto.setMotivation(findApplication.getApplyMotivation());
        dto.setLectureId(findApplication.getLecture().getId());
        dto.setApplyDate(findApplication.getApplyDate());
        return dto;
    }


    //임시저장한 글 수정하기
    @Transactional
    public void updateApply(ApplyDto applyDto, Long applyId) {
        Application application = applicationRepository.findById(applyId).orElseThrow();
        if (application.getRegistStatus().equals(ApplyStatus.DONE)) {
            throw new DoneException("이미 지원완료된 지원서입니다!");
        }
        application.setTitle(applyDto.getTitle());
        application.setApplyDate(LocalDateTime.now());
        application.setContent(applyDto.getContent());
        application.setApplyMotivation(applyDto.getMotivation());
    }

    //지원서 삭제하기
    @Transactional
    public void deleteApply(Long applyId) {
        Application application = applicationRepository.findById(applyId).orElseThrow();
        if (application.getRegistStatus().equals(ApplyStatus.DONE)) {
            throw new DoneException("지원완료된 지원서는 삭제할 수 없습니다!");
        }
        Member member = application.getMember();
        member.setApplyCnt(member.getApplyCnt() - 1);
        applicationRepository.deleteById(applyId);
    }

    //최종지원
    @Transactional
    public void done(ApplyDto applyDto) {
        Member member = memberRepository.findById(applyDto.getMemberId()).orElseThrow();
        if (member.getApplyCnt() > 4) {
            throw new OverApplyException("지원서를 작성할 수 있는 갯수를 넘겼습니다! 임시저장한 글을 지우시거나 그 글을 수정해서 지원해주세요!");
        }
        if (applicationRepository.existsByMemberIdAndRegistStatusAndLectureId(applyDto.getMemberId(), ApplyStatus.DONE, applyDto.getLectureId())) { // 최종지원
            throw new OverApplyException("같은 수업에 최종지원을 두번 할 수 없읍니다!");
        }
        Application application = new Application();
        LocalDateTime now = LocalDateTime.now();
        Lecture lecture = lectureRepository.findById(applyDto.getLectureId()).orElseThrow();
//        if (statusList.contains(application.getRegistStatus().equals(ApplyStatus.DONE)))
        application.setMember(member);
        application.setLecture(lecture);
        application.setApplyDate(now);
        application.setApplyMotivation(applyDto.getMotivation());
        application.setRegistStatus(ApplyStatus.DONE);
        application.setContent(applyDto.getContent());
        application.setTitle(applyDto.getTitle());
        application.setModify(false);
        overDate(application, lecture);
        applicationRepository.save(application);
        member.setApplyCnt(member.getApplyCnt() + 1);
    }

    @Transactional
    public void changeStatus() {
        Lecture lecture = lectureRepository.findById(1L).orElseThrow();
        lecture.setApplyEnd(LocalDateTime.now().minusDays(100));
        List<Application> ingList = applicationRepository.findAllByLectureId(1L).stream().filter(l -> l.getRegistStatus().equals(ApplyStatus.ING)).collect(Collectors.toList());
        for (Application application : ingList) {
            Member member = application.getMember();
            member.setApplyCnt(member.getApplyCnt() - 1);
            application.setRegistStatus(ApplyStatus.FAIL);
        }
    }


    private static void overDate(Application application, Lecture lecture) {
        if (!(lecture.getApplyStart().isBefore(application.getApplyDate()) && lecture.getApplyEnd().isAfter(application.getApplyDate()))) {
            throw new OverDateException("모집기간이 아닙니다.");
        }
    }

    private static void overApply(Member member) {
        log.info("Method Acnt : {}", member.getApplyCnt());
        if (member.getApplyCnt() > 4) {
            throw new OverApplyException("더 이상 지원서를 작성하실 수 없읍니다.");
        }
    }

}
