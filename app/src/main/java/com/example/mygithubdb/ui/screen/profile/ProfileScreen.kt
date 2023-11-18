package com.example.mygithubdb.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mygithubdb.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.fotokuh),
            contentDescription = stringResource(R.string.my_photo_profile),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .size(120.dp)
                .clip(CircleShape)
        )

        Text(
            text = stringResource(R.string.username, "fraamadhan"),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = stringResource(R.string.my_name),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = stringResource(R.string.my_email),
            style = MaterialTheme.typography.bodySmall.copy(
                fontStyle = FontStyle.Italic
            ),
        )
    }
}