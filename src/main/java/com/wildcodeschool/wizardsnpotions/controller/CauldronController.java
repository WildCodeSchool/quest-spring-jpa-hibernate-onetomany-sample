package com.wildcodeschool.wizardsnpotions.controller;

import com.wildcodeschool.wizardsnpotions.entity.Category;
import com.wildcodeschool.wizardsnpotions.entity.Potion;
import com.wildcodeschool.wizardsnpotions.repository.CategoryRepository;
import com.wildcodeschool.wizardsnpotions.repository.PotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CauldronController {

    @Autowired
    PotionRepository potionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public String init() {

        // remove all categories and potions
        potionRepository.deleteAll();
        categoryRepository.deleteAll();

        // create the restoration category
        Category restoration = categoryRepository.save(new Category("Restoration"));

        // add six potions to the restoration category
        potionRepository.save(new Potion("A Philter of Demonic Leech", 5, restoration));
        potionRepository.save(new Potion("A Potion of Antidepressant", 2, restoration));
        potionRepository.save(new Potion("A Ichor of Owls Wake", 2, restoration));
        potionRepository.save(new Potion("A Juice of Feast", 3, restoration));
        potionRepository.save(new Potion("A Vial of Nymph Breath", 4, restoration));
        potionRepository.save(new Potion("A Draught of Youth", 8, restoration));

        return "redirect:/potions?idCategory=" + restoration.getId();
    }

    @GetMapping("/potions")
    public String getPotions(Model out,
                              @RequestParam(required = false) Long idCategory) {

        if (idCategory == null) {
            return "redirect:/";
        }

        List<Potion> potions = new ArrayList<>();
        // load restoration category and get all its potions
        Optional<Category> optionalCategory = categoryRepository.findById(idCategory);
        if (optionalCategory.isPresent()) {
            potions = optionalCategory.get().getPotions();
        }
        out.addAttribute("potions", potions);

        return "potions";
    }

    @GetMapping("/potion/remove")
    public String removePotion(@RequestParam(required = false) Long idCategory,
                               @RequestParam(required = false) Long idPotion) {

        if (idCategory == null || idPotion != null) {
            potionRepository.deleteById(idPotion);
        }

        return "redirect:/potions?idCategory=" + idCategory;
    }
}
