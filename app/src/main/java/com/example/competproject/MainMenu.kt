package com.example.competproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier
                .background(colorResource(id = R.color.light_greyy))
                .fillMaxSize()){
                MainScreenInMainMenu()
            }
            
        }
    }
}
//@Preview (showBackground = true)
@Composable
fun MainScreenInMainMenu(){
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
Column {
    //Spacer(modifier = Modifier.padding(20.dp))
    upperMain()
    Spacer(modifier = Modifier.padding(top= 10.dp))
Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center) {

    SearchBox(
        query,
        active,
        onQueryChange = { query = it },
        onActiveChange = { active = it }
    )
    Spacer(modifier = Modifier.padding(start = 10.dp))
    FilterBox()

}


    lazyCategory()

}





}

@Composable
fun upperMain(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.menu), contentDescription ="menu button" , Modifier.size(30.dp))
        // Рисуем три линии
//        Canvas(modifier = Modifier.padding(bottom = 8.dp)) {
//            val strokeWidth = 2.dp.toPx()
//            val lineSpacing = 4.dp.toPx()
//            val lineLength = 30.dp.toPx()
//            val startX = 0f
//            val startY = size.height / 2 - lineSpacing - strokeWidth / 2
//
//            // Рисуем три горизонтальные линии
//            for (i in 0 until 3) {
//                drawLine(
//                    color = Color.Black,
//                    start = Offset(startX, startY + i * lineSpacing),
//                    end = Offset(startX + lineLength, startY + i * lineSpacing),
//                    strokeWidth = strokeWidth,
//                    cap = StrokeCap.Round
//                )
//            }
//        }

        // Текст под линиями

        Text(text = "Главное", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp))




            Box(
                modifier = Modifier

                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .size(55.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.Center), // Центрирование иконки в Box
                    painter = painterResource(id = R.drawable.bag_2),
                    contentDescription = "Filter Icon",
                    tint = Color.Black
                )
            }


    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    query: String,
    active: Boolean,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
) {

    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(0.7F)
            .background(
                color = Color.White, shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { /* Обработка поиска */ },
                active = active,
                onActiveChange = onActiveChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Поиск",
                            modifier = Modifier.alpha(0.8F),
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.grey)
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = colorResource(id = R.color.grey)
                    )
                },
                trailingIcon = {
                    if (active) {
                        IconButton(onClick = { onQueryChange(""); onActiveChange(false) }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear Query",
                                tint = Color.White
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent, // Прозрачный фон для SearchBar
                    inputFieldColors = SearchBarDefaults.inputFieldColors(
                        focusedTextColor = colorResource(id = R.color.black)
                    )
                )
            ){}
//            SearchBar(
//                query = query,
//                onQueryChange = onQueryChange,
//                onSearch = { /* Обработка поиска */ },
//                active = active,
//                onActiveChange = onActiveChange,
//                modifier = Modifier.weight(1f),
//                placeholder = { Text(text = "Поиск", modifier = Modifier.alpha(0.8F)) },
//                leadingIcon = {
//                    Icon(
//
//                        painterResource(id =R.drawable.vector),
//                        contentDescription = "Search Icon",
//                        tint = colorResource(id = R.color.grey)
//
//                    )
//                },
//                trailingIcon = {
//                    if (active) {
//                        IconButton(onClick = { onQueryChange(""); onActiveChange(false) }) {
//                            Icon(
//                                painterResource(id =R.drawable.vector),
//                                contentDescription = "Clear Query",
//                                tint = Color.White
//                            )
//                        }
//                    }
//                },
//                shape = RoundedCornerShape(10.dp),
//                colors = SearchBarDefaults.colors(
//                    containerColor = Color.Transparent, // Прозрачный фон для SearchBar
//                    inputFieldColors = SearchBarDefaults.inputFieldColors(
//                        focusedTextColor = colorResource(id = R.color.black)
//                    )
//                )
//            )
        }
    }
}

@Composable
fun FilterBox() {
    Row {


        Box(
            modifier = Modifier

                .background(
                    color = colorResource(id = R.color.light_blue),
                    shape = RoundedCornerShape(50.dp)
                )
                .size(60.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center), // Центрирование иконки в Box
                painter = painterResource(id = R.drawable.sliders),
                contentDescription = "Filter Icon",
                tint = Color.White
            )
        }
    }
}

@Composable
fun lazyCategory(){
    var selectedItem by remember { mutableStateOf<String?>(null) }

    val items = listOf("Все", "Outdoor", "Tennis", "Running")

    Column (modifier = Modifier.padding(20.dp)){
        Text(text = "Категории", fontSize = 20.sp, fontWeight = FontWeight.Bold )

    LazyRow(Modifier.fillMaxWidth()) {
        items(items) { item ->
            Text(
                text = item,
                fontSize = 16.sp,

                color = if (selectedItem == item) Color.White else Color.Black,

                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = if (selectedItem == item) colorResource(id = R.color.light_blue) else colorResource(
                            id = R.color.white
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedItem = item }
                    .padding(start = 48.dp, top = 14.dp, end = 48.dp, bottom = 15.dp)
            )
        }

    }
}
    
    
}


