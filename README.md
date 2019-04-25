# Gradle 비밀번호 처리 방법 정리
주의: 이 프로젝트에서 사용되는 민감 파일들인 `local.properties` 파일과 `.secrets` 디렉토리는
.gitignore에 등록되고 버전관리 시스템에서 지워져야 하지만 샘플 프로젝트이므로 편의상 지우지 않았습니다.

### 1. 콘솔
- 소스코드 -> [password-1-console.gradle](./gradle/password-1-console.gradle)

콘솔을 통해 입력받는 방법은 일부 환경에서는 실행되지 않습니다.

### 2. 파일
- 소스코드 -> [password-2-properties-file.gradle](./gradle/password-2-properties-file.gradle)

파일을 통해서도 민감한 파일들을 입력받을 수 있습니다.
이 경우에는 저장하는 것이기 때문에 항상 저장된 파일의 관리에 대해 주의를 기울여야 합니다.
이 예제에서는 `.properties` 파일을 이용하였습니다.

### 3. 환경변수
- 소스코드 -> [password-3-env-variable.gradle](./gradle/password-3-env-variable.gradle)

환경변수를 통해서 입력받을 수도 있습니다. 
이 때는 Gradle Task 매개변수를 이용해도 되고 `System.getenv()` 메서드를 이용하면 됩니다.
위 파일과 마찬가지로 저장하는 방식이기 때문에 항상 저장했었다는 것을 의식해야 합니다.    

### 4. GUI
- 소스코드 -> [password-4-gui.gradle](./gradle/password-4-gui.gradle)

Swing을 통해 GUI를 만들어서 입력받을 수 있습니다.
이 예제에서는 Groovy의 SwingBuilder를 사용하였습니다.

## 샘플
- 소스코드 
    - [sample.gradle](./gradle/sample.gradle)
    - [Main.java](./src/main/java/com/github/galcyurio/Main.java)
    
### 실행방법
```bash
./gradlew run
```

### 코드 설명
`copySecrets` Task는 `.secrets` 디렉토리에 있는 파일들을 프로그램의 classpath로 복사합니다.

Task만 만들어두면 작동하지 않기 때문에 기존 Task 중 하나인
`processResources` Task가 `copySecrets` Task에 의존하도록 만듭니다.

> 참고: processResources Task는 java 플러그인에서 미리 만들어둔 Task 중 하나로써 
resources 디렉토리에 있는 파일들을 classpath로 복사하는 역할을 수행합니다.

이렇게 하면 `build` Task가 기본적으로 `processResources` Task에 의존하고 있기 때문에 
`build` Task를 수행하면 자동으로 `copySecrets` Task를 수행하게 됩니다.  
 
마지막으로 `run` Task는 `build` 작업이 완료된 후 main 메서드를 실행합니다.

이렇게 Gradle을 통해 Task를 만들어둠으로써 [Main.java](./src/main/java/com/github/galcyurio/Main.java)에서 
볼 수 있듯이 classpath에 존재하는 `secret.properties` 파일을 불러와서 `apiKey`를 사용할 수 있습니다.
