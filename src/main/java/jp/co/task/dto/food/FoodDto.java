package jp.co.task.dto.food;

public class FoodDto {
	private Integer id;
	private String name;
	private double protein;
	private double fat;
	private double carbo;
	private double per;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbo() {
		return carbo;
	}
	public void setCarbo(double carbo) {
		this.carbo = carbo;
	}
	public double getPer() {
		return per;
	}
	public void setPer(double per) {
		this.per = per;
	}
//	public double getMeal_quantity() {
//		return meal_quantity;
//	}
//	public void setMeal_quantity(double meal_quantity) {
//		this.meal_quantity = meal_quantity;
//	}
//	private double meal_quantity;

}
