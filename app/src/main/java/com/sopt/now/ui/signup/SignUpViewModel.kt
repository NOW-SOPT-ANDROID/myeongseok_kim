import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ui.model.User
import com.sopt.now.domain.entity.request.AuthRequestModel
import com.sopt.now.domain.usecase.SignUpUseCase
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState = _signUpState

    fun signUp(request: AuthRequestModel) {
        _signUpState.value = UiState.Loading
        viewModelScope.launch {
            signUpUseCase(request)
                .onSuccess { response ->
                    if (response.isSuccessful) {
                        _signUpState.value =
                            UiState.Success(
                                User(
                                    request.authenticationId,
                                    request.password,
                                    request.nickname,
                                    request.phone,
                                    response.headers()[LOCATION].toString()
                                )
                            )
                    } else {
                        val errorMessage =
                            JSONObject(response.errorBody()?.string()).getString(MESSAGE)
                        _signUpState.value = UiState.Error(errorMessage.toString())
                    }
                }
                .onFailure { e ->
                    if (e is HttpException) {
                        _signUpState.value = UiState.Error(e.message())
                    } else {
                        _signUpState.value = UiState.Error(e.message.toString())
                    }
                }
        }
    }

    companion object {
        private const val LOCATION = "location"
        private const val MESSAGE = "message"
    }
}
