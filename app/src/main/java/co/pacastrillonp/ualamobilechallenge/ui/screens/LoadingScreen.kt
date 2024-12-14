package co.pacastrillonp.ualamobilechallenge.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.pacastrillonp.ualamobilechallenge.ui.theme.accentColor
import co.pacastrillonp.ualamobilechallenge.ui.theme.primaryColor

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = primaryColor,
            trackColor = accentColor,
            strokeWidth = 8.dp
        )
    }
}