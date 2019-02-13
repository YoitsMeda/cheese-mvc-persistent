package org.launchcode.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category 
{
@Id
@GeneratedValue
private int id;

@NotNull
@Size(min=3, max=15)
private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getId() {
	return id;
}

public Category() { } 

@OneToMany
@JoinColumn(name = "category_id")
private List<Cheese> cheeses = new ArrayList<>();


	
	

}//END CLASS

