# 특삼품 (국군 체력 통합관리 시스템)

<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/1604101210360.jpg width="400">

## 제품 소개(Product Description)

**특삼품**은 **특급을 세개 받은 프리미엄 전사**라는 뜻으로 짓게 되었으며 국군 체력 통합관리 시스템 입니다. 
특삼품은 크게 두 파트로 구성되어 있어 **TensorFlow** 기반의 체력검정 자동측정앱과 전군 통합 체력관리 웹시스템으로 구성되어 있습니다.  
대한민국 군인이라면 매년 실시해야 하는 덕목중 하나가 바로 체력검정입니다. 체력은 국력이라는 말이 있듯이 군인에게 체력은 임무를 수행함에 있어 가장 중요한 덕목중에 하나입니다. 다만 현재 실시되는 아날로그 방식의 체력검정 시스템은 객관성의 부재, 아날로그 방식에서 발생하는 갖가지 휴먼에러, 많은 인력 동원의 필요성, 그리고 자율적인 체력검정 실시의 불가능 등 여러가지 단점을 가지고 있습니다. 저희는 이러한 단점을 극복하고 군에 효율적인 장병 체력관리 시스템을 제공하고자 하여 프로젝트를 시작하게 되었습니다. 

<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/Tsproute.png width="1200">

___
### TSP Web Solution

통합 체력관리 페이지는 크게 3가지 구성요소로 이루어져 있습니다.

각 군의 최근 한달동안 이루어진 체력검정 결과의 평균값을 레이더 차트 형식으로 비교 분석하는 그래프
![adminOverall](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/Tsp_admin_overall.png)

각 군의 매달 실시되는 체력검정 결과에 평균값을 내어 최근 1년동안의 평균 체력 변화 추이를 보여주는 라인 그래프(5점:특급, 4점:1급, 3점:2급, 1점이하:미달)
![adminOverall2](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/Tsp_admin_overall_history.png)

그리고 구체적인 부대별 체력 대비테세 및 상,하위 부대를 확인할 수 있는 부대별 최근 한달동안의 체력검정 결과 테이블을 제공하고 있습니다.
![adminOverall](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/webpimage.png)  

위의 3가지 구성요소를 통합 어드민 페이지에서 실시간으로 제공하고 있습니다.
![adminPage](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/Tsp_adminPage.png)

이러한 통합 시스템은 관리자가 좁게는 개개인의 사용자의 체력검정능력을 넓게는 각 부대 단위의 체력을 측정하고 관리할 수 있게됩니다.
TSP를 통하여 과거와 현재의 체력 대비태세를 점검하고 평가할수 있으며 매년 변화되는 국군 체력 증진 관련 정책의 효과를 실시간으로 확인하고 효과적인 정책을 선택하는데 큰 도움을 줄수 있습니다.

#### [Youtube 웹시연영상](https://www.youtube.com/watch?v=Y0NcxoXA_Go)
<img src="https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/WEBPage.gif" width="1200"/>

### TSP Mobile Solution

App은 디자인, 편의성, 다양한 기능 등 여러가지 기술들을 개발하여 사용자가 사용할때 보다 쉽게 App에 접근할 수 있도록 개발하였습니다.
App은 크게 다양한 운동 종목을 측정할 수 있는 측정 엑티비티, 사용자가 측정한 기록을 시간 순서에 따라 보여주는 그래프 엑티비티, 그리고 사용자의 프로필 및 사용자의 최대 능력을 보여주는 프로필 엑티비티로 구성되어 있습니다. 

#### [Youtube 앱시연영상](https://www.youtube.com/watch?v=xPObigWPFjk)
<img src="https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/App_running_animation.gif" width="600"/>

