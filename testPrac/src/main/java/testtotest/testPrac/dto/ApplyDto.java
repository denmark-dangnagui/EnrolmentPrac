package testtotest.testPrac.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import testtotest.testPrac.entity.application.ApplyStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApplyDto {

    private Long applyId;

    private Long lectureId;

    private Long memberId;

    private String motivation;

    private String title;

    private String content;

    private LocalDateTime applyDate;
}
