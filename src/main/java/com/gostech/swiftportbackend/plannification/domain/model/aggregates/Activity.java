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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "location_id_origin", referencedColumnName = "id", insertable = false, updatable = false)
    private Location locationOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id_destination", referencedColumnName = "id", insertable = false, updatable = false)
    private Location locationDestination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id_origin", referencedColumnName = "id", insertable = false, updatable = false)
    private Zone zoneOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id_destination", referencedColumnName = "id", insertable = false, updatable = false)
    private Zone zoneDestination;

    public Activity() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        task.setActivity(this);
        tasks.add(task);
    }

    public void setActivityCode(ActicityCode activityCode) {
        this.activityCode = activityCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpectedTime(LocalDateTime expectedTime) {
        this.expectedTime = expectedTime;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public void setLocationOrigin(Location locationOrigin) {
        this.locationOrigin = locationOrigin;
    }

    public void setLocationDestination(Location locationDestination) {
        this.locationDestination = locationDestination;
    }

    public void setZoneOrigin(Zone zoneOrigin) {
        this.zoneOrigin = zoneOrigin;
    }

    public void setZoneDestination(Zone zoneDestination) {
        this.zoneDestination = zoneDestination;
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
