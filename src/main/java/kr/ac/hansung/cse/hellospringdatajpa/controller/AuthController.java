package kr.ac.hansung.cse.hellospringdatajpa.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import kr.ac.hansung.cse.hellospringdatajpa.dto.UserDto;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.service.UserService;

@Controller
@RequestMapping
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signupSubmit(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            BindingResult bindingResult,
            Model model) {

        if (!userDto.isPasswordConfirmed()) {
            bindingResult.rejectValue("confirmPassword", "error.userDto", "비밀번호가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            User saved = userService.registerNewUser(userDto);
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup";
        }

        // 가입 후 로그인 페이지로 리다이렉트
        return "redirect:/login?registered";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm(@RequestParam(value="error", required=false) String error,
                            @RequestParam(value="logout", required=false) String logout,
                            @RequestParam(value="registered", required=false) String registered,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        if (logout != null) {
            model.addAttribute("infoMessage", "로그아웃되었습니다.");
        }
        if (registered != null) {
            model.addAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요.");
        }
        return "login";
    }
}
