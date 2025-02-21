package com.example.competproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class RecoverPassword : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenInRecover()
        }
    }
}

@Composable
fun MainScreenInRecover(){
    Column {
        Spacer(modifier = Modifier.padding(top = 30.dp))
        ArrowBackBox()
        upperTextsinRecover()
        EnterDatainRecover()
        buttonSendInRecover()
    }
}

@Composable
fun upperTextsinRecover() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Забыл пароль", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Text(
            text = "Введите свою учетную запись \nДля сброса",
            fontSize = 16.sp,
            color = colorResource(id = R.color.grey),
            textAlign = TextAlign.Center,
            style = TextStyle(lineHeight = 24.sp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterDatainRecover() {
    var email by remember { mutableStateOf("") }

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
}

@Composable
fun buttonSendInRecover() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Spacer(modifier = Modifier.padding(top = 15.dp))

    Button(
        onClick = {
            showDialog = true
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF48B2E7), // Цвет фона кнопки
            contentColor = Color.White // Цвет текста
        ),
        shape = RoundedCornerShape(15.dp), // Закругленные углы
        modifier = Modifier
            .fillMaxHeight(0.12F)
            .fillMaxWidth() // Кнопка занимает всю доступную ширину
            .padding(horizontal = 16.dp, vertical = 8.dp) // Отступы
    ) {
        Text(
            text = "Отправить",
            fontSize = 14.sp // Размер шрифта
        )
    }

    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(horizontalArrangement = Arrangement.Center) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        colorResource(id = R.color.light_blue),
                                        shape = RoundedCornerShape(24.dp)
                                    )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.email_1),
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Text(
                            text = "Проверьте Ваш Email",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "Мы отправили код восстановления\nпароля на вашу электронную почту.",
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.greyy)
                        )
                    }
                }
            }
        }
    }
}

//    Button(
//        onClick = {
//            val intent = Intent(context, SecondActivity::class.java)
//            context.startActivity(intent)
//        },
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFF48B2E7), // Цвет фона кнопки
//            contentColor = Color.White // Цвет текста
//        ),
//        shape = RoundedCornerShape(15.dp), // Закругленные углы
//        modifier = Modifier
//            .clickable {
//                showDialog = true
//            }
//            .fillMaxHeight(0.12F)
//            .fillMaxWidth() // Кнопка занимает всю доступную ширину
//            .padding(horizontal = 16.dp, vertical = 8.dp) // Отступы
//    ) {
//        Text(
//            text = "Отправить",
//            fontSize = 14.sp // Размер шрифта
//        )
//    }

