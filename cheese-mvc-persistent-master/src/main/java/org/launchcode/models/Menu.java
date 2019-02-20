package org.launchcode.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;





@Entity
public class Menu 
{

	@NotNull
	@Size(min=3, max=15)
	private String name;
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToMany
	private List<Cheese> cheeses;
	
	public void addItem(Cheese item) 
	{
		
	}
	
	public Menu() 
	{
		
	}
	
	public Menu(String name) 
	{
		this.setName(name);
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Integer getId() 
	{
		return id;
	}
	
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	public List<Cheese> getCheeses() 
	{
		return cheeses;
	}
	
	
	
	
}
