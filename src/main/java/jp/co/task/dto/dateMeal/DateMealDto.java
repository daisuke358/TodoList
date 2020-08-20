package jp.co.task.dto.dateMeal;

import java.sql.Timestamp;

public class DateMealDto {
	private Integer id;
	private Integer food_id;
	private double meal_quantity;
	private String date;
	private Timestamp created_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFood_id() {
		return food_id;
	}
	public void setFood_id(Integer food_id) {
		this.food_id = food_id;
	}
	public double getMeal_quantity() {
		return meal_quantity;
	}
	public void setMeal_quantity(double meal_quantity) {
		this.meal_quantity = meal_quantity;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
