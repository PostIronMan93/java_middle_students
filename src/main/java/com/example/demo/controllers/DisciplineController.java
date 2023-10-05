package com.example.demo.controllers;

import com.example.demo.models.Discipline;
import com.example.demo.repo.DisciplineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DisciplineController {
    @Autowired
    private DisciplineRepo disciplineRepo;

    @GetMapping("/disciplines")
    public String showDisciplines(Model model){
        Iterable<Discipline> disciplines = disciplineRepo.findAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }

    @GetMapping("/addDiscipline")
    public String addDisciplines(){
        return "addDiscipline";
    }

    @PostMapping("/addDiscipline")
    public String saveDiscipline(
            @RequestParam String discipline
    ){
        Discipline discipline1 = new Discipline(discipline, true);
        disciplineRepo.save(discipline1);
        return "redirect:/addDiscipline";
    }
    @GetMapping("/editDiscipline/{id}")
    public String editDiscipline(@PathVariable(value = "id") long id, Model model){
        Optional<Discipline>discipline = disciplineRepo.findById(id);
        model.addAttribute("discipline", discipline.get());
        return "editDiscipline";
    }
    @PostMapping("/editDiscipline")
    public String editDiscipline(
            @RequestParam long id,
            @RequestParam String discipline
    ){
        Optional<Discipline>discipline1 = disciplineRepo.findById(id);
        Discipline discipline2 = discipline1.get();
        discipline2.discipline = discipline;
        disciplineRepo.save(discipline2);
        return "redirect:/disciplines";
    }

    @GetMapping ("/deleteDiscipline/{id}")
    public String deleteDiscipline (@PathVariable(value = "id") long id){
        disciplineRepo.deleteById(id);
        return "redirect:/disciplines";
    }
}
