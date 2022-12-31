package testtotest.testPrac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtotest.testPrac.dto.ResultDto;
import testtotest.testPrac.dto.TemporalSaveDto;
import testtotest.testPrac.entity.application.Application;
import testtotest.testPrac.entity.application.ApplyStatus;
import testtotest.testPrac.entity.member.Member;
import testtotest.testPrac.exception.BeforeSubmitException;
import testtotest.testPrac.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final ApplicationRepository applicationRepository;


    public List<TemporalSaveDto> getAllapply(Member member) {
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

    @Transactional
    public void giveResult(ResultDto resultDto){
        Application application = applicationRepository.findById(resultDto.getApplyId()).orElseThrow();
        if (application.getRegistStatus().equals(ApplyStatus.DONE)){
            application.setRegistStatus(resultDto.getApplyStatus());
        }else if (application.getRegistStatus().equals(ApplyStatus.ING)){
            throw new BeforeSubmitException("아직 지원완료되지 않은 지원서입니다!");
        }else {
            throw new BeforeSubmitException("이미 결과가 정해진 지원서입니다!");
        }
    }
}
