# kotlin-lotto

#### 예상 시나리오
1. 특정 템플릿을 가진 문자열을 전달받는다 ("" "1,2" "1:2:3")
2. 전달받은 값을 구분자로 분리한다. 
3. 분리한 값을 더한다
4. 값을 화면에 출력한다

#### 구현할 기능 리스트
##### 1) 입력 - 문자열을 전달받는다 (readLine)
- 숫자,구분자,숫자 형태로 전달받아야함 
- 커스텀구분자가 존재할 수도 있음 “//;\n1;2;3” 
- null blank 예외처리 

##### 2) 구분자로 분리
- 숫자는 2개 이상을 받아야 하며 구분자 역시 1개 이상이 있어야함 
- split시에 위의 구성상태가 아니면 예외처리를 함 
- 숫자 이외의 값 또는 음수 전달시에 예외처리 
- 숫자하나를 문자열로 입력했을 경우엔 숫자로 반환 
- 구문자는 1,2:3 이런식으로 , 이외에 :도 사용가능

##### 3) 숫자의 합 반환


#### 위에서 도출된 테스트리스트
1. 문자열이 null, blank가 아닌가?
2. 우리 형식대로 입력을 잘 했는가?
3. 구분자는 : , 를 잘 썼는가? 
4. 커스텀구분자 추가시에도 잘 되는가?
5. 숫자가 2개이상, 구분자도 1개 이상인가? 
6. 숫자이외의 값이나 음수 전달시에 예외처리가 잘 되는가? 
7. 입력값에 문자열로 숫자하나를 입력했을 경우 숫자로 잘 반환이 되는가? 
8. 분리된 값의 연산이 잘 되는가?



#### 요구사항
1. indent depth는 1까지 허용한다
2. 메서드의 길이가 10라인을 넘어가지 않도록 구현한다
3. 메서드는 한가지 일만 잘 하도록 구현한다