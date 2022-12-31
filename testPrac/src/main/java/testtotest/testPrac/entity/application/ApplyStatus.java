package testtotest.testPrac.entity.application;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApplyStatus {
    ING("지원중"),
    DONE("지원완료"),
    PASS("합격"),
    FAIL("불합격");


    private String korean;

    public String getKorean() {
        return korean;
    }
}
