package com.group.laxyapp.controller.user;


import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.dto.user.UserDto;
import com.group.laxyapp.dto.user.request.UserLoginRequest;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public String joinPage(Model model) {
        model.addAttribute("userRegistRequest", new UserRegistRequest());
        return "user";
    }

    @PostMapping("/user")
    public String user(@ModelAttribute UserRegistRequest req, BindingResult bindingResult, Model model) {
        bindingResult = userService.joinValid(req, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user";
        }

        userService.join(req);
        model.addAttribute("message", "회원가입에 성공했습니다!\n로그인 후 사용 가능합니다!");
        model.addAttribute("nextUrl", "/user");
        return "printMessage";
    }

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {

        // 로그인 성공 시 이전 페이지로 redirect 되게 하기 위해 세션에 저장
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/login") && !uri.contains("/user")) {
            request.getSession().setAttribute("prevPage", uri);
        }

        model.addAttribute("userLoginRequest", new UserLoginRequest());
        return "user/login";
    }

    @GetMapping("/mypage")
    public String userEditPage(Authentication auth, Model model) {
        User user = userService.myInfo(auth.getName());
        model.addAttribute("userDto", UserDto.of(user));
        return "mypage";
    }

    @PostMapping("/mypage")
    public String userEdit(@ModelAttribute UserDto dto, BindingResult bindingResult, Authentication auth, Model model) {
        bindingResult = userService.editValid(dto, bindingResult, auth.getName());

        if (bindingResult.hasErrors()) {
            return "mypage";
        }

        userService.edit(dto, auth.getName());
        model.addAttribute("message", "정보가 수정되었습니다.");
        model.addAttribute("nextUrl", "/mypage/board");
        return "printMessage";
    }

    @GetMapping("/delete")
    public String userDeletePage(Authentication auth, Model model) {
        User user = userService.myInfo(auth.getName());
        model.addAttribute("userDto", UserDto.of(user));
        return "user/delete";
    }

    @PostMapping("/delete")
    public String userDelete(@ModelAttribute UserDto dto, Authentication auth, Model model) {
        Boolean deleteSuccess = userService.delete(auth.getName(), dto.getNowPassword());
        if (deleteSuccess) {
            model.addAttribute("message", "탈퇴 되었습니다.");
            model.addAttribute("nextUrl", "/users/logout");
            return "printMessage";
        } else {
            model.addAttribute("message", "현재 비밀번호가 틀려 탈퇴에 실패하였습니다.");
            model.addAttribute("nextUrl", "/user/delete");
            return "printMessage";
        }
    }

    @GetMapping("/admin")
    public String adminPage(@RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "") String keyword,
                            Model model) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        Page<User> users = userService.findAllByNickname(keyword, pageRequest);

        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "user/admin";
    }

    @GetMapping("/admin/{userId}")
    public String adminChangeRole(@PathVariable Long userId,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "") String keyword,
                                  Authentication auth) throws UnsupportedEncodingException {
        userService.changeRole(userId, auth);
        return "redirect:/user/admin?page=" + page + "&keyword=" + URLEncoder.encode(keyword, "UTF-8");
    }
}