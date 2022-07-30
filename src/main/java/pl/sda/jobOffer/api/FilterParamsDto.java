package pl.sda.jobOffer.api;

import lombok.*;

import java.util.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FilterParamsDto {

    private String location;
    private Double  minSalary;
    private Double  maxSalary;

    public Double getMinSalaryOrDefault() {
        return Objects.requireNonNullElse(minSalary, Double.MIN_VALUE);
    }

    public Double getMaxSalaryOrDefault() {
        return Objects.requireNonNullElse(maxSalary, Double.MAX_VALUE);
    }
}
