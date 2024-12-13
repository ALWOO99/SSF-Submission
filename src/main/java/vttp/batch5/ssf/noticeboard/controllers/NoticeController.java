package vttp.batch5.ssf.noticeboard.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp.batch5.ssf.noticeboard.models.Notice;

// Use this class to write your request handlers

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final List<Notice> list = new ArrayList<>();

    @GetMapping({"", "/"})
    public String showForm(Model model) {
        model.addAttribute("notice", new Notice()); // Form binding
        model.addAttribute("notices", list); // Display existing notices
        return "notice"; // Thymeleaf template name
    }

    @PostMapping({"", "/"})
    public String saveNotice(@Valid @ModelAttribute("notice") Notice info, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("notices", list); // Include notices on validation error
            return "notice";
        }

        
        list.add(info); // Add notice to list
        return "redirect:/notice/"; // Redirect to form to display updated list
    }
}

    

