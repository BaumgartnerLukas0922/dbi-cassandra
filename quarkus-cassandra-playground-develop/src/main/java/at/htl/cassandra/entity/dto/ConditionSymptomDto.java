package at.htl.cassandra.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConditionSymptomDto implements Comparable<ConditionSymptomDto>{
    private Long id;
    private String name;
    private int symptomsCount;

    @Override
    public int compareTo(ConditionSymptomDto conditionSymptomDto) {
        return Integer.compare(conditionSymptomDto.symptomsCount, this.symptomsCount);
    }
}
