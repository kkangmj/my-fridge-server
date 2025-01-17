package com.ilang.myfridge.model.fridge;

import com.ilang.myfridge.model.BaseTimeEntity;
import com.ilang.myfridge.model.food.Food;
import com.ilang.myfridge.model.user.User;

import java.util.Collections;
import java.util.List;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Fridge extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false)
  private String fridgeIcon;

  @Column(length = 50, nullable = false)
  private String fridgeName;

  @Enumerated(EnumType.STRING)
  @Column(length = 20, nullable = false)
  private FridgeType fridgeType;

  @Column(columnDefinition = "text default null")
  private String fridgeMemo;

  @Column(nullable = false)
  private boolean fridgeBasic;

  @ManyToOne(cascade= CascadeType.ALL)
  @JoinColumn(name = "userid")
  private User user;

  @OneToMany(fetch=FetchType.LAZY, mappedBy = "fridge")
  private List<Food> foodList;

  public static Fridge of(String fridgeName, FridgeType fridgeType, String fridgeMemo, boolean fridgeBasic, String fridgeIcon, User user) {
    List<Food> foodList = Collections.emptyList();
    return new Fridge(null, fridgeIcon, fridgeName, fridgeType, fridgeMemo, fridgeBasic, user, foodList);
  }

  public Fridge update(String fridgeName, FridgeType fridgeType, String fridgeMemo, boolean fridgeBasic, String fridgeIcon) {
    this.fridgeName = fridgeName;
    this.fridgeType = fridgeType;
    this.fridgeMemo = fridgeMemo;
    this.fridgeBasic = fridgeBasic;
    this.fridgeIcon = fridgeIcon;
    return this;
  }

}
