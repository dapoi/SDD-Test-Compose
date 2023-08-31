package com.dapascript.sddtest.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dapascript.sddtest.R
import com.dapascript.sddtest.data.source.PromoResponse
import com.dapascript.sddtest.vo.Resource

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (PromoResponse) -> Unit
) {
    homeViewModel.getPromos.collectAsState().value.let {
        when (it) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.purple_500),
                    )
                }
            }

            is Resource.Success -> {
                HomeContent(
                    promo = it.data,
                    navigateToDetail = navigateToDetail
                )
            }

            is Resource.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.throwable.message.toString(),
                        color = colorResource(id = R.color.purple_500),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Promo App",
            color = colorResource(id = R.color.purple_500),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    promo: List<PromoResponse>,
    navigateToDetail: (PromoResponse) -> Unit
) {
    Column {
        AppBar(modifier = modifier.fillMaxWidth())
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(promo) { data ->
                HomeListItem(
                    promo = data,
                    onItemClick = {
                        navigateToDetail(data)
                    }
                )
            }
        }
    }
}

@Composable
fun HomeListItem(
    modifier: Modifier = Modifier,
    promo: PromoResponse,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = promo.nama.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = promo.desc.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Divider(modifier = Modifier.padding(top = 16.dp))
        }
    }
}