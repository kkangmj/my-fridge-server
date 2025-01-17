package com.ilang.myfridge.service.food;

import com.ilang.myfridge.controller.exception.ErrorCode;
import com.ilang.myfridge.controller.exception.NotFoundException;
import com.ilang.myfridge.model.food.Food;
import com.ilang.myfridge.model.food.FoodType;
import com.ilang.myfridge.model.fridge.Fridge;
import com.ilang.myfridge.model.fridge.FridgeType;
import com.ilang.myfridge.repository.food.FoodRepository;
import com.ilang.myfridge.repository.fridge.FridgeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FoodService {

  private final FridgeRepository fridgeRepository;
  private final FoodRepository foodRepository;

  @Transactional(readOnly = true)
  public Food findFoodDetail(Long foodId) {

    Food food = getFoodIfExist(foodId);

    return food;
  }

  @Transactional
  public List<Food> findFoodList(Long fridgeId) {

    Fridge fridge = getFridgeIfExist(fridgeId);

    return foodRepository.findAllByFridge(fridge);
  }

  @Transactional
  public Food saveFood(
      String foodName, FoodType foodType, String foodMemo, LocalDate expireAt, Long fridgeId) {

    Fridge fridge = getFridgeIfExist(fridgeId);

    checkFoodName(fridge, foodName);
    checkFoodType(fridge.getFridgeType(), foodType);

    return foodRepository.save(Food.of(foodName, foodType, foodMemo, expireAt, fridge));
  }

  @Transactional
  public Food updateFood(
      Long foodId,
      String foodName,
      FoodType foodType,
      String foodMemo,
      LocalDate expireAt,
      Long fridgeId) {

    Food food = getFoodIfExist(foodId);
    Fridge fridge = getFridgeIfExist(fridgeId);

    checkFoodName(fridge, foodId, foodName);
    checkFoodType(fridge.getFridgeType(), foodType);

    return food.update(foodName, foodType, foodMemo, expireAt, fridge);
  }

  @Transactional
  public void deleteFood(Long foodId) {
    getFoodIfExist(foodId);
    foodRepository.deleteById(foodId);
  }

  private Food getFoodIfExist(Long foodId) {
    return foodRepository
        .findById(foodId)
        .orElseThrow(() -> NotFoundException.of(ErrorCode.FOOD_NOT_FOUND));
  }

  private Fridge getFridgeIfExist(Long fridgeId) {
    return fridgeRepository
        .findById(fridgeId)
        .orElseThrow(() -> NotFoundException.of(ErrorCode.FRIDGE_NOT_FOUND));
  }

  private void checkFoodName(Fridge fridge, String foodName) {
    List<String> foodNameList =
        foodRepository.findAllByFridge(fridge).stream()
            .map(food -> food.getFoodName())
            .filter(food -> food.equals(foodName))
            .collect(Collectors.toList());

    if (!foodNameList.isEmpty()) {
      throw NotFoundException.of(ErrorCode.FOOD_NAME_DUPLICATED);
    }
  }

  private void checkFoodName(Fridge fridge, Long foodId, String foodName) {
    List<String> foodNameList =
        foodRepository.findAllByFridge(fridge).stream()
            .filter(food -> (!food.getId().equals(foodId)))
            .map(food -> food.getFoodName())
            .filter(food -> food.equals(foodName))
            .collect(Collectors.toList());

    if (!foodNameList.isEmpty()) {
      throw NotFoundException.of(ErrorCode.FOOD_NAME_DUPLICATED);
    }
  }

  private void checkFoodType(FridgeType fridgeType, FoodType foodType){
    if (!foodType.typeMatch(fridgeType)) {
      // todo NotFoundException 변경 필요
      throw NotFoundException.of(ErrorCode.TYPE_NOT_MATCH);
    }
  }
}
