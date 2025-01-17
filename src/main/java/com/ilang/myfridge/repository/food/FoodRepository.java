package com.ilang.myfridge.repository.food;

import com.ilang.myfridge.model.food.Food;
import com.ilang.myfridge.model.fridge.Fridge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

  List<Food> findAllByFridge(Fridge fridge);

  void deleteById(Food foodId);
}
