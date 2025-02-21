package com.example.competproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nav()
        }
    }
}

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen() }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = {
                LinearOutSlowInEasing.transform(it)
            }
        ),
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2000)




        navController.navigate("main")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF48B2F7), Color(0xFF0076B1)
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.matule_text),
            contentDescription = null,
            alpha = alphaAnim.value,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.padding(top = 30.dp))
        ArrowBackBox()
        upperTexts()
        EnterData(context)
        buttonSignIn(context)
        BottomText(context)
        ButtonBack()
    }
}



@Composable
fun ArrowBackBox() {
    val context = LocalContext.current
    Row {
        Spacer(modifier = Modifier.padding(start = 16.dp))
        Box(
            modifier = Modifier
                .clickable {
                    (context as? Activity)?.finish()
                }
                .background(colorResource(id = R.color.light_greyy), shape = RoundedCornerShape(40.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = "IconBackStack")
        }
    }
}

@Composable
fun upperTexts() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Привет!", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Text(
            text = "Заполните Свои Данные Или \n Продолжите Через Социальные Медиа",
            fontSize = 16.sp,
            color = colorResource(id = R.color.grey),
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(lineHeight = 24.sp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterData(context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        Text(text = "Email", modifier = Modifier.padding(16.dp), fontSize = 16.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("xyz@gmail.com", color = colorResource(id = R.color.grey)) },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
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
                )
        )
        Text(text = "Пароль", modifier = Modifier.padding(16.dp), fontSize = 16.sp)
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Введите пароль", color = colorResource(id = R.color.grey)) },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
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
                    painterResource(id = R.drawable.unvisible)
                else
                    painterResource(id = R.drawable.eye_visibale)

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(painter = image, contentDescription = "Toggle password visibility", modifier = Modifier.size(25.dp))
                }
            }
        )
        Text(
            text = "Восстановить",
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, RecoverPassword::class.java)
                    context.startActivity(intent)
                }
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.grey),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun buttonSignIn(context: Context) {
    Spacer(modifier = Modifier.padding(top = 15.dp))
    Button(
        onClick = {
            val intent = Intent(context, MainMenu::class.java)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF48B2E7),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxHeight(0.23F)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = "Войти", fontSize = 14.sp)
    }
}

@Composable
fun BottomText(context: Context) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Вы впервые?",
            fontSize = 16.sp,
            color = Color(0xFF6A6A6A),
            modifier = Modifier
        )
        Text(
            text = "Создайте пользователя",
            fontSize = 16.sp,
            color = Color(0xFF000000),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 2.dp)
                .clickable {
                    val intent = Intent(context, CreateUser::class.java)
                    context.startActivity(intent)
                }
        )
    }
}
