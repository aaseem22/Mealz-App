package com.example.model.responses

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponses( val categories: List<MealResponse>)

// Gson deserialization:
// Json -> data classes

// naming should be same as the gson format, otherwise uses  @SerializedName("") format as below

 data class MealResponse(
  @SerializedName("idCategory")  val  id: String,
  @SerializedName("strCategory")  val  name: String,
  @SerializedName("strCategoryThumb")  val  imageUrl: String,
  @SerializedName("strCategoryDescription")  val  description: String
 )

