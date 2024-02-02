package io.entgra.bugtrackerapi.service.impl;

import io.entgra.bugtrackerapi.dto.Chartdata;
import io.entgra.bugtrackerapi.dto.IssueDto;
import io.entgra.bugtrackerapi.dto.StatusHistoryDto;
import io.entgra.bugtrackerapi.entity.Issue;
import io.entgra.bugtrackerapi.entity.StatusHistory;
import io.entgra.bugtrackerapi.entity.User;
import io.entgra.bugtrackerapi.exception.ResourceNotFound;
import io.entgra.bugtrackerapi.mapper.IssueMapper;
import io.entgra.bugtrackerapi.mapper.StatusHistoryMapper;
import io.entgra.bugtrackerapi.repository.IssueRepository;
import io.entgra.bugtrackerapi.repository.StatusHistoryRepository;
import io.entgra.bugtrackerapi.service.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    private IssueRepository issueRepository;
    private StatusHistoryRepository statusHistoryRepository;
    @Override
    public List<IssueDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream().map(
                (issue)->IssueMapper.mapToIssueDto(issue)
        ).collect(Collectors.toList());
    }

    @Override
    public IssueDto getIssue(Long issueID) {
        Issue issue = issueRepository.findById(issueID).orElseThrow(
                ()-> new ResourceNotFound("Issue Not Found")
        );
        return IssueMapper.mapToIssueDto(issue);
    }

    @Override
    public IssueDto createIssue(IssueDto issueDto) {
        Issue issue = IssueMapper.mapToIssue(issueDto);
        issue.setStatus("Open");
        Issue savedIssue = issueRepository.save(issue);


        StatusHistory statusHistory = new StatusHistory( savedIssue, "Open");
        statusHistoryRepository.save(statusHistory);

        return IssueMapper.mapToIssueDto(savedIssue);
    }

    @Override
    public IssueDto updateIssue(Long issueID, IssueDto issueDto) {
        Issue issue = issueRepository.findById(issueID).orElseThrow(
                ()-> new ResourceNotFound("Issue Not Found")
        );
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setStatus(issueDto.getStatus());
        issue.setType(issueDto.getType());

        Issue updatedIssue = issueRepository.save(issue);
        return IssueMapper.mapToIssueDto(updatedIssue);
    }

    @Override
    public IssueDto setStatus(Long issueID, String status) {
        Issue issue = issueRepository.findById(issueID).orElseThrow(
                ()-> new ResourceNotFound("Issue Not Found")
        );
        issue.setStatus(status);

        Issue updatedIssue = issueRepository.save(issue);

        StatusHistory statusHistory = new StatusHistory( updatedIssue, status);
        statusHistoryRepository.save(statusHistory);

        return IssueMapper.mapToIssueDto(updatedIssue);
    }

    @Override
    public IssueDto deleteIssue(Long issueID) {
        Issue issue = issueRepository.findById(issueID).orElseThrow(
                ()-> new ResourceNotFound("Issue Not Found")
        );

        issueRepository.deleteById(issueID);

        return IssueMapper.mapToIssueDto(issue);
    }

    @Override
    public List<StatusHistoryDto> getIssueHistory(Long issueID) {
        List<StatusHistory> records = statusHistoryRepository.findByIssueId(issueID);
        return records.stream().map(
                (record)-> StatusHistoryMapper.mapToStatusHistoryDto(record)
        ).collect(Collectors.toList());
    }

    @Override
    public Chartdata getChartData() {
        return new Chartdata(
                issueRepository.countByStatus("Open"),
                issueRepository.countByStatus("In-Progress"),
                issueRepository.countByStatus("Waiting on Client"),
                issueRepository.countByStatus("Resolved"),
                issueRepository.findAll().size()
        );
    }
}
