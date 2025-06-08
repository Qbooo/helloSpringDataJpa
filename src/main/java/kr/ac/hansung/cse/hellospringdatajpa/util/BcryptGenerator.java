package kr.ac.hansung.cse.hellospringdatajpa.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 원하는 평문 비밀번호를 여기에 입력
        System.out.println(encoder.encode("admin123"));
    }
}

