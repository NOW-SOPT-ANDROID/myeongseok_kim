import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ui.model.User
import com.sopt.now.domain.entity.AuthRequestModel
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
                    _signUpState.value =
                        UiState.Success(
                            User(
                                request.authenticationId,
                                request.password,
                                request.nickname,
                                request.phone,
                            )
                        )
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
}
