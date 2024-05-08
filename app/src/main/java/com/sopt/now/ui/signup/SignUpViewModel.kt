import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.model.User
import com.sopt.now.util.UiState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<UiState<User>>()

    fun signUp(request: RequestSignUpDto) {
        liveData.value = UiState.Loading

        authService.signUp(request).enqueue(object : Callback<BaseResponse<Unit>> {
            override fun onResponse(
                call: Call<BaseResponse<Unit>>,
                response: Response<BaseResponse<Unit>>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["Location"]
                    liveData.value = UiState.Success(request.toUserWithUserId(userId.toString()))
                } else {
                    val error = response.errorBody()?.string()
                    try {
                        val errorJson = JSONObject(error)
                        val errorMessage = errorJson.getString("message")
                        liveData.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        liveData.value = UiState.Error("회원가입 실패: 에러 메시지 파싱 실패")
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                liveData.value = UiState.Error("서버 에러")
            }
        })
    }
}
