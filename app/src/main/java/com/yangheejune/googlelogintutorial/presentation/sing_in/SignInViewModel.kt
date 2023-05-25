package com.yangheejune.googlelogintutorial.presentation.sing_in

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel이란?
 * android jetpack의 구성요소 중 하나
 * mvvm 디자인 패턴으로부터 파생된 단어
 * Activity와 Fragment와 같은 UI 컨트롤러의 로직에서 데이터를 다루는 로직을 분리하기 위해서 등장한 jetpack 라이브러리
 * 필요성?
 * 안드로이드는 모바일 OS 특성상 리소스에 대한 많은 제약들이 존재
 * 예를 들어 화면이 세로에서 가로로 회전했을때 Activity가 onDestroy 된 다음 다시 화면이 그려짐
 * onCreate -> onStart 기존의 데이터가  onCreate가 되면서 데이터가 날아간다.
 *  참고 : https://youngdroidstudy.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9D%98-Lifecycle
 *
 * 이때 이러한 이벤트 등을 통해 데이터가 날아가는 문제를 해결 하기 위해 기존에는  saveInstanceState를 이용했다.
 * activity가 onDestroy 되기전 세이브 하고 싶은 데이터를 저부분을 통해 onCreate로 넘겨서 데이터를 날리지 않고 계속 이용할 수 있다.
 * 여기서 단점
 * 1. 담을 수 있는 데이터가 적다 50kb 미만
 * 2. 담을 수 있는 데이터 형태가 제한 됨
 * 3. onCreate에서 작업을 처리해야 하므로 UI 컨트롤러가 해야 할 일이 늘어나고 이로 인해 화면을 띄우는데 시간이 오래 걸림
 *
 * 이러한 문제를 해결하기 위해 나온게 ViewModel
 * ViewModel은 Activity가 더이상 사용하지 않는 상태인 onDestroy() 호출 이후 onCleared()을 호출하여 내부 데이터를 초기화 하고 해제함.
 * ViewModel의 scope(생명 주기의 범위)는 ViewModelProvider에 의해서 결정됨
 *
 *
 */
class SignInViewModel:  ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}