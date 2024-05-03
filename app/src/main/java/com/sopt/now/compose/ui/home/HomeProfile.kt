package com.sopt.now.compose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.data.model.Profile

@Composable
fun HomeMyProfile(data: Profile) {
    Column (modifier = Modifier.background(Color.Gray)){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(data.profileImage),
                contentDescription = "profileImg",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = data.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = data.number,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HomeFriendsProfile(data: Profile) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(data.profileImage),
            contentDescription = "profileImg",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = data.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(50.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = data.number,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
fun PreviewMyProfile() {
    HomeMyProfile(data = Profile(R.drawable.img_profile, "명석", "enfp"))
}

@Preview
@Composable
fun PreviewFriendsProfile() {
    HomeFriendsProfile(data = Profile(R.drawable.img_profile, "의진", "CUTE"))
}