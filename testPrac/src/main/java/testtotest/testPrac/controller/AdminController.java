package testtotest.testPrac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testtotest.testPrac.dto.ResultDto;
import testtotest.testPrac.dto.TemporalSaveDto;
import testtotest.testPrac.entity.member.Member;
import testtotest.testPrac.repository.MemberRepository;
import testtotest.testPrac.service.AdminService;
import testtotest.testPrac.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final MemberRepository memberRepository;

    private final AdminService adminService;
    @GetMapping("/check-status/{memberId}")
    public ResponseEntity<?> checkStatus(@PathVariable Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<TemporalSaveDto> dto = adminService.getAllapply(member);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/result")
    public ResponseEntity<?> outcome(@RequestBody ResultDto resultDto){
        adminService.giveResult(resultDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
