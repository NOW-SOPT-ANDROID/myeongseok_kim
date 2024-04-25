# Now Sopt Android

## ✏️ Work Description
### 로그인화면
- [x]  ID,Password 입력 
    - [x] 검증단계

### 회원가입 - SignUpActivity
- [x] ID, Password, 
    - MBTI
    - 거주지
    - 주량
    - 등등
- [x] 예외처리
    - [x]  ID : 6~10 글자
   - [x] Password : 8~12 글자
   - [x] 닉네임 : 한 글자 이상, 공백으로만 이루어진 닉네임은 불가

### 메인화면
- [x]  UI
- [x] 정보 불러와 표시


UIstate -https://hyeonlog-developer.tistory.com/131
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
기능 - 다시 못돌아오게함 
현재 task 에 있는 모든 액티비티를 종료하고 새로 시작하는 엑티비티를 task 를 할당함으로 써 다시 못돌아오게함
앱 로그인 이후에 메인 화면으로 이동 하기 위해 사용함