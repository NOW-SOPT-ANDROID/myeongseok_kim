import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.model.User
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState = _signUpState

    fun signUp(request: RequestSignUpDto) {
        _signUpState.value = UiState.Loading
        viewModelScope.launch {
            authRepository.signUp(request.toAuthRequestModel())
                .onSuccess { response ->
                    if (response.isSuccessful)
                        _signUpState.value =
                            UiState.Success(request.toUserWithUserId(response.headers()[LOCATION].toString()))
                    else {
                        val errorMessage =
                            JSONObject(response.errorBody()?.string()).getString(MESSAGE)
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

    companion object {
        const val MESSAGE = "message"
        const val LOCATION = "location"
    }
}
