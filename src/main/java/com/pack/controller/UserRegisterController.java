package com.pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.dto.UserRegisterDto;
import com.pack.serviceimpl.UserRegisterServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/v1")
@Api(value = "Controller for user")
public class UserRegisterController {
	
	
	@Autowired
	UserRegisterServiceImpl service;
	
	@ApiOperation(value = " Get user data by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/{id}")
	public UserRegisterDto getByIdUser(@PathVariable("id") Integer userId) {
		return service.getuserByid(userId);

	}
	@ApiOperation(value = " Get All user data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping()
	public ResponseEntity<List<UserRegisterDto>> getAllContract() {
		List<UserRegisterDto> list = service.getAllUser();

		return new ResponseEntity<List<UserRegisterDto>>(list, HttpStatus.OK);

	}
	
	@ApiOperation(value = " Create new User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping()
	public ResponseEntity<UserRegisterDto> createUser(@RequestBody UserRegisterDto userRegisterDto) {
		UserRegisterDto userlead = service.addNewUser(userRegisterDto);
		return new ResponseEntity<UserRegisterDto>(userlead, HttpStatus.OK);

	}
	@ApiOperation(value = " Update ledas data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping("/{id}")
	public ResponseEntity<UserRegisterDto> updateContract(@RequestBody UserRegisterDto userRegisterDto) {

		service.updateUser(userRegisterDto);
		return new ResponseEntity<UserRegisterDto>(userRegisterDto, HttpStatus.OK);

	}

	@ApiOperation(value = "  Delete user data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	@DeleteMapping("/{userId}")
	public void deleteContract(@ApiParam(value = "id", example = "") @PathVariable Integer userId) {
		service.deleteUser(userId);
	}

	
}
