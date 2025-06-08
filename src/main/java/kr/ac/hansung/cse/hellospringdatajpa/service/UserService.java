package kr.ac.hansung.cse.hellospringdatajpa.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.hellospringdatajpa.dto.UserDto;
import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.repo.RoleRepository;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입 처리
     *  - 이메일 중복 검사
     *  - 비밀번호 암호화
     *  - ROLE_USER 기본 할당
     */
    public User registerNewUser(UserDto dto) {
        if (!dto.isPasswordConfirmed()) {
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new IllegalStateException("이미 가입된 이메일입니다."); });

        // 엔티티로 변환
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 기본 ROLE_USER 할당
        Role userRole = roleRepository
                .findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER가 정의되어 있지 않습니다."));
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }
}
