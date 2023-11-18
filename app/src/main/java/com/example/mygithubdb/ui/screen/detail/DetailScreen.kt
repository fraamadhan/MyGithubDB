package com.example.mygithubdb.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mygithubdb.di.Injection
import com.example.mygithubdb.ui.ViewModelFactory
import com.example.mygithubdb.ui.components.DetailUserItem

@Composable
fun DetailScreen(
    username: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack:() -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit, block = {
        viewModel.getUser(username)
    })
    val githubUser by viewModel.githubUser.observeAsState()

    if(githubUser != null) {
        DetailUserItem(
            name = githubUser!!.name,
            username = githubUser!!.login,
            repo = githubUser!!.reposUrl,
            photoUrl = githubUser!!.avatarUrl,
            follower = githubUser!!.followers,
            following = githubUser!!.following,
            onBackClick = navigateBack,
        )
    }
}