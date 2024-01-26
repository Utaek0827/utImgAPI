# utImgAPI 명세서 노션링크
https://mahogany-glue-1ce.notion.site/UTImgApi-8dbf3663a31a46ce9c3280b920ec68f3

# 🔐 **Auth**

---

### 로그인

- 🌐 URL
    
    POST /api/v1/auth/sign-in
    
- 📰 Header
    
    -
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | email | string / String | * | 이메일 |
    | password | string / String | * | 패스워드 |
    
    **Example**
    
    ```json
    {
      "email": "email@email.com",
      "password": "P!ssw0rd"
    }
    ```
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    | token | string / String | * | JWT |
    | expirationTime | number / int | * | 만료 시간 |
    
    **Example**
    
    ```json
    {
      "code": "SU",
      "message": "로그인 성공",
      "token": "eyJhbG...",
      "expirationTime": 3600
    }
    
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    유효성 검사 실패
    Http Status - 400 (Bad Request)
    
    {
        "code": "VF",
        "message": "Validation failed."
    }
    ```
    
    ```json
    로그인 실패
    Http Status - 400 (Bad Request)
    
    {
        "code": "SF",
        "message": "Login information mismatch."
    }
    ```
    
    ```json
    데이터 베이스 오류
    Http Status - 400 (Bad Request)
    
    {
        "code": "DBE",
        "message": "Database error"
    }
    ```
    

---

### 회원 가입

- 🌐 URL
    
    POST /api/v1/auth/sign-up
    
