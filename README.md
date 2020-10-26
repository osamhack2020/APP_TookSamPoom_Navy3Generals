# 특삼품

<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/TSPLOGO.png width="400">

## 제품 소개

**특삼품**은 **특급을 세개 받은 프리미엄 전사**라는 뜻으로 짓게 되었습니다.
특삼품은 **TensorFlow** 기반의 체력검정 측정앱입니다. 대한민국 군인이라면 일년에 한번씩 체력검정을 하게되어 있으며 우수한 성적을 거두어 특급전사가 될시 포상도 주어집니다! 하지만 채점자에 따라 체점기준이 판이하며 체력검정이 어려워 지기도 쉬워 지기도 하는것이 현실입니다. 저희는 체력검정 과정을 좀더 **객관적**으로 진행하고 **간소화**시키며 또한 수검자들이 미리 자신의 체력을  테스트 해 볼수 있도록 앱을 개발하게 되었습니다!


## 설치방법(Install)

[apk파일 다운로드](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/tree/master/app/release)
위 하이퍼링크에서 apk파일을 다운로드 받을 수 있습니다.

## 사용방법(How to use)

## 개발과정 
저희 팀이 **특삼품**을 개발하는 단계에서 필요로 하는 작업들을 정리해보았습니다.

### Model 개발
체력검정 측정하는데에 있어 제일 기본이 되는 **이미지 분석 Model** 입니다.
스마트폰에 있는 카메라 기능을 이용하여 수검자들의 신체를 AI MODEL이 인식한 후 수검자들의 훈련중 자세를 측정하고 측정한 자세를 기반으로 운동 횟수를 객관적으로 측정하는 시스템입니다.  
저희 이미지 분석 기반 모델은 크게 두 부분으로 구성되어 있습니다. 
1. [Google](https://www.google.com/)에서 제공하는 오픈 소스형 ML API인 [ML Kit Pose Detection](https://developers.google.com/ml-kit/vision/pose-detection)
ML Kit Pose Detection은 사람의 신체구조를 좌표로 리턴해주는 API입니다. 
2. 저희가 독자적으로 개발한 TSP image classifier 입니다.
저희 **특삼품**은 팔굽혀펴기와 윗몸일으키기를 측정하기 위해 다양한 샘플을 다양한 각도에서 촬영한 데이터를 가지고 AI Modeling을 하였습니다.  
AI Modeling중 사용된 동영상을 프레임단위로 나눠지게 하여 샘플이 팔굽혀펴기와 윗몸일으키기 도중 어떠한 상태를 취하고 있는지를 분석하여 팔굽혀펴기와 윗몸일으키기마다 약 4만장의 이미지를 분석하여 AI Modeling을 하였고 **시작자세(Stand)**, **움직임자세(Move)**, **끝자세(Down)** 를 트레이닝해 특정 자세패턴을 통과시 갯수를 카운트 하는 모델입니다.  
#### 예제(Example)

![classifier](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/classfier.JPG)  

![pushupb](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/Pushupb.JPG)  
![situpb](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/Situpb.JPG)  




### App 개발

App은 디자인, 편의성, 다양한 기능 등 여러가지 기술들을 개발하여 사용자가 사용할때 보다 쉽게 App에 접근할 수 있도록 개발하였습니다.
App은 크게 다양한 운동 종목을 측정할 수 있는 측정 엑티비티, 사용자가 측정한 기록을 시간 순서에 따라 보여주는 그래프 엑티비티, 그리고 사용자의 프로필 및 사용자의 최대 능력을 보여주는 프로필 엑티비티로 구성되어 있습니다. 


### Server 개발

Server는 Node.js 기반으로 SQLite를 활용하여 데이터를 저장하도록 디자인 되었습니다. Server는 각종 데이터의 축적 및 분석을 실행하는 통합 정보 페이지를 제공합니다. 시스템 어드민은 통합 정보 페이지를 통하여 각 사용자의 데이터 및 통계 데이터를 실시간으로 확인하고 변경할 수 있습니다. 통합 정보 페이지에서는 현제 군의 평균 체력 상태뿐만 아니라 최근 1년간의 체력 측정 정보, 각 부대별 체력 측정 현황등을 확인할 수 있습니다.

## 소프트웨어 구성 및 시스템 요구사항

- Android API level 26 이상 (필수)
- Qualcomm®5 SnapdragonTM 730G 이상 <~30FPS 이미지 분석>(권장)

## 기술 스택 (Technique Used)

|Server | | |
|:----------:|:----------:|:----------:|
| front | | |
| back | | |  
| Other | | |  

| Android | | |
|:----------:|:----------:|:----------:|
| API | CameraX |  |
| Model | ML Kit Pose Detection |  |  
| Other | | |  

## 확장성

## 팀 소개

팀장: 김기태(https://github.com/gth1030)
- UC Berkeley, Bioinformatics 학부

팀원: 정민석(https://github.com/minseok4787)
- UNIST, Life Science 학부

팀원: 김민철(https://github.com/uselessArkness)
- 경성대학교, 정보통신공학과

## 버젼목표

### v0.1
- 윗몸 일으키기 모델 계발
- Main Activity에 그래프 형식의 유저 결과표 추가
- Developer 모드 추가
- 히스토리 그래프 추가

### v0.2
- 달리기 측정모드 서비스 형식으로 전환
- 측정모드 화면회전 한가지로 고정

## 저작권 및 사용권 정보
 * [MIT](https://github.com/osam2020-WEB/Sample-ProjectName-TeamName/blob/master/license.md)
 * [ML Kit Pose Detection](https://developers.google.com/ml-kit/vision/pose-detection)
