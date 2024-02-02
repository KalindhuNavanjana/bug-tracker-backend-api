package io.entgra.bugtrackerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistoryDto {
    private Long id;
    private Long issueId;
    private String status;
    private Timestamp timestamp;
}
