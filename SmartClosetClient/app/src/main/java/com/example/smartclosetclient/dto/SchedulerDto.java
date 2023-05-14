package com.example.smartclosetclient.dto;

import java.util.Date;

public class SchedulerDto {

    private Date selectedDate;
    private Long outfitId;
    private Long closetId;

    public SchedulerDto() {
        super();
    }
    public SchedulerDto(Date selectedDate, Long outfitId, Long closetId) {
        super();
        this.selectedDate = selectedDate;
        this.outfitId = outfitId;
        this.closetId = closetId;
    }
    public Date getSelectedDate() {
        return selectedDate;
    }
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
    public Long getOutfitId() {
        return outfitId;
    }
    public void setOutfitId(Long outfitId) {
        this.outfitId = outfitId;
    }
    public Long getClosetId() {
        return closetId;
    }
    public void setClosetId(Long closetId) {
        this.closetId = closetId;
    }

}
