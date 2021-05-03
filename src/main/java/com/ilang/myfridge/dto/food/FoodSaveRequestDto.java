package com.ilang.myfridge.dto.food;

import com.ilang.myfridge.model.food.FoodType;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodSaveRequestDto {

  @NotBlank
  @Size(min = 1, max = 50)
  private String foodName;

  // todo enum validation
  @NotBlank private FoodType foodType;
  private String foodMemo;

  @NotBlank private LocalDate expireAt;

  @NotBlank private Long fridgeId;
}
