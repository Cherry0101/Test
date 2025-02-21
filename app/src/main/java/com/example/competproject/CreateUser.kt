package com.example.competproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CreateUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenInCreate(context = this)
        }
    }
}


@Composable
fun MainScreenInCreate(context: Context) {
    Column {
        Spacer(modifier = Modifier.padding(top = 30.dp))
        ArrowBackBox()
        upperTextsInCreate()
        EnterDataInCreate(context)
        buttonSignInCreate(context)
        BottomTextInCreate(context)
        ButtonBack()
    }
}

//@Preview(showBackground = true)
@Composable
fun upperTextsInCreate(){
    Spacer(modifier = Modifier.padding(top = 30.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(text = "Регистрация!", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier)
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Text(text = "Заполните Свои Данные Или \n Продолжите Через Социальные Медиа",
            fontSize = 16.sp,
            color = colorResource(id = R.color.grey),
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 24.sp // Установите нужное значение для высоты строки
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun EnterDataInCreate(context: Context){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isChecked = remember { mutableStateOf(false) }

    Column {

        Text(text = "Ваше Имя", modifier = Modifier.padding(start= 16.dp, top = 16.dp),fontSize = 16.sp )

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("xxxxxxxx", color = colorResource(id = R.color.grey)) },
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    colorResource(id = R.color.light_grey),
                    shape = RoundedCornerShape(12.dp)
                ),
            // keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            //shape = MaterialTheme.shapes.extraSmall // Убирает границу
        )





        Text(text = "Email", modifier = Modifier.padding(start = 16.dp),
            fontSize = 16.sp )

        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("xyz@gmail.com", color = colorResource(id = R.color.grey)) },
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    colorResource(id = R.color.light_grey),
                    shape = RoundedCornerShape(12.dp)
                ),
            // keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            //shape = MaterialTheme.shapes.extraSmall // Убирает границу
        )
        Text(text = "Пароль", modifier = Modifier.padding(start = 16.dp),fontSize = 16.sp )

        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Введите пароль", color = colorResource(id = R.color.grey)) },
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    colorResource(id = R.color.light_grey),
                    shape = RoundedCornerShape(12.dp)
                ),

            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (isPasswordVisible)
                    painterResource(id = R.drawable.unvisible) // Иконка видимого глаза
                else
                    painterResource(id = R.drawable.eye_visibale) // Иконка перечеркнутого глаза

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(painter = image, contentDescription = "Toggle password visibility", modifier = Modifier.size(25.dp))
                }
            },
        )


        Row {



        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it},
            modifier = Modifier.padding(start = 16.dp)
        )



        Text(text = "Даю согласие на обратботку\nперсональных данных",
            color = Color(0xFF6A6A6A),
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()




        )
    }
//        Text(text = "Восстановить",
//            modifier = Modifier
//                .clickable {
//                    val intent = Intent(context, RecoverPassword::class.java)qqqqqq
//                    context.startActivity(intent)
//                }
//                .padding(16.dp)
//                .fillMaxWidth(),
//
//            fontSize = 12.sp,
//            color = colorResource(id = R.color.grey),
//            textAlign = TextAlign.End
//        )
    }
}

@Composable
fun buttonSignInCreate(context: Context) {
    Spacer(modifier = Modifier.padding(top = 15.dp))
    Button(
        onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF48B2E7), // Цвет фона кнопки
            contentColor = Color.White // Цвет текста
        ),
        shape = RoundedCornerShape(15.dp), // Закругленные углы
        modifier = Modifier
            .fillMaxHeight(0.32F)
            .fillMaxWidth() // Кнопка занимает всю доступную ширину
            .padding(horizontal = 16.dp, vertical = 8.dp) // Отступы
    ) {
        Text(
            text = "Зарегистрироваться",
            fontSize = 14.sp // Размер шрифта
        )
    }
}

@Composable
fun BottomTextInCreate(context: Context){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {

        Text(text = "Есть аккаунт?",
            fontSize = 16.sp,
            color = Color(0xFF6A6A6A),
            modifier = Modifier,
        )
        Text(text = "Войти",
            fontSize = 16.sp,
            color = Color(0xFF000000),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 2.dp)
                .clickable {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)

                },
        )
    }
}
@Composable
fun ButtonBack(){
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Box(modifier = Modifier, contentAlignment = Alignment.Center){
        IconButton(onClick = {onBackPressedDispatcher?.onBackPressed()}) {
            Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = "IconBackStack")
        }
    }
}