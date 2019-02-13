package org.launchcode.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "menu")
public class MenuController 
{

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private CheeseDao cheeseDao;
	
	@RequestMapping(value="")
	public String index(Model model) 
	{
		model.addAttribute("title", "My Menus");
		model.addAttribute("menus", menuDao.findAll());
		return "menu/index";
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String displayAddMenuForm(Model model) 
	{
		model.addAttribute("title", "My Menus");
		model.addAttribute(new Menu());
		return "cheese/add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String processAddMenu(@ModelAttribute @Valid Menu newMenu, Model model, Errors errors) 
	{
		if (errors.hasErrors()) 
		{
			model.addAttribute("title", "My Menus");
			return "/menu/add";
			
		}
		menuDao.save(newMenu);
		return "redirect:view/"+newMenu.getId();
	}
	
	@RequestMapping(value="add-item/{menuId}", method=RequestMethod.GET)
	public String addItem(Model model, @PathVariable int menuId)
	{
		Menu aMenu = menuDao.findOne(menuId);
		AddMenuItemForm form = new AddMenuItemForm(aMenu, aMenu.getCheeses());
		List<Cheese> options = new ArrayList<>();
		int x = 0;
		for(Cheese cheese : cheeseDao.findAll()) 
		{
			x=0;
			for(Cheese check : aMenu.getCheeses()) 
			{
				if (cheese.equals(check)) 
				{
					x=1;
					break;
				}
			}
			if (x == 0) 
			{
				options.add(cheese);
			}
		}
        
		model.addAttribute("title", "Add Item to Menus: "+aMenu.getName());
		model.addAttribute("form", form);
		model.addAttribute("cheeses", options);
		
		return "menu/add-item";
		
		
	}
	@RequestMapping(value="add-item", method=RequestMethod.POST)
	public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm addMenuItemForm, Errors errors) 
	{
		if (errors.hasErrors()) 
		{
			return "menu/add-item";
		}
		Menu menu = menuDao.findOne(addMenuItemForm.getMenuId());
		Cheese cheese = cheeseDao.findOne(addMenuItemForm.getCheeseId());
		menu.addItem(cheese);
		menuDao.save(menu);
		
		return "redirect:view/"+menu.getId();
	}
	
	
}
