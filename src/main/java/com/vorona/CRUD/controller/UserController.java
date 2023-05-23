package com.vorona.CRUD.controller;

import com.vorona.CRUD.Service.UserService;
import com.vorona.CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("")
    public String returnUser (Model model) {
        model.addAttribute("user",userService.getUser(/*Integer.parseInt(count)*/));
        return "user";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //        System.out.println(userService.show(id));
        model.addAttribute("people",userService.show(id));
        return "show";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping
    public String create(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.save(user);
        return "redirect:/user";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id")int id) {
        if(bindingResult.hasErrors())
            return "edit";
        userService.update(id,user);
        return  "redirect:/user";
    }
 //   @DeleteMapping("/{id}")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id) {
        userService.delete(id);
        return  "redirect:/user";
    }
}


