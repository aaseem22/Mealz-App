package com.example.mealzapp.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.model.responses.MealResponse
import java.lang.Float.min


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MealDetailsScreen(meal :MealResponse?){
    val scrollState = rememberLazyListState()
    val offSet = min(1f , 1-
            (scrollState.firstVisibleItemScrollOffset/600f + scrollState.firstVisibleItemIndex) )
    val size by animateDpAsState(targetValue =max(100.dp , 200.dp*offSet) )
   Surface(color= MaterialTheme.colors.background) {
       Column() {
           Surface(elevation = 8.dp) {
               Row(modifier = Modifier.fillMaxWidth()) {
                   Card(modifier = Modifier.padding(4.dp),
                       shape = CircleShape,
                       border = BorderStroke(
                           width = 2.dp,
                           color = Color.Gray
                       )) {
                       Image(
                           painter = rememberImagePainter(data = meal?.imageUrl,
                               builder = {
                                   transformations(CircleCropTransformation())
                               }),
                           contentDescription = null,
                           modifier = Modifier
                               .size(size)
                       )
                   }
                   Text(text = meal!!.name,
                       modifier = Modifier
                           .padding(16.dp)
                           .align(Alignment.CenterVertically)
                   )

               }
          }
           val dummyList = (0..100).map { it.toString() }
           LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally),
               state=scrollState
               ) {
                        items(dummyList){dummyItem->
                                    Text(text = dummyItem,
                                            modifier = Modifier.padding(24.dp)
                                        )
                        }
           }

       }
    }



}

enum class MealProfilePicState(val color: Color, val size: Dp, val borderWidth: Dp){
  Normal(Color.Red, 120.dp, 2.dp),
    Expanded(Color.Magenta,200.dp,8.dp)
}