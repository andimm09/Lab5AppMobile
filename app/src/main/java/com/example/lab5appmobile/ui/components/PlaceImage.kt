package com.example.lab5appmobile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.DownhillSkiing
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab5appmobile.data.Category
import com.example.lab5appmobile.data.Place
import com.example.lab5appmobile.ui.theme.accent

fun Category.icon(): ImageVector = when (this) {
    Category.SPORT -> Icons.Filled.DownhillSkiing
    Category.MUZEE -> Icons.Filled.AccountBalance
    Category.RESTAURANTE -> Icons.Filled.Restaurant
}

/** Fotografia locului sau, dacă lipsește, un gradient cu iconița categoriei. */
@Composable
fun PlaceImage(
    place: Place,
    modifier: Modifier = Modifier,
    iconSize: Dp = 56.dp,
) {
    if (place.imageRes != null) {
        Image(
            painter = painterResource(place.imageRes),
            contentDescription = place.name,
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    } else {
        val accent = place.category.accent()
        Box(
            modifier = modifier.background(
                Brush.linearGradient(listOf(accent, accent.copy(alpha = 0.55f))),
            ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                place.category.icon(),
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.size(iconSize),
            )
        }
    }
}

/** Etichetă mică, colorată după categorie. */
@Composable
fun CategoryBadge(category: Category, modifier: Modifier = Modifier) {
    Text(
        text = category.label.uppercase(),
        color = Color.White,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(category.accent())
            .padding(horizontal = 10.dp, vertical = 4.dp),
    )
}

/** Gradient închis la baza unei imagini, ca textul alb de deasupra să fie lizibil. */
val ScrimBrush = Brush.verticalGradient(
    0f to Color.Transparent,
    0.45f to Color.Transparent,
    1f to Color.Black.copy(alpha = 0.75f),
)
