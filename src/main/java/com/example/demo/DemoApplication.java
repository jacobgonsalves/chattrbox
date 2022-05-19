package com.example.demo;


import com.example.demo.model.Group;
import com.example.demo.model.GroupPrivacy;
import com.example.demo.model.GroupType;
import com.example.demo.model.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(userService.deleteRepository());
		System.out.println(groupService.deleteRepository());
		System.out.println();

		createUsers();

		// users
		User user1 = userService.findUser("user1@gmail.com");
		User user2 = userService.findUser("user2@gmail.com");
		User user3 = userService.findUser("user3@gmail.com");
		User user4 = userService.findUser("user4@gmail.com");
		User user5 = userService.findUser("user5@gmail.com");

		createGroups(user1);

		// groups
		List<Group> groups = groupService.getPublicGroups(user2);
		System.out.println(groupService.joinGroup(user2.getUserId(), groups.get(0).getGroupId()));
		System.out.println(groupService.joinGroup(user2.getUserId(), groups.get(3).getGroupId()));

	}

	public void createUsers(){

		User user1 = new User();
		user1.setUserEmail("user1@gmail.com");
		user1.setUserPassword("password");
		user1.setUserFirstName("John");
		user1.setUserLastName("Doe");
		user1.setUserCity("Raleigh");
		user1.setUserState("NC");
		user1.setUserPhoto("photo_url");

		User user2 = new User();
		user2.setUserEmail("user2@gmail.com");
		user2.setUserPassword("password");
		user2.setUserFirstName("John");
		user2.setUserLastName("Doe");
		user2.setUserCity("Raleigh");
		user2.setUserState("NC");
		user2.setUserPhoto("photo_url");

		User user3 = new User();
		user3.setUserEmail("user3@gmail.com");
		user3.setUserPassword("password");
		user3.setUserFirstName("John");
		user3.setUserLastName("Doe");
		user3.setUserCity("Raleigh");
		user3.setUserState("NC");
		user3.setUserPhoto("photo_url");

		User user4 = new User();
		user4.setUserEmail("user4@gmail.com");
		user4.setUserPassword("password");
		user4.setUserFirstName("John");
		user4.setUserLastName("Doe");
		user4.setUserCity("Raleigh");
		user4.setUserState("NC");
		user4.setUserPhoto("photo_url");

		User user5 = new User();
		user5.setUserEmail("user5@gmail.com");
		user5.setUserPassword("password");
		user5.setUserFirstName("John");
		user5.setUserLastName("Doe");
		user5.setUserCity("Raleigh");
		user5.setUserState("NC");
		user5.setUserPhoto("photo_url");

		// user services
		System.out.println(userService.createUser(user1));
		System.out.println(userService.createUser(user2));
		System.out.println(userService.createUser(user3));
		System.out.println(userService.createUser(user4));
		System.out.println(userService.createUser(user5));
	}

	public void createGroups(User user) {

		Group group1 = new Group();
		group1.setGroupOwner(user.getUserId());
		group1.setGroupCity(user.getUserCity());
		group1.setGroupState(user.getUserState());
		group1.setGroupName("Group 1");
		group1.setGroupDescription("Group 1 Description");
		group1.setGroupType(GroupType.GENERAL);
		group1.setGroupPrivacy(GroupPrivacy.PUBLIC);
		group1.getGroupMembers().add(user.getUserId());

		Group group2 = new Group();
		group2.setGroupOwner(user.getUserId());
		group2.setGroupCity(user.getUserCity());
		group2.setGroupState(user.getUserState());
		group2.setGroupName("Group 2");
		group2.setGroupDescription("Group 2 Description");
		group2.setGroupType(GroupType.ATHLETIC);
		group2.setGroupPrivacy(GroupPrivacy.PUBLIC);
		group2.getGroupMembers().add(user.getUserId());

		Group group3 = new Group();
		group3.setGroupOwner(user.getUserId());
		group3.setGroupCity(user.getUserCity());
		group3.setGroupState(user.getUserState());
		group3.setGroupName("Group 3");
		group3.setGroupDescription("Group 3 Description");
		group3.setGroupType(GroupType.GENERAL);
		group3.setGroupPrivacy(GroupPrivacy.RESTRICTED);
		group3.getGroupMembers().add(user.getUserId());

		Group group4 = new Group();
		group4.setGroupOwner(user.getUserId());
		group4.setGroupCity(user.getUserCity());
		group4.setGroupState(user.getUserState());
		group4.setGroupName("Group 4");
		group4.setGroupDescription("Group 4 Description");
		group4.setGroupType(GroupType.DATING);
		group4.setGroupPrivacy(GroupPrivacy.PUBLIC);
		group4.getGroupMembers().add(user.getUserId());

		Group group5 = new Group();
		group5.setGroupOwner(user.getUserId());
		group5.setGroupCity(user.getUserCity());
		group5.setGroupState(user.getUserState());
		group5.setGroupName("Group 5");
		group5.setGroupDescription("Group 5 Description");
		group5.setGroupType(GroupType.CLUBBING);
		group5.setGroupPrivacy(GroupPrivacy.PRIVATE);
		group5.getGroupMembers().add(user.getUserId());

		System.out.println(groupService.createGroup(group1));
		System.out.println(groupService.createGroup(group2));
		System.out.println(groupService.createGroup(group3));
		System.out.println(groupService.createGroup(group4));
		System.out.println(groupService.createGroup(group5));

	}
}
