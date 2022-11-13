package com.example.bb.frontend

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay



@Composable
fun AnimatedSplashBackGround(navController: NavHostController){
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(0) // TO DECREASE TIME HERE
        navController.popBackStack()
        navController.navigate(NavigationItem.Budget.route)
    }
    SplashScreenAnim(alphaAnim.value)


}



@Composable
fun SplashScreenAnim(alpha:Float){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0.1f to Color(0xFF006A43),
                    0.7f to Color(0xFF00C77A),
                    0.7f to Color(0xFF00C77A),
                    1.0f to Color(0xFF040706),

                    )
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "QQQ",
            modifier = Modifier.alpha(alpha),
            color = Color.White,
            style =
            MaterialTheme.typography.h1,
            fontWeight = FontWeight.ExtraBold)
    }


}