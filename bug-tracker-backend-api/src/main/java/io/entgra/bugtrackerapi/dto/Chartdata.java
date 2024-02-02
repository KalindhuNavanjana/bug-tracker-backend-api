package io.entgra.bugtrackerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chartdata{
    private int openCount;
    private int inProgressCount;
    private int waitingCount;
    private int resolvedCount;
    private int totalCount;
}
