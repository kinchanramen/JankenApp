package com.wakosen.janken.ui.theme.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.wakosen.janken.R
import com.wakosen.janken.ui.home.HandType

@Composable
fun ResultScreen(nav:NavController,handType: HandType){
    val myHand=handType
    val handRange=(0 until HandType.values().size)
    val randomNumInHandType=handRange.random()
    val enemyHand=HandType.values()[randomNumInHandType]
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 64.dp)
    ) {
        Text(text = "相手", fontSize = 24.sp)
        Box(modifier = Modifier.weight(1f)){
            Enemy(enemyHand)
        }
        Spacer(modifier = Modifier.height(96.dp))
        ResultText(myHand,enemyHand)
        Box(modifier = Modifier.weight(1f)){
            HandImage(type=myHand)
        }
        Text(text = "自分", fontSize = 24.sp)
        Button(onClick = {nav.popBackStack()}) {
            Text(text = "戻る", fontSize = 24.sp)
        }
    }
}
@Composable
fun Enemy(handType: HandType){
    Box(modifier = Modifier.fillMaxSize()){
        AsyncImage(model = "https://2.bp.blogspot.com/-ZwYKR5Zu28s/U6Qo2qAjsqI/AAAAAAAAhkM/HkbDZEJwvPs/s400/omocha_robot.png", contentDescription = null, modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit)
        Box(modifier = Modifier
            .offset(x = 240.dp, y = 64.dp)
            .size(64.dp)){
            HandImage(handType)
        }
    }
}
@Composable
fun ResultText(myHandType: HandType,enemyHandType: HandType){
    val result=when(enemyHandType.ordinal-myHandType.ordinal){
        1,-2->"かち"
        0->"あいこ"
        -1,2->"まけ"
        else->"あいこ"
    }
    Text(text = result, fontSize = 32.sp, fontWeight = FontWeight.Bold)
}
@Composable
fun HandImage(type: HandType){
    val imageRes:Int=when(type){
        HandType.SCISSORS-> R.drawable.janken_choki
        HandType.ROCK->R.drawable.janken_gu
        HandType.PAPER->R.drawable.janken_pa
    }
    Image(painter = painterResource(id = imageRes), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize())
}
@Preview
@Composable
fun ResultPreview(){
    ResultScreen(rememberNavController(),HandType.ROCK)
}