import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.model.User
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.SERVER_ERROR
import com.sopt.now.util.StringNetworkError.SIGNUP
import com.sopt.now.util.UiState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState = _signUpState

    fun signUp(request: RequestSignUpDto) {
        _signUpState.value = UiState.Loading

        authService.signUp(request).enqueue(object : Callback<BaseResponse<Unit>> {
            override fun onResponse(
                call: Call<BaseResponse<Unit>>,
                response: Response<BaseResponse<Unit>>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["Location"]
                    _signUpState.value = UiState.Success(request.toUserWithUserId(userId.toString()))
                } else {
                    val error = response.errorBody()?.string()
                    try {
                        val errorJson = JSONObject(error)
                        val errorMessage = errorJson.getString("message")
                        _signUpState.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        _signUpState.value = UiState.Error(FAIL_ERROR.format(SIGNUP))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                _signUpState.value = UiState.Error(SERVER_ERROR)
            }
        })
    }

}
