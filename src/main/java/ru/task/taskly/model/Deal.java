package ru.task.taskly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.task.taskly.model.dto.DealCalendarDto;

import java.time.Instant;

@Table(name = "taskly_deal")
@Entity
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Integer id;
    @Column(name = "deal_name", nullable = false)
    private String name;
    @Column(name = "deal_description")
    private String description;
    @Column(name = "deal_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(name = "deal_date_start", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant dateStart;
    @Column(name = "deal_date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant dateEnd;

    public Deal() {

    }
    public Deal(String name, String description, Status status, Instant dateStart, Instant dateEnd, User user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.user = user;
    }

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
