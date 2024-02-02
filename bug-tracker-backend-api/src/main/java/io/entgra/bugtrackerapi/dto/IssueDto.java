package io.entgra.bugtrackerapi.dto;

import io.entgra.bugtrackerapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {
    private Long id;
    private String title;
    private String description;
    private String type;
    private String status;
    private User user;
}
