package com.practice.languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.languages.models.Language;
import com.practice.languages.repository.LanguageRepository;

@Service
public class LanguageService {
	
	@Autowired
	private LanguageRepository languageRepository;
	
	
	public List<Language> allLanguages() {
        return languageRepository.findAll();
    }
	
	public Language findLanguage(Long id) {
		Optional<Language> optionalLang = languageRepository.findById(id);
		if (optionalLang.isPresent()) {
			return optionalLang.get();
		}
		else {
			return null;
		}

	}
	
	public Language createLanguage(Language lang) {
		return languageRepository.save(lang);
		
	}
	
	public Language updateLanguage(Language lang, Long id) {
		Language langOg = findLanguage(id);
		langOg.setName(lang.getName());
		langOg.setCreator(lang.getCreator());
		langOg.setCurrentVersion(lang.getCurrentVersion());
		languageRepository.save(langOg);
		return langOg;
	}
	
	public void deleteLanguage(Long id) {
		languageRepository.deleteById(id);
	}
}
