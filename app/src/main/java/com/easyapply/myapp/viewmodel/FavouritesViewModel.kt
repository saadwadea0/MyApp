package com.easyapply.myapp.viewmodel

import androidx.lifecycle.ViewModel
import com.easyapply.myapp.domain.use_case.get_posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouriteUseCase: GetPostsUseCase
) : ViewModel() {
   /* val favourites: LiveData<List<Post>> = postRepository.getFavourites()

    fun addFavourite(post: Post) {
        viewModelScope.launch {
         //   postRepository.addFavourite(post)
        }
    }

    fun removeFavourite(post: Post) {
        viewModelScope.launch {
         //   postRepository.removeFavourite(post)
        }
    }*/
}