package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Group {

    @Id
    private String groupId;
    private String groupOwner;
    private String groupCity;
    private String groupState;
    private String groupName;
    private String groupDescription;
    private GroupType groupType;
    private GroupPrivacy groupPrivacy;
    private List<String> groupMembers;

    public Group() {
        this.groupMembers = new ArrayList<>();
    }

}