- 📰 Header
    
    -
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | email | string / String | * | 이메일 |
    | password | string / String | * | 패스워드, 길이 8 ~ 20 |
    | telNumber | string / string | * | 휴대전화번호 (숫자로만 이루어) |
    | agreedPersonal | boolean / Boolean | * | 개인정보동의여부 (true) |
    
    **Example**
    
    ```json
    {
    	"email": "email@email.com",
    	"password": "P!ssw0rd",
      "telNumber": "01012345678",
      "agreedPersonal": true
    }
    ```
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    유효성 검사 실패
    Http Status - 400 (Bad Request)
    
    {
        "code": "VF",
        "message": "Validation failed."
    }
    ```
    
    ```json
    중복된 이메일
    Http Status - 400 (Bad Request) 
    
    {
        "code": "DE",
        "message": "Duplicate email."
    }
    ```
    
    ```json
    중복된 휴대전화번호
    Http Status - 400 (Bad Request) 
    
    {
        "code": "DT",
        "message": "Duplicate telephone number."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

# 🔐 Menu

---

### 사용중인 서비스 목록 GET /api/v1/menu/{email}

- 🌐 URL
    
    GET /api/v1/menu/service-list/{email}
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
- 🙏 Request
    
    -
    
    **Example**
    
    ```json
    
    ```
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    | serviceList | serviceList[] | * | 서비스 리스트 |
    
    serviceList
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    | serviceInfo | string / String | * | 서비스 설명 |
    | serviceTotalCap | string / String | * | 서비스 전체용량 |
    | serviceUsedCap | string / String | * | 서비스 사용중용량(초기0) |
    | serviceKey | string / String | * | 서비스 키 |
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success.",
    		"serviceList" : 
    		[
    			"serviceName" : "서비스1",
    			"serviceInfo" : "서비스 설명",
    			"serviceTotalCap" : "서비스 전체용량",
    			"serviceUsedCap" : "서비스 사용용량",
    			"serviceKey" : "asdfknasdlkfnasdknnfasd"
    		]
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 서비스 생성 POST /api/v1/menu

- 🌐 URL
    
    POST /api/v1/menu
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스이름 |
    | serviceInfo | string / String | * | 서비스 설명 |
    | StorageTotalCap | string / String | * | 저장용량(기본5Gb),등급에 따라 추가설정 가능 |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"serviceInfo": "의류쇼핑몰 이미지용",
      "storageCapacity": "5Gb"
    }
    ```
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    | serviceName | string / String | * | 서비스 이름 |
    | serviceInfo | string / String | * | 서비스 설명 |
    | serviceTotalCap | string / String | * | 서비스 전체용량 |
    | serviceUsedCap | string / String | * | 서비스 사용중용량(초기0) |
    | serviceKey | string / String | * | 서비스 키 |
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    		"serviceName" : "서비스1",
    		"serviceInfo" : "서비스 설명",
    		"serviceTotalCap" : "서비스 전체용량",
    		"serviceUsedCap" : "서비스 사용용량",
    		"serviceKey" : "asdfknasdlkfnasdknnfasd"
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    
    ```json
    중복된 서비스 이름
    Http Status - 400 (Bad Request) 
    
    {
        "code": "DN",
        "message": "Duplicated serviceName."
    }
    ```
    
    ```json
    존재하지 않는 유저
    Http Status - 404 (Not Found) 
    
    {
        code: "NU",
        message: "This user does not exist."
    }
    ```
    
    ```json
    인증실패
    Http Status - 401 (Unauthorized) 
    
    {
        code: "AF",
        message: "Authorization Failed."
    }
    ```
    

---

### 서비스 삭제 DELETE /api/v1/menu/{serviceName}

- 🌐 URL
    
    DELETE /api/v1/menu/{serviceName}
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    }
    ```
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success.",
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    
    ```json
    중복된 서비스 이름
    Http Status - 400 (Bad Request) 
    
    {
        "code": "DN",
        "message": "Duplicated serviceName."
    }
    ```
    
    ```json
    존재하지 않는 유저
    Http Status - 404 (Not Found) 
    
    {
        code: "NU",
        message: "This user does not exist."
    }
    ```
    
    ```json
    인증실패
    Http Status - 401 (Unauthorized) 
    
    {
        code: "AF",
        message: "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    

---

### 이미지 보기(웹) GET /{serviceName}/{imgChanName}

⇒ url을 통해서 외부에서도 접속 가능함

- 🌐 URL
    
    GET  /{serviceName}/{imgChanName}
    
    ---
    
- 📰 Header
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    | imgChanName | string / String | * | 이미지 암호화이름(중복문제) |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"imgChanName": "sdfqoikhansdofin.jpg"
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    | imgItem | imgItem[] | * | 이미지 파일, URL |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success.",
    		"imgItem" : 
    	[
    		"imgUrl": "asdfbasdknflkasdf.jpg"
    		"imgFile" : 이미지 파일
    	]
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 목록(웹) GET /api/v1/menu/{serviceName}

- 🌐 URL
    
    GET /api/v1/menu/{serviceName}
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    }
    ```
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    | imgList | ImgListItem[] | * | 이미지 리스트 아이템 |
    
    ImgListItem
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | imgOriName | string / String | * | 이미지 원본이름 |
    | imgChanName | string / String | * | 이미지 암호화이름(URL) |
    | imgExt | string / String | * | 이미지 유형(확장자) |
    | imgUptime | string / String | * | 이미지 업로드 시간 |
    | imgSize | string / String | * | 이미지 크기 |
    | email | string / String | * | 사용자 이메일 |
    | serviceName | string / String | * | 서비스 이름 |
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success.",
    		"imgList": [
    		{
    			"imgChanName": "85d8a200-9105-455c-9751-c62b0990cade.png",
    			"imgExt": "jpg",
    			"imgOriName": "imgOriName",
    			"imgUptime": "17054f1498487",
    			"imgSize": "964015",
    			"email": "asdf@gmail.com",
    			"serviceName": "wewt"
    		}
    	]
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 등록(웹) POST /api/v1/menu/{serviceName}

- 🌐 URL
    
    POST /api/v1/menu/{serviceName}
    
    ---
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    | imgFile | FormData / FormData | * | 이미지 파일 |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"imgFile": 이미지파일
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 수정(웹) PATCH /api/v1/menu/{serviceName}/{imgChanName}

- 🌐 URL
    
    PATCH /api/v1/menu/{serviceName}/{imgChanName}
    
    ---
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    | imgFile | FormData / FormData | * | 이미지 파일 |
    | imgChanName | string / String | * | 이미지 암호화이름(중복문제) |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"imgFile": 이미지파일,
    	"imgChanName": "asdfklnasdkfnlkasdfna.jpg"
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 삭제(웹) DELETE /api/v1/menu/{serviceName}/{imgChanName}

