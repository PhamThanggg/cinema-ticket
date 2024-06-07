package com.example.cinematicket.service;

import com.example.cinematicket.dto.request.UserCreationRequest;
import com.example.cinematicket.dto.request.UserUpdateRequest;
import com.example.cinematicket.dto.response.UserResponse;
import com.example.cinematicket.entity.User;
import com.example.cinematicket.exception.AppException;
import com.example.cinematicket.exception.ErrorCode;
import com.example.cinematicket.mapper.UserMapper;
import com.example.cinematicket.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if(!request.getPassword().equals(request.getRepassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_SAME);

        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public UserResponse getMyInfo() {

        return null;
    }

    @Override
    public Page<UserResponse> getAllUsers(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
        return userRepository.findAll(pageRequest).map(userMapper::toUserResponse);
    }

    @Override
    public Page<UserResponse> searchUsers(String search, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<UserResponse> pageUser;
        if(search == null){
            pageUser = userRepository.findAll(pageRequest).map(userMapper::toUserResponse);
        }else{
            pageUser = userRepository.findByFullNameContaining(search, pageRequest).map(userMapper::toUserResponse);
        }
        return pageUser;
    }

    @Override
    public Long getCountUsers() {
        return userRepository.count();
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
