import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.data.model.User
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState = _signUpState

    fun signUp(request: RequestSignUpDto) {
        _signUpState.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                authService.signUp(request)
            }.onSuccess { response ->
                if (response.isSuccessful)
                    _signUpState.value =
                        UiState.Success(request.toUserWithUserId(response.headers()["userid"].toString()))
                else {
                    val errorMessage =
                        JSONObject(response.errorBody()?.string()).getString("message")
                    _signUpState.value = UiState.Error(errorMessage.toString())
                }
            }.onFailure { e ->
                if (e is HttpException) {
                    _signUpState.value = UiState.Error(e.message())
                } else {
                    _signUpState.value = UiState.Error(e.message.toString())
                }
            }
        }
    }
}
