package com.ttn.reap.reapbootcamp.controller;


import com.ttn.reap.reapbootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ttn.reap.reapbootcamp.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String show(User user){
        //return "dashboard";
        return "home";
    }

    @GetMapping("/home")
    public String returnHome()
    {
        return "home";
    }

    /*@RequestMapping( value = "/form", method =  RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    public String addSignUp(User user){
        System.out.println("Sign up successful");
        System.out.println(user);
        return "home";
    }*/

    @RequestMapping(value = {"/userSignUp"}, method = RequestMethod.GET)
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/home/signup");
        return modelAndView;
    }

    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    public ModelAndView userSignUp(@Valid User user, BindingResult bindingResult)
    {
        ModelAndView modelAndView=new ModelAndView();
        User userExists=userService.findUserByEmail(user.getEmail());

        if(userExists!=null)
        {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors())
        {
            modelAndView.setViewName("home");
        }
        else {
            System.out.println(user);
            userService.saveOnSignUp(user);
            System.out.println("Registered");
            modelAndView.addObject("msg1","User has been registered successfully");
            modelAndView.addObject("user",new User());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView fetchUserFromDatabase(@ModelAttribute("user") User user, HttpSession session) {
        System.out.println("hii");
        ModelAndView modelAndView = new ModelAndView();
        User userexistsemail = userService.findUserByEmail(user.getEmail());
        if (userexistsemail != null) {
            String userexistPassword = userexistsemail.getPassword();
            if (!userexistPassword.isEmpty() && userexistPassword.equals(user.getPassword())) {
                System.out.println("#### " + user.getEmail());
                System.out.println("$$$$ " + userexistsemail.getPassword() + " %%%%%% " + user.getPassword());
                session.setAttribute("user",user);
                modelAndView.setViewName("dashboard");
            }
            else{
                modelAndView.addObject("msg","Incorrect Password");
                modelAndView.setViewName("home");
            }
        }else{
            modelAndView.addObject("msg","Email id doesn't Exist. Please register before login");
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session ) {
        ModelAndView modelAndView=new ModelAndView();
        session.invalidate();
        System.out.println("logout invoked");
        modelAndView.setViewName("logoutUser");
        return modelAndView;
    }

   /* @PostMapping("/login")
    public String loginUser(User user,@RequestParam("email") String email,@RequestParam("password") String password)
    {

        System.out.println(email);
        System.out.println(password);
        User userEmailExist=userService.findUserByEmail(user.getEmail());
        if (userEmailExist != null) {
            String userexistPassword = userEmailExist.getPassword();
            if (!userexistPassword.isEmpty() && userexistPassword.equals(user.getPassword())) {
                System.out.println("#### " + user.getEmail());
                System.out.println("$$$$ " + userEmailExist.getPassword() + " %%%%%% " + user.getPassword());
                return "loginUser";
            }
            else
                {
                addObject("msg","Incorrect Password");
                return "home";
            }
        }else{
            modelAndView.addObject("msg","Email id doesn't Exist. Please register before login");
            return "home";
        }
        return "loginUser";
    }*/




    @RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
    public String recoverPass(@RequestParam("email") String email) {
        return "home_old";
    }


    }



