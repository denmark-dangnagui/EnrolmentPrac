package testtotest.testPrac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testtotest.testPrac.dto.ApplyDto;
import testtotest.testPrac.dto.TemporalSaveDto;
import testtotest.testPrac.entity.application.Application;
import testtotest.testPrac.entity.member.Member;
import testtotest.testPrac.repository.MemberRepository;
import testtotest.testPrac.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    //임시저장
    @PostMapping("/temp")
    public void temporalSave(@RequestBody ApplyDto applyDto){
        memberService.temporal(applyDto);
    }

    //임시저장중인 리스트 보기
    @GetMapping("/temp/{memberId}")
    public ResponseEntity<?> getAllTemp(@PathVariable Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<TemporalSaveDto> dto = memberService.getAllTemp(member);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //임시저장중인 단건 조회
    @GetMapping("/temp/apply/{applyId}")
    public ResponseEntity<?> getOneApply(@PathVariable Long applyId){
        ApplyDto dto = memberService.getOneApplication(applyId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //임시저장중인 단건 조회 후 수정시
    @PutMapping("/temp/update/{applyId}")
    public ResponseEntity<?> updateApply(@PathVariable Long applyId, @RequestBody ApplyDto applyDto){
        memberService.updateApply(applyDto,applyId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //지원서 삭제
    @DeleteMapping("/temp/delete/{applyId}")
    public ResponseEntity<?> deleteApply(@PathVariable Long applyId){
        memberService.deleteApply(applyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //최종지원
    @PostMapping("/finalApply")
    public ResponseEntity<?> finalApply(@RequestBody ApplyDto applyDto){
        memberService.done(applyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/exceed")
    public ResponseEntity<?> changeStatus(){
        memberService.changeStatus();
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
