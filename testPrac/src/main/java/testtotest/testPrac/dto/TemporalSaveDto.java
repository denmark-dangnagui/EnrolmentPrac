package testtotest.testPrac.dto;

import lombok.Getter;
import lombok.Setter;
import testtotest.testPrac.entity.application.ApplyStatus;

@Getter
@Setter
public class TemporalSaveDto {
    private String title;

    private String lectureName;

    private Long applyId;

    private ApplyStatus applyStatus;
}
