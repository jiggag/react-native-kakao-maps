# react-native-starter

## feature/starter
- typescript
- kotlin
- swift
- eslint
- react-query
- react-navigation
- fastlane

## Firebase + Fastlane 앱 배포 준비
- Firebase 준비
  - 아래 경로에 파일 저장
    - `android/app/google-services.json`
    - `ios/RNStarter/GoogleService-Info.plist`
  - `iOS Development Provisioning` 발급
  - [문서: firebase + fastlane으로 배포하기](https://firebase.google.com/docs/app-distribution/android/distribute-fastlane?authuser=0)
  - [문서: firebase 로그인](https://firebase.google.com/docs/app-distribution/android/distribute-fastlane?authuser=0#google-acc-fastlane)
- 아래 내용의 환경변수 등록
  ```shell
  export FIREBASE_APP_ID_AOS="FIREBASE_APP_ID_AOS"
  export FIREBASE_APP_ID_IOS="FIREBASE_APP_ID_IOS"
  export FIREBASE_CLI_TOKEN="FIREBASE_CLI_TOKEN"
  export FIREBASE_TEST_GROUPS="FIREBASE_TEST_GROUPS"
  ```

## Actions 앱 배포 준비
- 깃헙 액션 시크릿키 설정
  - 공통
    - `FIREBASE_CLI_TOKEN`
    - `FIREBASE_TEST_GROUPS`
  - 안드로이드
    - `FIREBASE_APP_ID_AOS`
    - `GOOGLE_SERVICES_JSON`: `google-services.json`
  - iOS
    - `FIREBASE_APP_ID_IOS`
    - `GOOGLE_SERVICE_INFO_PLIST`: `GoogleService-Info.plist`
    - `BUILD_CERTIFICATE_BASE64`: 발급한 iOS Development 인증서에서 내보낸 p12의 base64
      - `base64 {파일} | pbcopy` => 복사해서 사용
    - `BUILD_PROVISION_PROFILE_BASE64`: 발급한 iOS Development Provisioning `*.mobileprovision`의 base64
    - `P12_PASSWORD`: p12 만들면서 사용한 패스워드
    - `KEYCHAIN_PASSWORD`: 임의의 값
  

------
## feature/heavy-starter
- [jiggag/react-native-**heavy**-starter](https://github.com/jiggag/react-native-starter/tree/feature/heavy-starter)
  - typescript
  - kotlin
  - swift
  - react-navigation
  - styled-components
  - redux
  - redux-saga
  - react-query
  - fastlane
  - bugsnag
  - codepush 
  - stylelint 
  - eslint
