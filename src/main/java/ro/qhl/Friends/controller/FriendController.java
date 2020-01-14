package ro.qhl.Friends.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.qhl.Friends.model.Friend;
import ro.qhl.Friends.service.FriendService;

@RestController
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	@PostMapping("/friend")
	Friend createFriend(@RequestBody Friend friend) {
		return friendService.save(friend);
	}
	
	@GetMapping("/friend")
	Iterable<Friend> readFriendList(){
		return friendService.findAll();
	}
	
	@PutMapping("/friend")
	Friend updateFriend(@RequestBody Friend friend) {
		return friendService.save(friend);
	}
	
	@DeleteMapping("/friend/{friendId}")
	void deleteFriendById(@PathVariable Integer friendId) {
		friendService.deleteById(friendId);
	}
	
	@GetMapping("/friend/{friendId}")
	Optional<Friend> findById(@PathVariable Integer friendId){
		return friendService.findById(friendId);
	}
	
	@GetMapping("/friend/search")
	Iterable<Friend> findByFirstNameAndLastName(@RequestParam(value = "firstName", required = false) String firstName,
												@RequestParam(value = "lastName", required = false) String lastName)
	{
		if (null != firstName && null != lastName) {
			return friendService.findByFirstNameAndLastName(firstName, lastName); 
		}
		if (null != firstName) {
			return friendService.findByFirstName(firstName);
		}
		if (null != lastName) {
			return friendService.findByLastName(lastName);
		}
		return friendService.findAll();
	}
	
}
