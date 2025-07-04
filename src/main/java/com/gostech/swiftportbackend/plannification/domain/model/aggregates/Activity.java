package com.gostech.swiftportbackend.plannification.domain.model.aggregates;

import com.gostech.swiftportbackend.plannification.domain.model.commands.CreateActivityCommand;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActicityCode;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActivityStatus;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Activity extends AuditableAbstractAggregateRoot<Activity> {

    @Embedded
    private ActicityCode activityCode;

    private String description;

    @Column(name = "expected_time")
    private LocalDateTime expectedTime;

    private Integer weekNumber;

    private ActivityStatus activityStatus;

    @Embedded
    private TenantId tenantId;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id_origin")
    private Location locationOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id_destination")
    private Location locationDestination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id_origin")
    private Zone zoneOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id_destination")
    private Zone zoneDestination;

    public Activity() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        task.setActivity(this);
        tasks.add(task);
    }

    public Activity(Long tenantId, CreateActivityCommand command) {
        this.tasks = new ArrayList<>();
        this.activityCode = new ActicityCode(command.activityCode());
        this.description = command.description();
        this.expectedTime = command.expectedTime();
        this.weekNumber = command.weekNumber();
        switch (command.activityStatus()) {
            case "Planned":
                this.activityStatus = ActivityStatus.PLANNED;
                break;
            case "Completed":
                this.activityStatus = ActivityStatus.COMPLETED;
                break;
            case "InProgress":
                this.activityStatus = ActivityStatus.IN_PROGRESS;
                break;
            default:
                this.activityStatus = ActivityStatus.CANCELLED;
                break;
        }
        this.tenantId = new TenantId(tenantId);
    }

    public void updateActivityStatus(String activityStatus) {
        switch (activityStatus) {
            case "Planned":
                this.activityStatus = ActivityStatus.PLANNED;
                break;
            case "Completed":
                this.activityStatus = ActivityStatus.COMPLETED;
                break;
            case "InProgress":
                this.activityStatus = ActivityStatus.IN_PROGRESS;
                break;
            case "Cancelled":
                this.activityStatus = ActivityStatus.CANCELLED;
                break;
            default:
                break;
        }
    }

}
