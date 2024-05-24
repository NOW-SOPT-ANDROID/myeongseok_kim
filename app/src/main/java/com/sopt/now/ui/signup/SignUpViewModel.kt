import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.model.User
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.SERVER_ERROR
import com.sopt.now.util.StringNetworkError.SIGNUP
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState = _signUpState

    fun signUp(request: RequestSignUpDto) {
        _signUpState.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                authService.signUp(request)
            }.onSuccess {
                _signUpState.value =
                    UiState.Success(request.toUserWithUserId(it.headers()["userid"].toString()))
            }.onFailure { e ->
                if (e is HttpException) {
                    val errorMessage =
                        JSONObject(e.response()?.errorBody()?.string()).getString("message")
                    _signUpState.value = UiState.Error(errorMessage)
                } else {
                    _signUpState.value = UiState.Error("코드 똑바로 짜라.. ")
                }

            }
        }
    }
}
