import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.model.User
import com.sopt.now.domain.usecase.SignUpUseCase
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch

class SignUpViewModel(private val  signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState = _signUpState

    fun signUp(request: RequestSignUpDto) {
        _signUpState.value = UiState.Loading
        viewModelScope.launch {
            _signUpState.value = signUpUseCase.execute(request)
        }
    }
}
