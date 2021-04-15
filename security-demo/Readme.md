Author: 
陳泓宇

Email: 
klyvechen@gmail.com

Phone number: 0963041615

建置方法：

    安裝mvn, 並在pom目錄mvn clean install

啟動方法：

    java -jar backent-test-1.0.jar

Swagger:

    http://ec2-3-19-239-104.us-east-2.compute.amazonaws.com:8080/v1/swagger-ui.html

測試網址:

    http://ec2-3-19-239-104.us-east-2.compute.amazonaws.com:8080/v1/user/register

Example:
    
    1. type: 0 為email, 1為手機登入
    
    2. value: 要輸入的帳號, 若為type為0則aa@aa.a的格式, 若type為1則台灣地區號碼
    
    3. language, country為語系 目前有三種: [ch,TW], [jp,JP], [en,US] 

    ex: POST
    {
        "name": "測試",
        "type": 1,
        "value": "0963",
        "language": "en",
        "country": "US",
        "password": "password",
        "checkPassword": "password"
    }
    
    {
        "name": "測試",
        "type": 0,
        "value": "email_you_want_to_test@email.ee",
        "language": "en",
        "country": "US",
        "password": "password",
        "checkPassword": "password"
    }

