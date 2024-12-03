package com.auca.onlineQuizApp.controller;
//
//import com.auca.onlineQuizApp.model.Role;
//import com.auca.onlineQuizApp.model.User;
//import com.auca.onlineQuizApp.service.UserService;
//import com.fasterxml.jackson.databind.DatabindContext;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//
//
//import java.util.List;
//
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/home")
//    public String home() {
//        return "index"; //home page
//    }
//
//    @GetMapping("/register")
//    public String showRegisterForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register"; // Template name for registration
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "register"; // If form validation fails, return the registration page
//        }
//
//        // Prevent user from registering as admin
//        if (user.getRole() == Role.ROLE_ADMIN) {
//            model.addAttribute("error", "You are not authorized to register as an admin.");
//            return "register";
//        }
//
//        // Register user
//        userService.registerUser(user);
//        model.addAttribute("message", "Registration successful! You can log in now.");
//        return "login"; // Redirect to login page
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//        // Add an error message if it exists
//        model.addAttribute("error", model.getAttribute("error"));
//        return "login"; // Template name for login
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username,@RequestParam String password, HttpSession session, Model model) {
//        User user = userService.loginUser(username);
//
//        if (user == null || !user.getPassword().equals(password)) {
//            model.addAttribute("error", "Invalid username or password");
//            return "login"; // Show login page again with error message
//        }
//
//        // Set user information in session
//        session.setAttribute("loggedInUser", user); //Ensure no space in the attribute name
//
//        // Redirect based on the role
//        if (user.getRole() == Role.ROLE_ADMIN) {
//            return "redirect:/admin"; // Admin page
//        } else if (user.getRole() == Role.ROLE_TEACHER) {
//            return "redirect:/teacher"; // Teacher page
//        } else if (user.getRole() == Role.ROLE_USER) {
//            return "redirect:/student"; // Student page
//        }
//
//        // Default redirect if no role matches (optional safety fallback)
//        return "redirect:/login";
//    }
//
////    @GetMapping("/admin")
////    public String showAdminDashboard(HttpSession session,Model model) {
////
////        User user = (User ) session.getAttribute("loggedInUser");
////        if (user == null || user.getRole() != Role.ROLE_ADMIN) {
////            return "redirect:/login"; // Redirect to log in if not logged in or not admin
////        }
////
////
////        List<User> users = userService.getNonAdminUsers(); // Fetch all users
////        model.addAttribute("users", users); // Add users to the model
////        return "admin"; // Return the name of your admin dashboard template
////    }
//
//
//
//    @GetMapping("/admin")
//    public String showAdminDashboard(HttpSession session, Model model,
//                                     @RequestParam(defaultValue = "0") int pageNo,
//                                     @RequestParam(defaultValue = "10") int pageSize,
//                                     @RequestParam(defaultValue = "id") String sortBy) {
//        User user = (User ) session.getAttribute("loggedInUser ");
//        if (user == null || user.getRole() != Role.ROLE_ADMIN) {
//            return "redirect:/login"; // Redirect to log in if not logged in or not admin
//        }
//
//        // Create a Pageable object for pagination and sorting
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Page<User> userPage = userService.getAllUsers(pageable); // Fetch paginated users
//
//        model.addAttribute("users", userPage.getContent()); // Add the users list to the model
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", userPage.getTotalPages());
//        model.addAttribute("totalUsers", userPage.getTotalElements());
//        model.addAttribute("sortBy", sortBy); // Add sortBy to the model for UI
//
//        return "admin"; // Return the admin dashboard template
//    }
//
//    @GetMapping("/teacher")
//    public String teacherPage(HttpSession session) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null || user.getRole() != Role.ROLE_TEACHER) {
//            return "redirect:/login"; // Redirect to log in if not logged in or not teacher
//        }
//        return "teacher"; // teacher dashboard template
//    }
//
//    @GetMapping("/student")
//    public String studentPage(HttpSession session) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null || user.getRole() != Role.ROLE_USER) {
//            return "redirect:/login"; // Redirect to log in if not logged in or not student
//        }
//        return "student"; // Customer dashboard template
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
//        // Log the logout action
//        System.out.println("Logging out user: " + session.getAttribute("loggedInUser "));
//
//        session.invalidate(); // Invalidate session on logout
//
//        // Log after invalidation
//        System.out.println("Session invalidated.");
//
//        // Add a flash attribute for the logout message
//        redirectAttributes.addFlashAttribute("message", "You are logged out.");
//
//        return "redirect:/login"; // Redirect to login after logout
//    }
//
//
//}


import com.auca.onlineQuizApp.model.Role;
import com.auca.onlineQuizApp.model.User;
import com.auca.onlineQuizApp.service.AuditLogService;
import com.auca.onlineQuizApp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.registerUser(user);
        model.addAttribute("message", "Registration successful! You can log in now.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", model.getAttribute("error"));
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        // Set default admin credentials
        if ("admin".equals(username) && "admin123".equals(password)) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("admin123");
            adminUser.setRole(Role.ROLE_ADMIN);
            session.setAttribute("loggedInUser", adminUser);
            return "redirect:/admin";
        }

        User user = userService.loginUser(username);

        if (user == null || !password.equals(user.getPassword())) {
            model.addAttribute("error", "Invalid username or password");
            auditLogService.logAction("LOGIN_FAILED", username, "Failed login attempt");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        auditLogService.logAction("LOGIN_SUCCESS", user.getUsername(), "User logged in successfully");

        if (user.getRole() == Role.ROLE_TEACHER) {
            return "redirect:/teacher";
        } else if (user.getRole() == Role.ROLE_USER) {
            return "redirect:/student";
        }

        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String showAdminDashboard(HttpSession session, Model model,
                                     @RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != Role.ROLE_ADMIN) {
            return "redirect:/login";
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> userPage = userService.getAllUsers(pageable);

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalUsers", userPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);

        return "admin";
    }

    @GetMapping("/teacher")
    public String teacherPage(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != Role.ROLE_TEACHER) {
            return "redirect:/login";
        }
        return "teacher";
    }

    @GetMapping("/student")
    public String studentPage(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != Role.ROLE_USER) {
            return "redirect:/login";
        }
        return "student";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            auditLogService.logAction("LOGOUT", user.getUsername(), "User logged out");
        }
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "You are logged out.");
        return "redirect:/login";
    }

    @GetMapping("/e-showroom")
    public String showEshowroomPage() {
        return "e-showroom";
    }
}