- 🌐 URL
    
    DELETE /api/v1/menu/{serviceName}/{imgChanName}
    
    ---
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceName | string / String | * | 서비스 이름 |
    | imgChanName | string / String | * | 이미지 암호화이름(중복문제) |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"imgChanName": "sdfqoikhansdofin.jpg"
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 등록(서버) POST /api/v1/menu/{serviceKey}/{serviceName}

- 🌐 URL
    
    POST /api/v1/menu/{serviceName}
    
    ---
    
- 📰 Header
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceKey | string / String | * | 서비스 키 |
    | serviceName | string / String | * | 서비스 이름 |
    | imgFile | FormData / FormData | * | 이미지 파일 |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"imgFile": 이미지파일
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 수정(서버) PATCH /api/v1/menu/{serviceKey}/{imgChanName}

- 🌐 URL
    
    PATCH /api/v1/menu/{serviceKey}/{imgChanName}
    
    ---
    
- 📰 Header
    
    -
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceKey | string / String | * | 서비스 키 |
    | serviceName | string / String | * | 서비스 이름 |
    | imgFile | FormData / FormData | * | 이미지 파일 |
    | imgChanName | string / String | * | 이미지 암호화이름(중복문제) |
    
    **Example**
    
    ```json
    {
    	"serviceName": "무신사",
    	"imgFile": 이미지파일,
    	"imgChanName": "asdfklnasdkfnlkasdfna.jpg"
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 이미지 삭제(서버) DELETE /api/v1/menu/{serviceKey}/{imgChanName}

- 🌐 URL
    
    DELETE /api/v1/menu/{serviceKey}/{imgChanName}
    
    ---
    
- 📰 Header
    
    -
    
    ---
    
- 🙏 Request
    
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | serviceKey | string / String | * | 서비스 키 |
    | imgChanName | string / String | * | 이미지 암호화이름(중복문제) |
    
    **Example**
    
    ```json
    {
    	"serviceName": "ASDFBN3WQN30BNF0-9SNDF9N3",
    	"imgChanName": "sdfqoikhansdofin.jpg"
    }
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

# 🔐 User

---

### 유저 정보 GET /api/v1/user/{email}

- 🌐 URL
    
    GET /api/v1/user/{email}
    
    ---
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
    
    ---
    
- 🙏 Request
    
    -
    
    **Example**
    
    ```json
    
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    | userStartDate | string / String | * | 서비스 이용기간 시 |
    | userEndDate | string / String | * | 서비스 이용기간 끝 |
    | userUsageCap | string / String | * | 서비스 현재 사용량 |
    | userTotalCap | string / String | * | 서비스 전체 사용량 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success.",
    		"imgItem" : 
    	[
    		"imgUrl": "asdfbasdknflkasdf.jpg"
    		"imgFile" : 이미지 파일
    	]
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    존재하지 않는 이미지
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "NU",
        "message": "This user does not exist."
    }
    ```
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---

### 회원 탈퇴 DELETE /api/v1/user/{email}

- 🌐 URL
    
    DELETE /api/v1/user/{email}
    
    ---
    
- 📰 Header
    
    
    | Name | Value | Description |
    | --- | --- | --- |
    | Authorization | Bearer Token | JWT토큰 |
    | email | 로그인 유저 이메일 | 이메일 |
    
    ---
    
- 🙏 Request
    
    -
    
    **Example**
    
    ```json
    
    ```
    
    ---
    
- 📬 Response
    - **Success**
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    ---
    
    **Example**
    
    ```json
    Http Status - 200 (OK)
    
    {
        "code": "SU",
        "message": "Success."
    }
    ```
    
    - Fail
    
    | Name | Type | Required | Description |
    | --- | --- | --- | --- |
    | code | string / String | * | 코드 |
    | message | string / String | * | 메세지 |
    
    **Example**
    
    ```json
    인증 실패
    Http Status - 401 (Unauthorized) 
    
    {
        "code": "AF",
        "message": "Authorization Failed."
    }
    ```
    
    ```json
    권한 없음
    Http Status - 403 (Forbidden) 
    
    {
        "code": "NP",
        "message": "Do not have permission."
    }
    ```
    
    ```json
    데이터베이스 오류
    Http Status - 500 (Internal Server Error)
    
    {
        "code": "DBE",
        "message": "Database error."
    }
    ```
    

---
