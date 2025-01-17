package com.ilang.myfridge.dto.food;

import com.ilang.myfridge.model.food.Food;
import com.ilang.myfridge.model.food.FoodType;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodDetailResponseDto {

  private Long foodId;
  private String foodName;
  private FoodType foodType;
  private String foodMemo;
  private LocalDate expireAt;

  public static FoodDetailResponseDto from(Food food) {
    return new FoodDetailResponseDto(
        food.getId(),
        food.getFoodMemo(),
        food.getFoodType(),
        food.getFoodMemo(),
        food.getExpireAt());
  }
}
