package io.entgra.bugtrackerapi.mapper;

import io.entgra.bugtrackerapi.dto.StatusHistoryDto;
import io.entgra.bugtrackerapi.entity.Issue;
import io.entgra.bugtrackerapi.entity.StatusHistory;
import io.entgra.bugtrackerapi.exception.ResourceNotFound;
import io.entgra.bugtrackerapi.repository.IssueRepository;
import io.entgra.bugtrackerapi.service.IssueService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StatusHistoryMapper {
    private IssueRepository issueRepository;
    public static StatusHistoryDto mapToStatusHistoryDto(StatusHistory statusHistory){
        return new StatusHistoryDto(
                statusHistory.getId(),
                statusHistory.getIssue().getId(),
                statusHistory.getStatus(),
                statusHistory.getTimestamp()
        );
    }

    public StatusHistory mapToStatusHistory(StatusHistoryDto statusHistoryDto){
        return new StatusHistory(
                statusHistoryDto.getId(),
                getIssueById(statusHistoryDto.getIssueId()) ,
                statusHistoryDto.getStatus(),
                statusHistoryDto.getTimestamp()
        );
    }

    private Issue getIssueById(Long id){
        return issueRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Issue Not Found")
        );
    }
}
