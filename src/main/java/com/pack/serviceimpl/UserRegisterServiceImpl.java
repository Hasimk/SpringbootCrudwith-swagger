package com.pack.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pack.dto.UserRegisterDto;
import com.pack.entity.UserRegisterEntity;
import com.pack.exception.ResourceNotFoundException;
import com.pack.repository.UserRegisterRepository;
import com.pack.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired

	UserRegisterRepository userRepository;

	@Override
	public UserRegisterDto getuserByid(Integer userId) {
		UserRegisterDto userdto = null;
		if (userId != null && userId > 0) {
			UserRegisterEntity userlist = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException(" data not found"));

			userdto = convertEntityToModel(userlist);
		} else {
			throw new ResourceNotFoundException("No data Found");
		}

		return userdto;
	}

	@Override
	public List<UserRegisterDto> getAllUser() {

		List<UserRegisterEntity> userEntityList = userRepository.findAll();

		if (userEntityList != null && !userEntityList.isEmpty()) {
			return userEntityList.stream().map(UserRegisterServiceImpl::convertEntityToModel)
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public UserRegisterDto addNewUser(UserRegisterDto userRegisterDto) {
		if (userRegisterDto != null) {
			UserRegisterEntity userEntity = convertModelToEntity(userRegisterDto);
			userRepository.save(userEntity);
			userRegisterDto.setId(userEntity.getId());
		} else {
			throw new ResourceNotFoundException("No  Data Found");
		}
		return userRegisterDto;
	}

	@Override
	public void updateUser(UserRegisterDto userRegisterDto) {
		if (userRegisterDto != null) {
			UserRegisterEntity userEntity = convertModelToEntity(userRegisterDto);
			userRepository.save(userEntity);
		} else {
			throw new ResourceNotFoundException("Unable to Update Data");
		}

	}

	@Override
	public void deleteUser(Integer userId) {
		if (userId != null && userId > 0) {
			UserRegisterEntity userlead = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("contract id Not found"));
			if (userlead != null && userlead.getId() != null) {
				userRepository.delete(userlead);
			}
		} else {
			throw new ResourceNotFoundException("Unable to Delete Data");
		}

	}
	
	  public List<UserRegisterEntity> getAllpagenation(Integer pageNo, Integer pageSize, String sortBy)
	    {
	        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	 
	        Page<UserRegisterEntity> pagedResult = userRepository.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<UserRegisterEntity>();
	        }
	    }

	private static UserRegisterDto convertEntityToModel(UserRegisterEntity userlist) {
		UserRegisterDto userdto = new UserRegisterDto();
		userdto.setId(userlist.getId());
		userdto.setFirstName(userlist.getFirstName());
		userdto.setLastName(userlist.getLastName());
		userdto.setEmail(userlist.getEmail());
		userdto.setPassword(userlist.getEmail());
		userdto.setPhoneNumber(userlist.getPhoneNumber());

		return userdto;
	}

	private UserRegisterEntity convertModelToEntity(UserRegisterDto userRegisterDto) {
		UserRegisterEntity userEntity = new UserRegisterEntity();
		userEntity.setEmail(userRegisterDto.getEmail());
		userEntity.setFirstName(userRegisterDto.getFirstName());
		userEntity.setId(userRegisterDto.getId());
		userEntity.setLastName(userRegisterDto.getLastName());
		userEntity.setPassword(userRegisterDto.getPassword());
		userEntity.setPhoneNumber(userRegisterDto.getPhoneNumber());

		return userEntity;
	}

}
