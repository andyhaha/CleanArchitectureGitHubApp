//package com.github.app.navigation
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavType
//import androidx.navigation.compose.*
//import androidx.navigation.navArgument
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApp()
//        }
//    }
//}
//
//@Composable
//fun MyApp() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "home"
//    ) {
//        // 定义 HomeScreen
//        composable("home") {
//            HomeScreen(navController = navController)
//        }
//
//        // 定义 DetailScreen，并接收参数
//        composable(
//            route = "detail/{message}",
//            arguments = listOf(navArgument("message") {
//                type = NavType.StringType
//            })
//        ) { backStackEntry ->
//            // 获取传递的参数
//            val message = backStackEntry.arguments?.getString("message")
//            DetailScreen(navController = navController, message = message)
//        }
//    }
//}
//
//@Composable
//fun HomeScreen(navController: NavController) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Button(onClick = {
//            // 点击时导航到 DetailScreen，并传递参数
//            navController.navigate("detail/Hello from HomeScreen")
//        }) {
//            Text(text = "Go to DetailScreen")
//        }
//    }
//}
//
////@Composable
////fun DetailScreen(navController: NavController, message: String?) {
////    Box(
////        modifier = Modifier.fillMaxSize(),
////        contentAlignment = Alignment.Center
////    ) {
////        Column(horizontalAlignment = Alignment.CenterHorizontally) {
////            Text(text = "DetailScreen")
////            Text(text = "Message: $message")
////
////            Spacer(modifier = Modifier.height(16.dp))
////
////            Button(onClick = {
////                navController.popBackStack() // 返回 HomeScreen
////            }) {
////                Text(text = "Go back")
////            }
////        }
////    }
////}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMyApp() {
//    MyApp()
//}
