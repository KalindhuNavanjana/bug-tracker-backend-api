package io.entgra.bugtrackerapi.service;
import io.entgra.bugtrackerapi.dto.Chartdata;
import io.entgra.bugtrackerapi.dto.IssueDto;
import io.entgra.bugtrackerapi.dto.StatusHistoryDto;
import io.entgra.bugtrackerapi.dto.UserDto;
import io.entgra.bugtrackerapi.entity.StatusHistory;

import java.util.List;

public interface IssueService {
    List<IssueDto> getAllIssues();
    IssueDto getIssue(Long issueID);
    IssueDto createIssue(IssueDto issueDto);
    IssueDto updateIssue(Long issueID, IssueDto issueDto);
    IssueDto setStatus(Long issueID, String status);
    IssueDto deleteIssue(Long issueID);

    // GET all status updates for a specific issue
    List<StatusHistoryDto> getIssueHistory(Long issueID);

    //GET the count of issues by status
    Chartdata getChartData();
}
