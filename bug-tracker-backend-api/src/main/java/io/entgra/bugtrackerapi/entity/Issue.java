package io.entgra.bugtrackerapi.entity;

import io.entgra.bugtrackerapi.service.UserService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Open' CHECK (status IN ('Open', 'Waiting on client', 'In Progress', 'Resolved'))")
    private String status = "Open";  // Set the default value to "Open"

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Issue(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.status= "Open";

        //This is just to Bypass the authorization stage & to demonstrate that if the
        // authorization has been completed author of an issue will be recorded as the user.
        this.user= new User(1L,"Kalindu","Navanjana");
    }
}
