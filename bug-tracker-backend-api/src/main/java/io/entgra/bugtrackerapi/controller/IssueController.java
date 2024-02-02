package io.entgra.bugtrackerapi.controller;

import io.entgra.bugtrackerapi.dto.Chartdata;
import io.entgra.bugtrackerapi.dto.IssueDto;
import io.entgra.bugtrackerapi.dto.StatusHistoryDto;
import io.entgra.bugtrackerapi.dto.UserDto;
import io.entgra.bugtrackerapi.service.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueController {
    private IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueDto>> getAllIssues(){
        List<IssueDto> issues = issueService.getAllIssues();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssueDto> getIssueByID(@PathVariable("id") Long issueID){
        IssueDto issueDto = issueService.getIssue(issueID);
        return ResponseEntity.ok(issueDto);
    }

    @PostMapping
    public ResponseEntity<IssueDto> newIssue(@RequestBody IssueDto issue){
        IssueDto issueDto = issueService.createIssue(issue);
        return new ResponseEntity<>(issueDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssueDto> updateIssueByID(@PathVariable("id") Long issueID, @RequestBody IssueDto updatedIssue){
        IssueDto issueDto = issueService.updateIssue(issueID,updatedIssue);
        return ResponseEntity.ok(issueDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity<IssueDto> updateIssueStatus(@PathVariable("id") Long issueID, @RequestBody IssueDto updatedIssue){
        String status = updatedIssue.getStatus();
        IssueDto updatedIssueDto = issueService.setStatus(issueID,status);
        return ResponseEntity.ok(updatedIssueDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<IssueDto> getIssue(@PathVariable("id") Long issueID){
        IssueDto issueDto = issueService.deleteIssue(issueID);
        return ResponseEntity.ok(issueDto);
    }

    @GetMapping("{id}/history")
    public ResponseEntity<List<StatusHistoryDto>> getIssueHistory(@PathVariable("id") Long issueID){
        List<StatusHistoryDto> issueHistory = issueService.getIssueHistory(issueID);
        return ResponseEntity.ok(issueHistory);
    }

    @GetMapping("status/count")
    public ResponseEntity<Chartdata> getChartData(){
        Chartdata chartdata = issueService.getChartData();
        return ResponseEntity.ok((chartdata));
    }
}
