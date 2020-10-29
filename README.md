# 특삼품 (국군 체력 통합관리 시스템)

<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/TSPLOGO.png width="400">

## 제품 소개(Product Description)

**특삼품**은 **특급을 세개 받은 프리미엄 전사**라는 뜻으로 짓게 되었으며 국군 체력 통합관리 시스템 입니다. 
특삼품은 크게 두 파트로 구성되어 있어 **TensorFlow** 기반의 체력검정 자동측정앱과 전군 통합 체력관리 웹시스템으로 구성되어 있습니다.
대한민국 군인이라면 일년에 한번씩 체력검정을 하게되어 있으며 우수한 성적을 거두어 특급전사가 될시 포상도 주어집니다!  
하지만 채점자에 따라 체점기준이 판이하며 체력검정이 어려워 지기도 쉬워 지기도 하는것이 현실입니다.  
저희는 체력검정 과정을 좀더 **객관적**으로 진행하고 **간소화**시키며 또한 수검자들이 미리 자신의 체력을  테스트 해 볼수 있도록 앱을 개발하게 되었습니다!  
기본적으로 앱을 사용하는 사용자 입장에서는 **특삼품**이 개개인의 체력검정 측정에도 도움이 되고 사용자가 체력검정뿐만 아니라 개인적으로 체력증진에도 힘을 얻고 싶다고 생각할때에도 이 앱을 통해
체력증진을 할 수 있도록 앱에 사용자가 그날 운동했던 운동량을 기록할 수 있게 되어있고 기록한 운동량을 보기쉽게 차트, 그래프 등으로 표현하여 나타내고 있습니다.  
또한 요즘 20~30대 층에서 유행하는 게임들의 공통점인 경쟁구도를 통한 자신의 계급을 나타내고자 현재 자신이 앱 사용자들중에서 어떤 수준에 머물고 있는지를 재미있게 표현하여 조금 더 체력검정에 열정을 쏟을 수 있게 만들었습니다.  
그리고 사용자 뿐만 아니라 체력검정을 관리하는 관리자 입장에서도 유용한 앱입니다.   
측정을 할때마다 여러 사람이 붙어서 일일이 측정하고 그 기록을 수기로 작성하여 인트라넷에 입력하게 되면 귀찮음도 있을 뿐 더러 시간이 매우 많이 소모되고 그렇게 되면 효율성이 떨어지게 됩니다.  
![adminPage](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/Tsp_adminPage.png)

위의 피드백을 반영하여 시간을 극도로 단축시키며 정확하고 객관적으로 기록 할 수 있게 하기 위해 []기반으로 웹 서버 데이터베이스를 구축하여 사용자들의 기록을 관리자들이 한눈에 볼 수 있게 만들게 되었습니다.  
이러한 특성은 관리자가 좁게는 개개인의 사용자의 체력검정능력을 넓게는 각 부대 단위의 체력을 측정하고 관리할 수 있게됩니다.
또한 이런 서비스를 구축하게 된다면 추후에 체력검정 정책이 개편될 경우에도 유용하게 사용할 수 있습니다.  
예를 들어 체력검정의 필요성이 요구되어 1년에 2번 측정하게 될 경우에도 힘을 들이지 않고 체력검정을 쉽게 볼 수 있고 정책개편이 올바른지에 대해서도 객관적으로 볼 수 있습니다.
그리고 체력검정을 실시하여 전년대비 성과가 출중했을 경우 휴가나 양호보고 제도를 개편했다 했을때도 마찬가지로 사용자들에게 이 제도가 얼마나 영향을 미쳤는지에 대해 분석할 수 있고 체력검정의 중요성을 느끼지 못하는 병사들에게도 충분히 동기부여를 할 수 있게 만드는 순기능중에 하나가 됩니다.  
이처럼 체력검정을 봐야하는 사용자 입장, 체력검정을 측정해야하는 관리자 입장에서도 모두 유용하게 사용할 수 있는 시스템입니다.

## 설치방법(Install)

[apk파일 다운로드](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/tree/master/app/release)
위 하이퍼링크에서 apk파일을 다운로드 받을 수 있습니다.

## 사용방법(How to use)

## 개발과정(Development Process)
저희 팀의 **특삼품**의 작동과정을 정리해보았습니다.

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

