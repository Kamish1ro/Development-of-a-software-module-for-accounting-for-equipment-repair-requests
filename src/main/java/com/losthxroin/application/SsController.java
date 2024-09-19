package com.losthxroin.application;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping
public class SsController {
    private ApplicationForm lastUpdate = new ApplicationForm();
    private final ApplicationFormsDAO applicationFormsDAO;
    private final CommentsDAO commentsDAO;

    @Autowired
    public SsController(ApplicationFormsDAO applicationFormsDAO, CommentsDAO commentsDAO){
        this.applicationFormsDAO = applicationFormsDAO;
        this.commentsDAO = commentsDAO;
    }

    @GetMapping
    public String a(){
        return "main";
    }

    @GetMapping("applicationForms")
    public String s(Model model){
        model.addAttribute("applicationForms", applicationFormsDAO.readAll());
        model.addAttribute("lastUpdate", lastUpdate);
        return "applicationForms";
    }

    @GetMapping("applicationForms/{id}")
    public String ss(@PathVariable("id") int id, Model model){
        model.addAttribute("applicationForm", applicationFormsDAO.read(id));
        model.addAttribute("comments", commentsDAO.read(id));
        model.addAttribute("addComment", new Comment());
        return "applicationForm";
    }

    @GetMapping("applicationForms/new")
    public String newApplicationForm(@ModelAttribute("applicationForm") ApplicationForm applicationForm){
        return "newApplicationForm";
    }

    @PostMapping("/applicationForms")
    public String create(@ModelAttribute("applicationForm") @Valid ApplicationForm applicationForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "newApplicationForm";
        Date date = new Date();
        applicationForm.setStartDate(date);
        String strDate = date.getDate() + "."+(date.getMonth()+1)+"."+(date.getYear()+1900);
        applicationForm.setDate(strDate);

        applicationFormsDAO.create(applicationForm);
        return "redirect:/applicationForms";
    }

    @GetMapping("/applicationForms/update/{id}")
    public String formUpdate(@PathVariable("id") int id, Model model){
        model.addAttribute("applicationForm", applicationFormsDAO.read(id));
        return "updateApplicationForm";
    }

    /**
     * @PutMapping не поддерживается классом с аннотацией @Controller, поэтому я сделал всё через @PostMapping
     * */
    @PostMapping("/applicationForms/{id}")
    public String update(@ModelAttribute("applicationForm") @Valid ApplicationForm applicationForm,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()) return "updateApplicationForm";
        if (applicationForm.getStatus().equals("Выполнено")){
            Date date = new Date();
            applicationForm.setCompletionDate(date);
        }
        applicationForm.setStartDate(applicationFormsDAO.read(id).getStartDate());
        applicationFormsDAO.update(applicationForm, id);
        lastUpdate = applicationFormsDAO.read(id);
        return "redirect:/applicationForms/"+id;
    }

    @PostMapping("/applicationForms/comment/{id}")
    public String addComment(@ModelAttribute("addComment") @Valid Comment comment,
                             BindingResult bindingResult,
                             @PathVariable("id") int id){
        if(bindingResult.hasErrors()) return "redirect:/applicationForms/"+ id;
        comment.setApplicationFormsID(id);
        commentsDAO.create(comment);
        return "redirect:/applicationForms/"+ id;
    }
    @GetMapping("/applicationForms/stats")
    public String stats(Model model){
        model.addAttribute("applicationForms", applicationFormsDAO);
        return "stats";
    }
}
