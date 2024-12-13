package vttp.batch5.ssf.noticeboard.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.services.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private List<Notice> list = new ArrayList<>();

    @Autowired
    private NoticeService noticeService; // Autowire NoticeService

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


    // Define the /status endpoint
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        boolean isHealthy = checkApplicationHealth();  // Method to check health status 

        // Create a JSON response based on health status
        JsonObject responseJson = Json.createObjectBuilder()
                .add("status", isHealthy ? "healthy" : "unavailable")
                .build();

        String response = responseJson.toString();

        if (isHealthy) {
            return ResponseEntity.ok()  // HTTP 200 OK
                    .header("Content-Type", "application/json")
                    .body(response);
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)  // HTTP 503 Service Unavailable
                    .header("Content-Type", "application/json")
                    .body(response);
        }
    }

    // Method to simulate checking the health of the application
    private boolean checkApplicationHealth() {
       
        return true;  
    }
}




    

