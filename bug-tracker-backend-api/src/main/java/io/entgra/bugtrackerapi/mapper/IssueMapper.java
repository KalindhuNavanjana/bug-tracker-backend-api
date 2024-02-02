package io.entgra.bugtrackerapi.mapper;

import io.entgra.bugtrackerapi.dto.IssueDto;
import io.entgra.bugtrackerapi.entity.Issue;

public class IssueMapper {
    public static IssueDto mapToIssueDto(Issue issue){
        return new IssueDto(
                issue.getId(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getType(),
                issue.getStatus(),
                issue.getUser()
        );
    }

    public static Issue mapToIssue(IssueDto issueDto){
        return new Issue(
                issueDto.getId(),
                issueDto.getTitle(),
                issueDto.getDescription(),
                issueDto.getType(),
                issueDto.getStatus(),
                issueDto.getUser()
        );
    }
}
