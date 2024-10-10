package com.example.cinematicket.services.user;

import com.example.cinematicket.constant.PredefinedRole;
import com.example.cinematicket.dtos.requests.UserCreationRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.UserResponse;
import com.example.cinematicket.entities.Role;
import com.example.cinematicket.entities.User;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.UserMapper;
import com.example.cinematicket.repositories.RoleRepository;
import com.example.cinematicket.repositories.UserRepository;
import com.example.cinematicket.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if(!request.getPassword().equals(request.getRepassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_SAME);

        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findByName(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PostAuthorize("returnObject.id.toString() == authentication.principal.getClaimAsString('id') or hasRole('ADMIN')")
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

       User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ACCOUNT')")
    public Page<UserResponse> getAllUsers(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
        return userRepository.findAll(pageRequest).map(userMapper::toUserResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ACCOUNT')")
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
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ACCOUNT')")
    public Long getCountUsers() {
        return userRepository.count();
    }

    @Override
    @PostAuthorize("returnObject.id.toString() == authentication.principal.getClaimAsString('id') or hasRole('ADMIN')")
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        userMapper.updateUser(user, request);

        var roles = roleRepository.findAllById(request.getRoleId());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateRole(Long id, Set<Long> roleIds) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );

        Set<Role> listRole = roleRepository.findByIdIn(roleIds);
        Set<Long> foundIds = listRole.stream().map(Role::getId).collect(Collectors.toSet());
        List<Long> missingGenreId = roleIds.stream()
                .filter(ids -> !foundIds.contains(ids)).toList();
        if(!missingGenreId.isEmpty()){
            String errorMessage = missingGenreId.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            throw new RuntimeException("Id not exists " + errorMessage);
        }

        user.setRoles(listRole);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ACCOUNT')")
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
