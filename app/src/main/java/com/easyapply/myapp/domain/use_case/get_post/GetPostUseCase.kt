package com.easyapply.myapp.domain.use_case.get_post

import com.easyapply.myapp.common.Resource
import com.easyapply.myapp.data.remote.dto.toPostDetail
import com.easyapply.myapp.domain.model.Post
import com.easyapply.myapp.domain.model.PostDetail
import com.easyapply.myapp.domain.repo.PostRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repo: PostRepo
) {

    //operator function is called automatically when I call GetPostUseCase
    operator fun invoke(postId:String): Flow<Resource<PostDetail>> = flow {
        try {
            emit(Resource.Loading())
            val post = repo.getPostDetailById(postId).toPostDetail()
            emit(Resource.Success(post))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            emit(Resource.Error("Couldnt reach the server. check your internet connection."))
        }
    }
}