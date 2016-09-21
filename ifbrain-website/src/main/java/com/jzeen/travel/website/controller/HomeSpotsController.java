package com.jzeen.travel.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotThemeRelate;
import com.jzeen.travel.data.entity.Theme;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CountryRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.SpotRepository;
import com.jzeen.travel.data.repository.SpotThemeRelateRepository;
import com.jzeen.travel.data.repository.ThemeRepository;

@Controller
@RequestMapping("/home")
public class HomeSpotsController {
	@Autowired
	SpotRepository _spotRepository;
	@Autowired
	ThemeRepository _themeRepository;
	@Autowired
	SpotThemeRelateRepository _spotThemeRelateRepository;
	@Autowired
	ImageRepository _imageRepository;
	@Autowired
	CityRepository _cityRepository;
	@Autowired
	CountryRepository _countryRepository;
	@RequestMapping(value = "/spots")
	public String spots(@ModelAttribute Theme Theme, Model model) {
		List<Theme> theme = _themeRepository.findAll();
		System.out.println(theme.size());
		for (int i = 0; i < theme.size(); i++) {
			int themeId = theme.get(i).getId();
			int count = _spotThemeRelateRepository.findByTheme(themeId).size();
			theme.get(i).setCount(count);
		/*	List<Image> image= _imageRepository.findByid(themeId);
			model.addAttribute("image", image);*/
		}
		List<City> city = _cityRepository.findAll();
		List<Country> country= _countryRepository.findAll();
		model.addAttribute("country", country);
		model.addAttribute("city", city);
		model.addAttribute("theme", theme);
		return "/home/spots";

	}
	@RequestMapping(value = "/spotsList/{id}" , method = RequestMethod.GET)
	public String spotsList(@PathVariable Integer id,@ModelAttribute SpotThemeRelate SpotThemeRelate, Model model ) {
		System.out.println("id==========="+id);
		List<SpotThemeRelate> spotThemeRelate= _spotThemeRelateRepository.findByTheme(id);
		model.addAttribute("spotThemeRelate", spotThemeRelate);
		for(int i=0;i<spotThemeRelate.size();i++){
			Image image=spotThemeRelate.get(i).getSpot().getImage();
			model.addAttribute("image", image);
		}
		
		return "/home/spotslist";

	}
}