#### [Youtube 팔굽혀펴기 시연영상](https://youtu.be/Q6kNGw0_eKk)
[![AppVideo](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/push_up_video.png)](https://youtu.be/Q6kNGw0_eKk)

#### [Youtube 윗몸일으키기 시연영상](https://youtu.be/F8IG_mpT1xQ)
[![AppVideo](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/sit_up_video.png)](https://youtu.be/F8IG_mpT1xQ)

## 설치방법(Install)

[apk파일 다운로드](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/tree/master/app/release)
위 하이퍼링크에서 apk파일을 다운로드 받을 수 있습니다.

## 사용방법(How to use)

앱의 시작화면입니다. 체력측정 시작시 체력측정 버튼을 클릭하시면 됩니다.  
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/appstart.jpg width="400">  

슬라이드를 이용하여 팔굽혀펴기, 윗몸일으키기, 3KM달리기 측정화면으로 넘어갈수있습니다.  
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/apppushup.jpg width="300">
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/appsitup.jpg width="300">
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/app3km.jpg width="300">  

다음은 체력측정차트입니다. 현재페이지에서 체력측정기록을 한눈에 확인할 수 있습니다.  
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/apppushupchart.jpg width="300">
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/appsitupchart.jpg width="300">
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/app3kmchart.jpg width="300">  

랭킹페이지입니다. 앱사용자들의 체력순위를 나타내주는 페이지입니다.  
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/appranking.jpg width="400">  

마지막으로 설정페이지입니다. 내정보, 등급표, 버전정보 등을 나타내주는 페이지입니다.  
<img src= https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/appsetting.jpg width="400">  


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

![classifier](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/classifier.PNG)

위 사진은 이미지의 신체내 각종 골격의 좌표를 특정 행동으로 분류하는 작업입니다. 사진마다 일일이 Stand, Move, Down을 입력해 AI Model이 인식하여 측정할 수 있게 데이터 작업을 약 100+시간, 40,000+장의 사진을 분류하였습니다.

![pushupb](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/pushup_classification.png)  
![situpb](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/situp_classification.png)  

#### POC(Proof Of Concept)

약 1000장의 이미지를 우선적으로 확보하여 POC 개념으로 초기모델을 생성 테스트하였습니다. 그 후 정확도를 올리기 위해 더 많은 이미지를 분류하고 적용하였습니다.  
![POC](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/POC.png)


이외에도 체력측정의 정확성과 효율성을 높이기 위해 저희 팀은 모델의 **정규화**와 **경량화** 를 하였습니다.  

#### 모델 정규화

기본 모델에서 조금 더 정교한 측정을 위해 다양한 모델 정규화를 테스트하였습니다.
신체부위의 각 위치는 이미지 내의 px의 위치를 좌표값으로 받습니다. 첫번째 프로토 타입은 이미지 내의 인체 좌표값의 최대값을 1, 최소값을 0으로 재분포하여 이미지의 기존 좌표축을 기준으로 계산할시 ~95%의 정확도를 보였습니다. 
따라서 모델의 정확도를 향상시키기 위해 다양한 신체 위치를 기준으로 좌표축을 새롭게 산출하여 모델의 성능을 테스트 하였습니다.

![nomalization](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/axis_comparison.png)

#### 모델 경량화

이미지를 서버로 전송하여 분류하는 방식이 아닌 On-Premise 모델의 개발을 위해 모델의 경량화를 실시하였습니다.
1차적으로 수작업으로 모델의 효율성 향상 및 불필요한 연산과정을  제거하여 정확도가 큰 폭으로 변하지 않는 선에서 모델의 최종 변수의 갯수를 최소화 하였습니다.
2차적으로 Tensorflow Converter를 사용하여 model.tf 를 TFLite로 변환하여 weight가 낮은 변수 제거 및 Android 환경 최적화를 진행하였습니다.

![WeightL](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/WeightL2.PNG)  

기존 모델은 서버에서 이용하여 데이터를 측정하고 하였으나 모델의 정규화와 경량화를 거친후에는 앱 자체에서도 로컬머신으로 측정이 가능하게 하여 서버와 통신없이 자체적으로 측정할 수 있게 되었습니다.

## 소프트웨어 구성 및 시스템 요구사항

- Android API level 26 이상 (필수)
- Qualcomm®5 SnapdragonTM 730G 이상 <~30FPS 이미지 분석>(권장)

## 다른 체력 측정앱 대비 장점

특산품은 제시된 다른 체력 검정 시스템과 비교하여 몇 가지 큰 장점을 가지고 있습니다.
1. **별도의 하드웨어를 필요로 하지 않습니다.** 병사들이 모두 개인적으로 소지하고 있는 휴대폰으로 간단히 사용이 가능하며 별도의 IOT 기기를 필요로 하지 않아 비용이 절감됩니다.
2. **수검자의 부정행위를 탐지하기 쉽습니다.** 근접센서, 중량센서등의 각종 센서 기반의 시스템은 사용자의 부적절한 자세나 무릎이 땅에 닫는 등의 탈락 행위 또는 대리시험등의 부정행위를 판단하는데 큰 제약이 있습니다. 하지만 특삼품은 Vision 기반의 이미지 분석을 기본으로 하여 신원확인 및 부적절한 자세 및 부정행위를 손쉽게 탐지하고 방지할 수 있습니다.
3. **다른 open-source 프로젝트를 기반으로 시스템을 구축하였습니다.** ML kit, nodeJs 등 여러가지 오픈소스 프로젝트를 기반으로 시스템을 구축하여 다른 오픈소스 시스템이 개선됨에 따라 특산품의 정밀도, 정확도 및 유용성도 함께 확장되며 함께 커뮤니티를 확장 시킬수 있습니다.
4. **넓은 확장성으로 다른 운동의 손쉽게 추가할 수 있습니다.** 특산품을 구축하는 시스템은 아주 높은 정확도를 가지고 있지만 위에서 보여지듯 간단한 시스템 설계를 가지고 있습니다. 앱 내에 위치한 이미지 분석 시스템을 통해 약 10시간 정도의 이미지 분류작업만 한다면 새로운 운동을 손쉽게 추가할 수 있습니다.

## 기술 스택 (Technique Used)


### Server 
![Server](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/ServerLogo.png)

### App
![App](https://github.com/osamhack2020/APP_TookSamPoom_Navy3Generals/blob/master/sampleImage/appLogo.png)


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
- ~~윗몸 일으키기 모델 계발~~
- ~~Main Activity에 그래프 형식의 유저 결과표 추가~~
- ~~Developer 모드 추가~~
- ~~히스토리 그래프 추가~~

### v0.2
- 달리기 측정모드 서비스 형식으로 전환
- 측정모드 화면회전 한가지로 고정

## 저작권 및 사용권 정보
 * [MIT](https://github.com/osam2020-WEB/Sample-ProjectName-TeamName/blob/master/license.md)
 * [ML Kit Pose Detection](https://developers.google.com/ml-kit/vision/pose-detection)
