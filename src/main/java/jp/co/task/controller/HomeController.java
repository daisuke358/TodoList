package jp.co.task.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.task.dto.dateMeal.DateMealDto;
import jp.co.task.dto.food.FoodDto;
import jp.co.task.dto.task.TaskDto;
import jp.co.task.form.DateMealForm;
import jp.co.task.form.FoodForm;
import jp.co.task.service.HomeService;

@Controller
public class HomeController {

	@Autowired
    private HomeService homeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String foodAll(Model model) {
		TaskDto user = homeService.getTask(1);
        model.addAttribute("user", user);
	    List<FoodDto> foods =homeService.getFoodAll();
	    model.addAttribute("foods", foods);
	    List<DateMealDto> date_meals =homeService.getDateMealAll();
	    model.addAttribute("date_meals", date_meals);
	    DateMealForm form = new DateMealForm();
        model.addAttribute("dateMealForm", form);

	    Date d = new Date();

        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        String c1 = d1.format(d);
        model.addAttribute("date1", c1);

        SimpleDateFormat d2 = new SimpleDateFormat("yyyy年MM月dd日");
        String c2 = d2.format(d);
        model.addAttribute("date2", c2);

        double protein =0;
        double fat =0;
        double carbo =0;
        for (DateMealDto date_meal : date_meals) {
        	if(date_meal.getDate().equals(c1)) {
        		for (FoodDto food : foods) {
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			protein = protein + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fat = fat + food.getFat() * date_meal.getMeal_quantity() /100;
            			carbo = carbo + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	protein = Math.round(protein);
        	fat = Math.round(fat);
        	carbo = Math.round(carbo);
        	model.addAttribute("protein", protein);
        	model.addAttribute("fat", fat);
        	model.addAttribute("carbo", carbo);
        }
        double calory = protein * 4 + fat * 9 + carbo * 4;
        model.addAttribute("calory", calory);
        double proteinPer = Math.round(protein * 4 / calory * 100);
        model.addAttribute("proteinPer", proteinPer);
        double fatPer = Math.round(fat * 9 / calory * 100);
        model.addAttribute("fatPer", fatPer);
        double carboPer = Math.round(carbo * 4 / calory * 100);
        model.addAttribute("carboPer", carboPer);
//        String A = foods.get(1).getName();
	    return "foodAll";
	}


    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public String foodInsert(Model model) {
        FoodForm form = new FoodForm();
        model.addAttribute("foodForm", form);
        return "food";
    }

    @RequestMapping(value = "/food", method = RequestMethod.POST)
    public String testInsert(@ModelAttribute FoodForm form, Model model) {

    	String name = form.getName();
    	String protein = String.valueOf(form.getProtein());
    	String fat = String.valueOf(form.getFat());
    	String carbo = String.valueOf(form.getCarbo());
    	String per = String.valueOf(form.getPer());
        int count = homeService.insertFood(name, protein, fat, carbo, per);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String testInsert(@ModelAttribute DateMealForm form, Model model) {
//    	Date d = new Date();
//        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
//        String c1 = d1.format(d);
//        System.out.println(form.getId());

        String id = String.valueOf(form.getId());
        String meal_quantity = String.valueOf(form.getMeal_quantity());
        String c1 = String.valueOf(form.getDate());
        int count = homeService.insertDateMeal(id, meal_quantity, c1);
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String theDayInsert(@ModelAttribute DateMealForm form, Model model) {
    	Date d = new Date();
        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        String c1 = d1.format(d);
        System.out.println(form.getId());

        String id = String.valueOf(form.getId());
        String meal_quantity = String.valueOf(form.getMeal_quantity());


        int count = homeService.insertDateMeal(id, meal_quantity, c1);
        return "redirect:/";
    }

    @RequestMapping(value = "/days", method = RequestMethod.GET)
    public String days(Model model)  {
    	List<FoodDto> foods =homeService.getFoodAll();
 	    model.addAttribute("foods", foods);
    	List<DateMealDto> date_meals =homeService.getDateMealAll();
	    model.addAttribute("date_meals", date_meals);

	    Date d = new Date();
//	    当日.
	    Calendar thatday = Calendar.getInstance();
        thatday.setTime(d);
		thatday.add(Calendar.DAY_OF_MONTH, 0);
		String thatdayA = new SimpleDateFormat("yyyy-MM-dd").format(thatday.getTime());
        String thatdayB = new SimpleDateFormat("yyyy年MM月dd日").format(thatday.getTime());
        model.addAttribute("thatDayB", thatdayB);
        model.addAttribute("thatdayA", thatdayA);

//      1日前.
        Calendar oneDayAgo = Calendar.getInstance();
        oneDayAgo.setTime(d);
        oneDayAgo.add(Calendar.DAY_OF_MONTH, -1);
		String oneDayAgoA = new SimpleDateFormat("yyyy-MM-dd").format(oneDayAgo.getTime());
        String oneDayAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(oneDayAgo.getTime());
        model.addAttribute("oneDayAgoB", oneDayAgoB);
        model.addAttribute("oneDayAgoA", oneDayAgoA);
//      2日前
        Calendar twoDaysAgo = Calendar.getInstance();
        twoDaysAgo.setTime(d);
        twoDaysAgo.add(Calendar.DAY_OF_MONTH, -2);
		String twoDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(twoDaysAgo.getTime());
        String twoDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(twoDaysAgo.getTime());
        model.addAttribute("twoDaysAgoB", twoDaysAgoB);
        model.addAttribute("twoDaysAgoA", twoDaysAgoA);
//      3日前
        Calendar threeDaysAgo = Calendar.getInstance();
        threeDaysAgo.setTime(d);
        threeDaysAgo.add(Calendar.DAY_OF_MONTH, -3);
		String threeDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(threeDaysAgo.getTime());
        String threeDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(threeDaysAgo.getTime());
        model.addAttribute("threeDaysAgoB", threeDaysAgoB);
        model.addAttribute("threeDaysAgoA", threeDaysAgoA);
//      4日前
        Calendar fourDaysAgo = Calendar.getInstance();
        fourDaysAgo.setTime(d);
        fourDaysAgo.add(Calendar.DAY_OF_MONTH, -4);
		String fourDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(fourDaysAgo.getTime());
        String fourDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(fourDaysAgo.getTime());
        model.addAttribute("fourDaysAgoB", fourDaysAgoB);
        model.addAttribute("fourDaysAgoA", fourDaysAgoA);
//      5日前
        Calendar fiveDaysAgo = Calendar.getInstance();
        fiveDaysAgo.setTime(d);
        fiveDaysAgo.add(Calendar.DAY_OF_MONTH, -5);
		String fiveDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(fiveDaysAgo.getTime());
        String fiveDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(fiveDaysAgo.getTime());
        model.addAttribute("fiveDaysAgoB", fiveDaysAgoB);
        model.addAttribute("fiveDaysAgoA", fiveDaysAgoA);
//      6日前
        Calendar sixDaysAgo = Calendar.getInstance();
        sixDaysAgo.setTime(d);
        sixDaysAgo.add(Calendar.DAY_OF_MONTH, -6);
		String sixDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(sixDaysAgo.getTime());
        String sixDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(sixDaysAgo.getTime());
        model.addAttribute("sixDaysAgoB", sixDaysAgoB);
        model.addAttribute("sixDaysAgoA", sixDaysAgoA);

        double proteinA =0;
        double fatA =0;
        double carboA =0;
        double proteinB =0;
        double fatB =0;
        double carboB =0;
        double proteinC =0;
        double fatC =0;
        double carboC =0;
        double proteinD =0;
        double fatD =0;
        double carboD =0;
        double proteinE =0;
        double fatE =0;
        double carboE =0;
        double proteinF =0;
        double fatF =0;
        double carboF =0;
        double proteinG =0;
        double fatG =0;
        double carboG =0;
        for (DateMealDto date_meal : date_meals) {  /*ミールテーブルにあるレコード分だけ繰り返してね。*/
        	if(date_meal.getDate().equals(thatdayA)) { /*その日のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinA = proteinA + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatA = fatA + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboA = carboA + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(oneDayAgoA)) { /*1日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinB = proteinB + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatB = fatB + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboB = carboB + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(twoDaysAgoA)) { /*2日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinC = proteinC + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatC = fatC + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboC = carboC + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(threeDaysAgoA)) { /*3日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinD = proteinD + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatD = fatD + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboD = carboD + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(fourDaysAgoA)) { /*4日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinE = proteinE + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatE = fatE + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboE = carboE + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(fiveDaysAgoA)) { /*5日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinF = proteinF + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatF = fatF + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboF = carboF + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(sixDaysAgoA)) { /*6日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinG = proteinG + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatG = fatG + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboG = carboG + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}

        }
        double caloryA = Math.round(proteinA * 4 + fatA * 9 + carboA * 4);
        model.addAttribute("caloryA", caloryA);
        model.addAttribute("proteinA", Math.round(proteinA * 4));
        model.addAttribute("fatA", Math.round(fatA * 9));
        model.addAttribute("carboA", Math.round(carboA * 4));
        double caloryB = Math.round(proteinB * 4 + fatB * 9 + carboB * 4);
        model.addAttribute("caloryB", caloryB);
        model.addAttribute("proteinB", Math.round(proteinB *4));
        model.addAttribute("fatB", Math.round(fatB *9));
        model.addAttribute("carboB", Math.round(carboB *4));
        double caloryC = Math.round(proteinC * 4 + fatC * 9 + carboC * 4);
        model.addAttribute("caloryC", caloryC);
        model.addAttribute("proteinC", Math.round(proteinC *4));
        model.addAttribute("fatC", Math.round(fatC *9));
        model.addAttribute("carboC", Math.round(carboC *4));
        double caloryD = Math.round(proteinD * 4 + fatD * 9 + carboD * 4);
        model.addAttribute("caloryD", caloryD);
        model.addAttribute("proteinD", Math.round(proteinD *4));
        model.addAttribute("fatD", Math.round(fatD *9));
        model.addAttribute("carboD", Math.round(carboD *4));
        double caloryE = Math.round(proteinE * 4 + fatE * 9 + carboE * 4);
        model.addAttribute("caloryE", caloryE);
        model.addAttribute("proteinE", Math.round(proteinE *4));
        model.addAttribute("fatE", Math.round(fatE *9));
        model.addAttribute("carboE", Math.round(carboE *4));
        double caloryF = Math.round(proteinF * 4 + fatF * 9 + carboF * 4);
        model.addAttribute("caloryF", caloryF);
        model.addAttribute("proteinF", Math.round(proteinF *4));
        model.addAttribute("fatF", Math.round(fatF *9));
        model.addAttribute("carboF", Math.round(carboF *4));
        double caloryG = Math.round(proteinG * 4 + fatG * 9 + carboG * 4);
        model.addAttribute("caloryG", caloryG);
        model.addAttribute("proteinG", Math.round(proteinG *4));
        model.addAttribute("fatG", Math.round(fatG *9));
        model.addAttribute("carboG", Math.round(carboG *4));
        return "days";
    }

    @RequestMapping(value = "/dayss", method = RequestMethod.GET)
    public String days(Model model, @RequestParam("weekChange") String weekChange)  {
    	int weekChangeInt = Integer.parseInt(weekChange) * 7;

    	List<FoodDto> foods =homeService.getFoodAll();
 	    model.addAttribute("foods", foods);
    	List<DateMealDto> date_meals =homeService.getDateMealAll();
	    model.addAttribute("date_meals", date_meals);

	    Date d = new Date();
//	    当日
	    Calendar thatday = Calendar.getInstance();
        thatday.setTime(d);
		thatday.add(Calendar.DAY_OF_MONTH, weekChangeInt);
		String thatdayA = new SimpleDateFormat("yyyy-MM-dd").format(thatday.getTime());
        String thatdayB = new SimpleDateFormat("yyyy年MM月dd日").format(thatday.getTime());
        model.addAttribute("thatDayB", thatdayB);
        model.addAttribute("thatdayA", thatdayA);
//      1日前
        Calendar oneDayAgo = Calendar.getInstance();
        oneDayAgo.setTime(d);
        oneDayAgo.add(Calendar.DAY_OF_MONTH, weekChangeInt-1);
		String oneDayAgoA = new SimpleDateFormat("yyyy-MM-dd").format(oneDayAgo.getTime());
        String oneDayAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(oneDayAgo.getTime());
        model.addAttribute("oneDayAgoB", oneDayAgoB);
        model.addAttribute("oneDayAgoA", oneDayAgoA);
//      2日前
        Calendar twoDaysAgo = Calendar.getInstance();
        twoDaysAgo.setTime(d);
        twoDaysAgo.add(Calendar.DAY_OF_MONTH, weekChangeInt-2);
		String twoDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(twoDaysAgo.getTime());
        String twoDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(twoDaysAgo.getTime());
        model.addAttribute("twoDaysAgoB", twoDaysAgoB);
        model.addAttribute("twoDaysAgoA", twoDaysAgoA);
//      3日前
        Calendar threeDaysAgo = Calendar.getInstance();
        threeDaysAgo.setTime(d);
        threeDaysAgo.add(Calendar.DAY_OF_MONTH, weekChangeInt-3);
		String threeDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(threeDaysAgo.getTime());
        String threeDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(threeDaysAgo.getTime());
        model.addAttribute("threeDaysAgoB", threeDaysAgoB);
        model.addAttribute("threeDaysAgoA", threeDaysAgoA);
//      4日前
        Calendar fourDaysAgo = Calendar.getInstance();
        fourDaysAgo.setTime(d);
        fourDaysAgo.add(Calendar.DAY_OF_MONTH, weekChangeInt-4);
		String fourDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(fourDaysAgo.getTime());
        String fourDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(fourDaysAgo.getTime());
        model.addAttribute("fourDaysAgoB", fourDaysAgoB);
        model.addAttribute("fourDaysAgoA", fourDaysAgoA);
//      5日前
        Calendar fiveDaysAgo = Calendar.getInstance();
        fiveDaysAgo.setTime(d);
        fiveDaysAgo.add(Calendar.DAY_OF_MONTH, weekChangeInt-5);
		String fiveDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(fiveDaysAgo.getTime());
        String fiveDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(fiveDaysAgo.getTime());
        model.addAttribute("fiveDaysAgoB", fiveDaysAgoB);
        model.addAttribute("fiveDaysAgoA", fiveDaysAgoA);
//      6日前
        Calendar sixDaysAgo = Calendar.getInstance();
        sixDaysAgo.setTime(d);
        sixDaysAgo.add(Calendar.DAY_OF_MONTH, weekChangeInt-6);
		String sixDaysAgoA = new SimpleDateFormat("yyyy-MM-dd").format(sixDaysAgo.getTime());
        String sixDaysAgoB = new SimpleDateFormat("yyyy年MM月dd日").format(sixDaysAgo.getTime());
        model.addAttribute("sixDaysAgoB", sixDaysAgoB);
        model.addAttribute("sixDaysAgoA", sixDaysAgoA);

        double proteinA =0;
        double fatA =0;
        double carboA =0;
        double proteinB =0;
        double fatB =0;
        double carboB =0;
        double proteinC =0;
        double fatC =0;
        double carboC =0;
        double proteinD =0;
        double fatD =0;
        double carboD =0;
        double proteinE =0;
        double fatE =0;
        double carboE =0;
        double proteinF =0;
        double fatF =0;
        double carboF =0;
        double proteinG =0;
        double fatG =0;
        double carboG =0;
        for (DateMealDto date_meal : date_meals) {  /*ミールテーブルにあるレコード分だけ繰り返してね。*/
        	if(date_meal.getDate().equals(thatdayA)) { /*その日のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinA = proteinA + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatA = fatA + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboA = carboA + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(oneDayAgoA)) { /*1日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinB = proteinB + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatB = fatB + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboB = carboB + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(twoDaysAgoA)) { /*2日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinC = proteinC + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatC = fatC + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboC = carboC + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(threeDaysAgoA)) { /*3日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinD = proteinD + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatD = fatD + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboD = carboD + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(fourDaysAgoA)) { /*4日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinE = proteinE + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatE = fatE + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboE = carboE + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(fiveDaysAgoA)) { /*5日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinF = proteinF + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatF = fatF + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboF = carboF + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	if(date_meal.getDate().equals(sixDaysAgoA)) { /*6日前のやつなら*/
        		for (FoodDto food : foods) { /**/
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			proteinG = proteinG + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fatG = fatG + food.getFat() * date_meal.getMeal_quantity() /100;
            			carboG = carboG + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}

        }
        double caloryA = Math.round(proteinA * 4 + fatA * 9 + carboA * 4);
        model.addAttribute("caloryA", caloryA);
        model.addAttribute("proteinA", Math.round(proteinA * 4));
        model.addAttribute("fatA", Math.round(fatA * 9));
        model.addAttribute("carboA", Math.round(carboA * 4));
        double caloryB = Math.round(proteinB * 4 + fatB * 9 + carboB * 4);
        model.addAttribute("caloryB", caloryB);
        model.addAttribute("proteinB", Math.round(proteinB *4));
        model.addAttribute("fatB", Math.round(fatB *9));
        model.addAttribute("carboB", Math.round(carboB *4));
        double caloryC = Math.round(proteinC * 4 + fatC * 9 + carboC * 4);
        model.addAttribute("caloryC", caloryC);
        model.addAttribute("proteinC", Math.round(proteinC *4));
        model.addAttribute("fatC", Math.round(fatC *9));
        model.addAttribute("carboC", Math.round(carboC *4));
        double caloryD = Math.round(proteinD * 4 + fatD * 9 + carboD * 4);
        model.addAttribute("caloryD", caloryD);
        model.addAttribute("proteinD", Math.round(proteinD *4));
        model.addAttribute("fatD", Math.round(fatD *9));
        model.addAttribute("carboD", Math.round(carboD *4));
        double caloryE = Math.round(proteinE * 4 + fatE * 9 + carboE * 4);
        model.addAttribute("caloryE", caloryE);
        model.addAttribute("proteinE", Math.round(proteinE *4));
        model.addAttribute("fatE", Math.round(fatE *9));
        model.addAttribute("carboE", Math.round(carboE *4));
        double caloryF = Math.round(proteinF * 4 + fatF * 9 + carboF * 4);
        model.addAttribute("caloryF", caloryF);
        model.addAttribute("proteinF", Math.round(proteinF *4));
        model.addAttribute("fatF", Math.round(fatF *9));
        model.addAttribute("carboF", Math.round(carboF *4));
        double caloryG = Math.round(proteinG * 4 + fatG * 9 + carboG * 4);
        model.addAttribute("caloryG", caloryG);
        model.addAttribute("proteinG", Math.round(proteinG *4));
        model.addAttribute("fatG", Math.round(fatG *9));
        model.addAttribute("carboG", Math.round(carboG *4));
        return "days";
    }


	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String dateMealDelete(@ModelAttribute DateMealForm form, Model model) {
	    int count = homeService.deleteDateMeal(form.getId());
	    return "forward:";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String foodAllEdit(Model model, @RequestParam("day") String day) {
		model.addAttribute("day", day);

		TaskDto user = homeService.getTask(1);
        model.addAttribute("user", user);
	    List<FoodDto> foods =homeService.getFoodAll();
	    model.addAttribute("foods", foods);
	    List<DateMealDto> date_meals =homeService.getDateMealAll();
	    model.addAttribute("date_meals", date_meals);
	    DateMealForm form = new DateMealForm();
        model.addAttribute("dateMealForm", form);

	    Date d = new Date();

        SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMdd");
        String c1 = d1.format(d);
        model.addAttribute("date1", c1);

        SimpleDateFormat d2 = new SimpleDateFormat("yyyy年MM月dd日");
        String c2 = d2.format(d);
        model.addAttribute("date2", c2);

        double protein =0;
        double fat =0;
        double carbo =0;
        for (DateMealDto date_meal : date_meals) {
        	if(date_meal.getDate().equals(day)) {
        		for (FoodDto food : foods) {
        			if(food.getId().equals(date_meal.getFood_id())) {
        				model.addAttribute("today_food", food.getName());
            			protein = protein + food.getProtein() * date_meal.getMeal_quantity() /100;
            			fat = fat + food.getFat() * date_meal.getMeal_quantity() /100;
            			carbo = carbo + food.getCarbo() * date_meal.getMeal_quantity() /100;
        			}
        		}
        	}
        	protein = Math.round(protein);
        	fat = Math.round(fat);
        	carbo = Math.round(carbo);
        	model.addAttribute("protein", protein);
        	model.addAttribute("fat", fat);
        	model.addAttribute("carbo", carbo);
        }
        double calory = protein * 4 + fat * 9 + carbo * 4;
        model.addAttribute("calory", calory);
        double proteinPer = Math.round(protein * 4 / calory * 100);
        model.addAttribute("proteinPer", proteinPer);
        double fatPer = Math.round(fat * 9 / calory * 100);
        model.addAttribute("fatPer", fatPer);
        double carboPer = Math.round(carbo * 4 / calory * 100);
        model.addAttribute("carboPer", carboPer);
	    return "foodAllEdit";
	}
}
