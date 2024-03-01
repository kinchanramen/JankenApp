package com.wakosen.janken.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wakosen.janken.R


enum class HandType {
    ROCK,
    SCISSORS,
    PAPER,
}
@Composable
fun HomeScreen(nav: NavController) {
    fun onSelectHand(type: HandType): Unit {
        nav.navigate("RESULT/${type.ordinal}")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "じゃんけん",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(64.dp))
        HandSelector(onSelectHand = {hand -> onSelectHand(hand)})
    }
}

@Composable
fun HandSelector(onSelectHand: (HandType) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HandType.values().forEach { hand ->
            Box(modifier = Modifier.weight(1f)) {
                Hand(type = hand) {
                    onSelectHand(hand)
                }
            }
        }
    }
}

@Composable
fun Hand(type: HandType, onTap: () -> Unit) {
    val imageRes: Int = when(type) {
        HandType.ROCK -> R.drawable.janken_gu
        HandType.SCISSORS -> R.drawable.janken_choki
        HandType.PAPER -> R.drawable.janken_pa
    }
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier.clickable { onTap() }
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(rememberNavController())
}