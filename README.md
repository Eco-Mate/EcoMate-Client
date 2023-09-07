# EcoMate-Client
지구를 위한 챌린지 달성 프로젝트 `EcoMate`의 클라이언트 Repository 입니다.
## 소개
> **에코메이트**와 함꼐하는 슬기로운 에코생활

에코메이트는 일상 속에서 작은 변화들을 이끌어내어 환경 보호로의 큰 변화를 만들어 나갈 수 있는 플랫폼을 형성하고자 출시한 서비스입니다.
에코메이트에서는 일상에서 가볍게 참여할 수 있는 챌린지를 도전하고, 그 과정을 공유하면서 자연스럽게 지속 가능한 삶의 환경을 조성할 수 있습니다. 더불어, 신선한 에코 정보 및 에코 매장을 제시하여 친환경 경제를 촉진하고 환경 친화적인 선택에 도움을 줄 수 있습니다.

## 기술 스택

<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=for-the-badge&logo=Android Studio&logoColor=white"> <img src="https://img.shields.io/badge/Retrofit-D20A0A?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/OkHttp-2C5BB4?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/Glide-007054?style=for-the-badge&logoColor=white">

## 시스템 아키텍쳐
![Ecomate_SystemArchi_Client Server](https://github.com/Eco-Mate/.github/assets/75007765/6d28c739-8457-48ca-82f6-7edf9b11c09e)

## 사용 방법

### Git Clone 실행
```shell
$ git clone https://github.com/Eco-Mate/EcoMate-Client.git
```

### Kakao 지도 설정

#### 프로젝트의 디버그 키 해시값 찾기
- MainActivity.kt의 클래스에 아래 코드 추가 (없으면)
- Logcat에서 KeyHash를 입력하고 프로젝트의 디버그 키 해시값 찾기
- 찾은 해시값을 Contributor에게 전달 및 Kakao Developers에 해시값 등록 요청
```kotlin
private fun getHashKey() {
    var packageInfo: PackageInfo? = null
    try {
        packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (packageInfo == null)
        Log.e("KeyHash", "KeyHash:null")
    for (signature: Signature in packageInfo!!.signatures) {
        try {
            var md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        } catch (e: NoSuchAlgorithmException) {
            Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e)
        }
    }
}
```
### FCM 적용 방법
- [Firebase Console](https://console.firebase.google.com/)에 접속하여 프로젝트를 추가한다.
- 추가한 프로젝트에 나오는 google-services.json파일을 app모듈에 추가한다.

## Contributors
|Name|Github|Email|
|:---|:---|:---|
|이현섭|[leehandsub](https://github.com/leehandsub)|gustjq@naver.com|
|허진수|[HJTN](https://github.com/HJTN)|zintn1234@naver.com|
