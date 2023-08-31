package com.dapascript.sddtest.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dapascript.sddtest.data.source.PromoResponse

@Composable
fun DetailScreen(
    promo: PromoResponse
) {
    DetailContent(promo = promo)
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    promo: PromoResponse
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(promo.img?.formats?.medium?.url)
                .crossfade(true)
                .build(),
            contentDescription = "Image"
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = promo.nama.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = promo.desc.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}