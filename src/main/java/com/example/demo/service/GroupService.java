package com.example.demo.service;


import com.example.demo.exceptions.GroupNotFoundException;
import com.example.demo.model.Group;
import com.example.demo.model.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public String createGroup(Group group) {
        groupRepository.insert(group);
        Optional<Group> createdGroup = groupRepository.findByGroupName(group.getGroupName());
        Optional<User> groupOwner = userRepository.findById(group.getGroupOwner());

        createdGroup.ifPresent(g -> {
            groupOwner.ifPresent(o -> {
                o.getUserGroups().add(g.getGroupId());
                userRepository.save(o);
            });
        });
        return "[" + group.getGroupName() + "] created.";
    }

    public Group viewGroup(String groupId) throws GroupNotFoundException {
        Optional<Group> group = groupRepository.findById(groupId);
        return group.orElseThrow(() -> new GroupNotFoundException("No group found for: " + groupId));
    }

    //public String updateGroup() {}
    public String joinGroup(String userId, String groupId) {
       groupRepository.findById(groupId).ifPresent(g -> {
           g.getGroupMembers().add(userId);
           groupRepository.save(g);
       });
       userRepository.findById(userId).ifPresent(u -> {
           u.getUserGroups().add(groupId);
           userRepository.save(u);
       });
       return "[" + userId + "] joined [" + groupId + "].";

    }

    public String leaveGroup(String userId, String groupId) {
        groupRepository.findById(groupId).ifPresent(g -> {
            g.getGroupMembers().remove(userId);
            groupRepository.save(g);
        });
        userRepository.findById(userId).ifPresent(u -> {
            u.getUserGroups().remove(groupId);
            userRepository.save(u);
        });
        return "[" + userId + "] left [" + groupId + "].";
    }

    public String deleteGroup(String groupId) {
        groupRepository.deleteById(groupId);
        return "[" + groupId + "] deleted.";
    }

    public String deleteRepository() {
        groupRepository.deleteAll();
        return "Clearing group repository...";
    }

    public List<Group> getPublicGroups(User user) {
        return groupRepository.findPublicGroups(user.getUserCity(), user.getUserState());
    }

    //public String getPrivateGroups() {}
    //public String getPopularGroups() {}



}
