package com.example.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mealzapp.ui.theme.MealzAppTheme
import com.example.model.responses.MealResponse

// we have to use the remember method in composable as composable fun get reused in th lifecycle
@Composable
fun MealsCategoriesScreen(navigationCallBack:(String)-> Unit ) {
    // we want view (composable) to depend on viewModel.
    val viewModel : MealsCategoriesViewModel= viewModel()
    val meals = viewModel.mealState.value

    Surface(color = Color.Transparent) {
        LazyColumn(contentPadding = PaddingValues(8.dp),
        ){
            items(meals) { meal ->
                MealCategory(meal, navigationCallBack )
            }
        }
    }

}
@Composable
fun MealCategory(meal:MealResponse,navigationCallBack:(String)-> Unit){
    var isExpanded by remember { mutableStateOf(false) }
    Card(shape = RoundedCornerShape(16.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                navigationCallBack(meal.id)
            }
        ) {
        Row(modifier= Modifier.animateContentSize()) {
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp)
                .fillMaxWidth(0.8f)
            ) {
                Text(text = meal.name,
                style= MaterialTheme.typography.h6
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = meal.description,
                        style= MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded)10
                                        else 4,
                        textAlign = TextAlign.Start
                    )
                }

            }
            Icon(imageVector = if (isExpanded)
                                    Icons.Filled.KeyboardArrowUp
                                else
                                         Icons.Filled.KeyboardArrowDown,
            contentDescription = "expandIcon",
            modifier = Modifier
                .padding(16.dp)
                .align(if
                        (isExpanded)
                            Alignment.Bottom
                           else
                               Alignment.CenterVertically
                )
                .clickable { isExpanded =!isExpanded }

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen({
        })
    }
}