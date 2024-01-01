# TastoryApp
내배캠 맛집 공유 SNS 팀과제 레포지토리

# [**README]**

### 📌 와이어 프레임

![image](https://github.com/NBC-SNS-Team8/TastoryApp/assets/65105035/2881de49-4d5f-4bbe-a9e6-69f74b2cbfe4)


### 📌 **프로젝트 설명**

- 프로젝트 참여 인원 : 김나희, 최영정, 유정목, 이가현
- 프로젝트 명 : **Tastory =** taste + story
- 식당 리뷰를 중점으로 둔 SNS 서비스

### **📌 Preview**

- 영상

### **📌 구현 기능**

1. 메인화면(dialog 적용)
2. 디테일 화면
3. 로그인, 회원가입 화면(회원가입 페이지에서 예외처리, 유효성 체크)
4. 마이 페이지 화면
5. Activity 전환시 animation 구현
6. 영어 버전으로 변경 적용(string.xml)
7. 동그란 ImageView 만들기
8. 스크롤 기능 추가(메인, 디테일 화면)
9. 회원 정보 관리 구현(data class 활용) : 회원가입에서 적은 id와 이름의 데이터를 data class로 저장하고 그 데이터를 로그인 페이지, 메인 페이지, 디테일 페이지, 마이페이지에서 활용

### **📌 트러블 슈팅**

- 김나희
    1. 커스텀 다이얼로그의 background가 적용되지 않는 문제
    
     → 코드 상에서 `dialog.*window*?.setBackgroundDrawable(ColorDrawable(Color.*TRANSPARENT*))` 를 추가해줘야 background가 적용된다. 
    

- 이가현
    1. 액티비티 간에 이동하는 애니메이션 구현 시 안드로이드 스튜디오 애뮬레이터에서는 실행되지만 기기 연결했을 땐 실행되지 않는 문제
    
           → 기기 설정에 애니메이션 삭제가 체크되어있었다. 체크를 풀었더니 제대로 적용되었다.
    
    1. 레이아웃에 ScrollView를 입히면 위젯들이 각각 흩어지는 문제
    
           → ScrollView는 아래에 한 가지 레이어가 크게 감싸고 그 안에 여러 레이어들을 넣어야 한다. 
    
               LinearLayout으로 크게 감싸고 밑에 여러 ConstraintLayout로 흩어지는 위젯들을 묶어서 정렬했더
    
               니 흩어지지 않고 스크롤뷰가 잘 작동하였다.
    

- 최영정
    1. Git organization repository Push 오류
        
        permission denied fatal: unable to access 403 에러
        
        → organization collaborator 권한을 read에서 write로 바꾸면 해결
        
        뭔가 권한을 쓰기가능으로 하면 안정성? 보안상 문제는 괜찮은지 의문점
        
    2. imageView 가로세로 비율 맞추기
        
        높이를 match parent로 해서 전체 이미지가 줄어들긴 했는데 너비는 원래 이미지의 가로 길이 그대로라서 이미지 양 옆으로 여백 공간이 남는 view가 되었다. constraint로 왼쪽에 붙여도 view 내부 여백때문에 모서리에 딱 붙지 않았다
        
        → 너비는 그대로 wrapcontent로 두고 adjustViewBounds 속성을 true로 추가하면 바뀐 쪽 길이에 맞춰서 다른 쪽도 바뀐다. view 가로세로 내부 content에 맞게 일정 비율로 고정하는 속성?
        
    3. refactor - rename으로 id값을 바꿨을 때 has no declaration in the base values folder 빨간줄 에러 발생
        
        → 적용?이 아직 안 된 거니까 clean project나 restart project로 캐시 삭제하기
        
    4. by lazy로 지연초기화를 하고 사용하지 않은 변수가 있을 때 NullPoint에러 발생
                    
    5. 4번이랑 같이… by lazy로 선언한 변수들로 구성된 리스트 변수를 선언할 때 그냥 변수명 = listOf()해서 값을 집어넣었더니 변수 다 사용했어도 NullPointException 발생
        
        → 변수명 다음에 get() = listOf()로 쓰니까 에러 해결
        
        get()함수가 무슨 기능인지? 왜 이렇게 되는지는 추가로 찾아보기
        
- 유정목
    1. 동그란 ImageView를 만들시 de.hdodenhof:circleimageview:3.1.0이 파일을 import 로 맨위줄에 위치시켰는데 그게 아니라 라이블러리 인 build.gradle.kts(module:app)파일 안에 implementaion 안에 놓고 나머지 xml 작업을 진행하였더니 되었습니다.

- 앱 핵심 기능
    
    오직 식당 후기만을 위한 플랫폼
    
    게시물 작성으로는 자세한 후기를 쓸 수 있고 히스토리에는 간편하게 짧은 감상을 남길 수 있어 사용자 편의에 따라 사용 가능
    
    지역 식당들과의 제휴를 통한 할인 이벤트 등 다양한 혜택 제공
    
    간결하고 누구나 사용이 쉬운 UI
    
    프로젝트 설명
    
    Tastory는 Taste와 Story를 합친 것으로 식당 리뷰를 중점으로 둔 SNS 서비스입니다.
    
    사용자가 다녀온 식당의 후기를 세분화하여 기록할 수 있고 다른 사람들의 후기를 찾아볼 수 있습니다.
    
    가까운 사람들의 후기를 추천 피드에서 볼 수 있습니다.
