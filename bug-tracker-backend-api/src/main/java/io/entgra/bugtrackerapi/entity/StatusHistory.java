package io.entgra.bugtrackerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status_history_records")
public class StatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private Issue issue;

    @Column(name = "status")
    private String status;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public StatusHistory(Issue issue, String status) {
        this.issue= issue;
        this.status= status;
        long currentTimeMillis = System.currentTimeMillis();
        this.timestamp= new Timestamp(currentTimeMillis);
    }
}
