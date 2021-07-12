package com.ilang.myfridge.dto.fridge;

import com.ilang.myfridge.model.fridge.FridgeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FridgeUpdateRequestDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String fridgeName;

    @NotNull private String fridgeIcon;
    @NotNull private String fridgeBasic;
    @NotNull private FridgeType fridgeType;
    private String fridgeMemo;

    public FridgeUpdateRequestDto() {
    }
}
