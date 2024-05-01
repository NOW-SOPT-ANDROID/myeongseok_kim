import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.ServicePool
import com.sopt.now.data.User
import com.sopt.now.data.datasouce.RequestSignUpDto
import com.sopt.now.data.datasouce.ResponseSignUpDto
import com.sopt.now.util.UiState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<UiState<User>>()

    fun signUp(request: RequestSignUpDto) {
        liveData.value = UiState.Loading
        authService.signUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    liveData.value = UiState.Success(request.toUser())
                } else {
                    val error = validateSignUp(request)
                    liveData.value = UiState.Error(error)
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                liveData.value = UiState.Error("서버 에러")
            }
        })
    }

    private fun validateSignUp(request: RequestSignUpDto): String {
        if (!validateID(text = request.authenticationId)) return "아이디 형식이 맞지 않습니다."
        if (!validatePassword(text = request.password)) return "패스워드 형식이 맞지 않습니다."
        if (!validateNickName(text = request.nickname)) return "닉네임 형식이 맞지 않습니다."
        if (!validatePhone(text = request.phone)) return "전화번호 형식이 맞지 않습니다."
        return "Error"
    }

    private fun validateID(text: String): Boolean {
        return text.length in 6..10
    }

    private fun validatePassword(text: String): Boolean {
        val pattern = Regex("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$")
        return pattern.matches(text)
    }

    private fun validateNickName(text: String): Boolean {
        return text.isNotBlank()
    }

    private fun validatePhone(text: String): Boolean {
        val regex =
            "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$"
        return Pattern.compile(regex).matcher(text).matches()
    }
}