![classifier](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/classifier.JPG)  
![pushupb](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/Pushupb.JPG)  
![situpb](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/Situpb.JPG)  

이외에도 체력측정의 정확성과 효율성을 높이기 위해 저희 팀은 모델의 **정규화**와 **경량화** 를 하였습니다.  

![nomalization](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/nomalization.JPG)
![WeightL](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/Markdown/sampleImage/WeightL.JPG)  

기존 모델은 서버에서 이용하여 데이터를 측정하고 하였으나 모델의 정규화와 경량화를 거친후에는 앱 자체에서도 로컬머신으로 측정이 가능하게 하여 서버와 통신없이 자체적으로 측정할 수 있게 되었습니다.

### App 개발

App은 디자인, 편의성, 다양한 기능 등 여러가지 기술들을 개발하여 사용자가 사용할때 보다 쉽게 App에 접근할 수 있도록 개발하였습니다.
App은 크게 다양한 운동 종목을 측정할 수 있는 측정 엑티비티, 사용자가 측정한 기록을 시간 순서에 따라 보여주는 그래프 엑티비티, 그리고 사용자의 프로필 및 사용자의 최대 능력을 보여주는 프로필 엑티비티로 구성되어 있습니다. 


### Server 개발

Server는 Node.js 기반으로 SQLite를 활용하여 데이터를 저장하도록 디자인 되었습니다. Server는 각종 데이터의 축적 및 분석을 실행하는 통합 정보 페이지를 제공합니다. 시스템 어드민은 통합 정보 페이지를 통하여 각 사용자의 데이터 및 통계 데이터를 실시간으로 확인하고 변경할 수 있습니다. 통합 정보 페이지에서는 현제 군의 평균 체력 상태뿐만 아니라 최근 1년간의 체력 측정 정보, 각 부대별 체력 측정 현황등을 확인할 수 있습니다.

## 소프트웨어 구성 및 시스템 요구사항

- Android API level 26 이상 (필수)
- Qualcomm®5 SnapdragonTM 730G 이상 <~30FPS 이미지 분석>(권장)

## 다른 체력 측정앱 대비 장점

특산품은 제시된 다른 체력 검정 시스템과 비교하여 몇 가지 큰 장점을 가지고 있습니다.
1. 별도의 하드웨어를 필요로 하지 않습니다. 병사들이 모두 개인적으로 소지하고 있는 휴대폰으로 간단히 사용이 가능하며 별도의 IOT 기기를 필요로 하지 않아 비용이 절감됩니다.
2. 다른 open-source 프로젝트를 기반으로 시스템을 구축하였습니다. ML kit, nodeJs 등 여러가지 오픈소스 프로젝트를 기반으로 시스템을 구축하여 다른 오픈소스 시스템이 개선됨에 따라 특산품의 정밀도, 정확도 및 유용성도 함께 확장되며 함께 커뮤니티를 확장 시킬수 있습니다.
3. 수검자의 부정행위를 탐지하기 쉽습니다. 근접센서, 중량센서등의 각종 센서 기반의 시스템은 사용자의 부적절한 자세나 무릎이 땅에 닫는 등의 탈락 행위 또는 대리시험등의 부정행위를 판단하는데 큰 제약이 있습니다. 하지만 특삼품은 Vision 기반의 이미지 분석을 기본으로 하여 신원확인 및 부적절한 자세 및 부정행위를 손쉽게 탐지하고 방지할 수 있습니다.
4. 넓은 확장성으로 다른 운동의 손쉽게 추가할 수 있습니다. 특산품을 구축하는 시스템은 아주 높은 정확도를 가지고 있지만 위에서 보여지듯 간단한 시스템 설계를 가지고 있습니다. 앱 내에 위치한 이미지 분석 시스템을 통해 약 10시간 정도의 이미지 분류작업만 한다면 새로운 운동을 손쉽게 추가할 수 있습니다.

## 기술 스택 (Technique Used)

|Server | | |
|:----------:|:----------:|:----------:|
| front | html | |
| back | node.js | |  
| Other | sqlite | |  

| Android | Android Studio 4.1 |  |
|:----------:|:----------:|:----------:|
| API | CameraX 1.0.0-beta08<br>ViewPager2 1.0.0 |  |
| Model | ML Kit Pose Detection 16.0.0 |  |  
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
