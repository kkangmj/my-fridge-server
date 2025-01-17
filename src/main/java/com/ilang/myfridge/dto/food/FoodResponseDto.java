package com.ilang.myfridge.dto.food;

import com.ilang.myfridge.model.food.Food;
import com.ilang.myfridge.model.food.FoodType;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodResponseDto {

  private Long foodId;
  private String foodName;
  private FoodType foodType;
  private LocalDate expireAt;
  private LocalDate createdAt;
  private String foodMemo;
  private Long fridgeId;

  public static FoodResponseDto from(Food food) {
    return new FoodResponseDto(
        food.getId(),
        food.getFoodName(),
        food.getFoodType(),
        food.getExpireAt(),
        LocalDate.from(food.getCreatedAt()),
        food.getFoodMemo(),
        food.getFridge().getId());
  }
}
