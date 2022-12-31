package testtotest.testPrac.dto;

import lombok.Getter;
import lombok.Setter;
import testtotest.testPrac.entity.application.ApplyStatus;

@Getter
@Setter
public class ResultDto {

    private Long applyId;

    private ApplyStatus applyStatus;
}
