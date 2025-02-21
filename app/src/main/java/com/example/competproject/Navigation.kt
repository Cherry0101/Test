//package com.example.competproject
//
//import android.window.SplashScreen
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun MainNav(){
//    val navigator = rememberNavController()
//
//    val bagAnimatedFragment = "started_fragment"
//    val firstOnBoardFragment = "on_board_one"
//    val secondOnBoardFragment = "on_board_two"
//    val threeOnBoardFragment = "on_board_three"
//    val hiFragment = "hi_fragment"
//    val regScreen = "reg_fragment"
//    val recoverPass = "reset_pass_fragment"
//    val OTPpass = "otpPass"
//
//    NavHost(navController = navigator,
//        startDestination = bagAnimatedFragment,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        composable(bagAnimatedFragment) {
//            StartedAnimationScreen(
//                navigateToFirstFragment = {navigator.navigate(hiFragment)}
//            )
//        }
//
//        composable(hiFragment){
//            BottomText(
//                navigateToSecond = {navigator.navigate(regScreen)},
//
//            )
//        }
//
//        composable(hiFragment){
//            BottomText(
//                navigateToRecover = {navigator.navigate(recoverPass)},
//                )
//        }
//
//
//
//        composable(regScreen){
//            SecondScreenRegistration(
//                navigateToFirst = {navigator.navigate(hiFragment)}
//            )
//
//        }
//        composable(passRelog) {
//            ResetPassMain(
//                navigateOnOTP = {navigator.navigate(OTPpass)}
//            )
//
//        }
//        composable(OTPpass) {
//            OTPScreen(
//
//            )
//        }
//        composable(firstOnBoardFragment) {
//            FirstOnBoardScreen(
//                navigateToSecondBoardScreen = {navigator.navigate(secondOnBoardFragment)}
//            )
//        }
//        composable(secondOnBoardFragment) {
//            SecondOnBoardScreen(
//                navigateToThreeBoardScreen = {navigator.navigate(threeOnBoardFragment)}
//            )
//        }
//        composable(threeOnBoardFragment) {
//            ThreeOnBoardScreen()
//        }
//    }
//}