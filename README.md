# ユーザー管理アプリ

## アプリ概要
- ユーザーデータベースを管理するREST API
- JUnitを用いたテストコードを実装中

## ユーザー情報
| 項目       | 型      | 備考               |
|----------|--------|------------------|
| ID       | int    | オートインクリメント、DB主キー |
| name     | String |                  |
| birthday | String |                  |

## 各クラスメソッド、単体テスト確認事項(随時追加予定)
<details><summary>Serviceクラス</summary><div>

| メソッド                                                                           | 概要                            | 単体テスト確認事項                                                 |
|--------------------------------------------------------------------------------|-------------------------------|-----------------------------------------------------------|
| List<User> findAll()                                                           | 全てのユーザーの情報を返す                 | - 全てのユーザーの情報を返すこと                                         |
| User findById(int id)                                                          | 指定したIDのユーザーデータを返す             | - 指定したIDのユーザーデータを返すこと<br/> - 指定したIDのユーザーが存在しないとき例外を返すこと   |
| User create(String name, String birthday,  BindingResult bindingResult)        | オートインクリメントで取ってきたIDに入力データを登録する | - オートインクリメントで取ってきたIDに入力データが登録できること                        |
| void update(int id, String name, String birthday, BindingResult bindingResult) | 指定したIDのデータを入力データで更新する         | - 指定したIDのデータを入力データで更新できること<br/> - 更新指定したIDが存在しないとき例外を返すこと |
| void delete(int id)                                                            | 指定したIDのデータを削除する               | - 指定したIDのデータが削除できること<br/> - 削除指定したIDが存在しないとき例外を返すこと       |

</div></details>


## ローカルでのアプリケーション起動方法
1.自分のPCにリポジトリをgit cloneする  
`git clone https://github.com/OOTSUJIYUUSUKWE/FinalTest.git `

2.Dockerを起動  
ターミナルで以下のコマンドを実行する  
`docker compose up`  
`docker compose exec db mysql -uroot -p`  
パスワードを聞かれたらpasswordと入力する

3.プロジェクト実行
src/main/java/com/example/RaiseTech11/RaiseTech11Application.javaを開き実行する

4.Postmanやcurlなどでリクエストを送る  
URLの共通部分：http://localhost:8080  
各操作に応じたHTTPメソッド、URL、リクエストボディの入力内容はAPI仕様を参照

5.結果を確認

## API仕様
| Request | メソッドcurlコマンド例                                                                                                                                                                                                                                                                                                                                                           |     |
|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----|
| GET     | List\<UserResponse> findAll() <br> `curl -X GET 'http://localhost:8080/users'`                                                                                                                                                                                                                                                                                          |     |
| GET     | User findById(@RequestParam(value = "id") int id) <br> `curl -X GET 'http://localhost:8080/users/id?id=1'`                                                                                                                                                                                                                                                              |     |
| POST    | ResponseEntity<Map<String, String>> createUser(@RequestBody @Validated CreateForm form, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) <br> `curl -X POST 'http://localhost:8080/users' \-H 'Content-Type: application/json' \--data '{"name" :"大辻友佑", "birthday" : "19950221"}'`                                                              |     |
| PATCH   | ResponseEntity<Map<String, String>> patchUser(@PathVariable("id")int id, @RequestBody @Validated UpdateForm form, BindingResult bindingResult) {userService.update(id, form.getName(), form.getBirthday(), bindingResult) <br> `curl -X PATCH 'http://localhost:8080/users/1' \-H 'Content-Type: application/json' \--data '{"name" :"大辻友佑", "birthday" : "20050221"}'` |     |
| DELETE  | ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id")int id) <br> `curl -X DELETE 'http://localhost:8080/users/1' `                                                                                                                                                                                                                                        |     |
