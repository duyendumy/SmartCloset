package com.example.smartclosetclient.dto;

import java.util.List;

public class OutfitDto {
    private Long closetId;

    private int display;

    List<Long> itemIdList;



    public OutfitDto(Long closetId, int display, List<Long> itemIdList) {
        super();
        this.closetId = closetId;
        this.display = display;
        this.itemIdList = itemIdList;
    }



    public List<Long> getItemIdList() {
        return itemIdList;
    }



    public void setItemIdList(List<Long> itemIdList) {
        this.itemIdList = itemIdList;
    }



    public OutfitDto() {
        super();
    }



    public Long getClosetId() {
        return closetId;
    }

    public void setClosetId(Long closetId) {
        this.closetId = closetId;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }


}
