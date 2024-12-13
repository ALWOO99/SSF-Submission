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

    /*
     * @PostMapping("/notice")
     * 
     * @ResponseBody
     * public String postInfo(
     * 
     * @RequestParam(required = false) String title,
     * 
     * @RequestParam(required = false) String poster,
     * 
     * @RequestParam(required = false) String postDate,
     * 
     * @RequestParam(required = false) List<String> categories,
     * 
     * @RequestParam(required = false) String text,
     * Model model) {
     * 
     * model.addAttribute("title", title);
     * model.addAttribute("poster", poster);
     * model.addAttribute("postDate", postDate);
     * model.addAttribute("categories", categories);
     * model.addAttribute("text", text);
     * 
     * return "notice";
     * 
     * }
     */
    private List<Notice> list = new ArrayList<>();

    // Display the form and the list of tasks
    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("notice", new Notice()); // Initialize a new empty ToDo object for the form
        model.addAttribute("tasks", list); // Pass the list of tasks to the view

        return "notice"; // Thymeleaf template name
    }

    // Handle the form submission and add the task to the list
    @PostMapping("/")
    public String saveNotice(@Valid @ModelAttribute Notice info, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "notice"; // Return to the form page if validation fails
        }
        list.add(info); // Add the new task to the list
        return "redirect:/notice/"; // Redirect to the form page to show the updated list
    }

}
