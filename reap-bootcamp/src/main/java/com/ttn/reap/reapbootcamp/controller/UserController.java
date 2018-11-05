package com.ttn.reap.reapbootcamp.controller;


import com.ttn.reap.reapbootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ttn.reap.reapbootcamp.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String show(User user){
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
            modelAndView.addObject("msg","User has been registered successfully");
            modelAndView.addObject("user",new User());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView fetchUserFromDatabase(User user) {
        ModelAndView modelAndView = new ModelAndView();
        User userexistsemail = userService.findUserByEmail(user.getEmail());
        if (userexistsemail != null) {
            String userexistPassword = userexistsemail.getPassword();
            if (!userexistPassword.isEmpty() && userexistPassword.equals(user.getPassword())) {
                System.out.println("#### " + user.getEmail());
                System.out.println("$$$$ " + userexistsemail.getPassword() + " %%%%%% " + user.getPassword());
                modelAndView.setViewName("loginUser");
            }
        }

        return modelAndView;
    }


    /*@RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ModelAndView userLogin(User user , @RequestParam(required = true) String email ,@RequestParam(required = true) String password)
    {

        ModelAndView modelAndView=new ModelAndView();
        System.out.println("hit");
        System.out.println(email);
        System.out.println(password);
        modelAndView.setViewName("loginUser");
        return modelAndView;
    }*/


    @RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
    public String recoverPass(@RequestParam("email") String email) {
        return "home";
    }


    }



