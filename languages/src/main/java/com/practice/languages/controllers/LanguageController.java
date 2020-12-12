package com.practice.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practice.languages.models.Language;
import com.practice.languages.services.LanguageService;

@Controller
public class LanguageController {
	@Autowired
	private LanguageService languageService;
	
//	Displays all languages
	@RequestMapping("/languages")
	public String index(@ModelAttribute("language") Language lang, Model model) {
		List<Language> languages = languageService.allLanguages();
		System.out.println("list: "+languages);
		model.addAttribute("languages", languages);
		return "/languages/index.jsp";
	}
	
//	Adds a new languages
	@RequestMapping(value = "/languages", method = RequestMethod.POST)
	public String newlanguage(@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/languages";
		} else {
			languageService.createLanguage(language);
			return "redirect:/languages";
		}	
	}
	
//	Shows a selected language
	@RequestMapping("/languages/{id}")
	public String showlanguage(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "/languages/show.jsp";
	}
	
//	Displays edit page for select language
	@RequestMapping("/languages/{id}/edit")
	public String editlanguage(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "/languages/edit.jsp";
	}
//	Updates a selected languages
	@RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
	public String updatelanguage(@Valid @ModelAttribute("language") Language language, BindingResult result, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "redirect:/languages";
		} else {
			languageService.updateLanguage(language, id);
			return "redirect:/languages";
		}	
	}

//	Deletes a selected languages
	@RequestMapping(value = "/languages/{id}/delete")
	public String deletlanguage(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
	}
}