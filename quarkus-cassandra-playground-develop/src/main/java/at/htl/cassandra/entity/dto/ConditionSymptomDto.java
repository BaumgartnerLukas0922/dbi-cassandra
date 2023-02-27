package at.htl.cassandra.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConditionSymptomDto {
    private Long id;
    private String conditionName;
    private int symptomCount;
}
