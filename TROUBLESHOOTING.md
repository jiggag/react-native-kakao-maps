## 갑자기 문제가 생겼습니다
### iOS 빌드가 되지 않아요
```shell
❌  ld: symbol(s) not found for architecture x86_64
❌  clang: error: linker command failed with exit code 1 (use -v to see invocation)
```

<details>
<summary>에러 메세지 자세히 보기</summary>

```shell
Undefined symbols for architecture x86_64:
"_sqlite3_bind_blob", referenced from:
mapEngine::ResourceCacheDao::_updateCacheData(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::_insertCacheData(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_bind_double", referenced from:
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateAccessTime(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::_updateCacheEntity(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::_insertCacheEntity(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::updateAccessTime(mobileToolkit::BasicString const*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_bind_int", referenced from:
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::deleteEntitiesByType(int) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::invalidateAllEntries() in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::selectCleaningList(int, int) in DaumMap(ResourceCacheDao.o)
...
"_sqlite3_bind_text", referenced from:
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateAccessTime(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::deleteEntity(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::selectCacheEntity(mobileToolkit::BasicString const*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::selectCacheData(mobileToolkit::BasicString const*, mobileToolkit::BasicString*) in DaumMap(ResourceCacheDao.o)
...
"_sqlite3_close", referenced from:
mapCore::DiskCacheDao::_openOrCreateDB() in DaumMap(DiskCacheDao.o)
mapCore::BaseSqliteDao::~BaseSqliteDao() in DaumMap(BaseSqliteDao.o)
mapCore::BaseSqliteDao::open() in DaumMap(BaseSqliteDao.o)
mapCore::BaseSqliteDao::close() in DaumMap(BaseSqliteDao.o)
"_sqlite3_column_blob", referenced from:
mapEngine::ResourceCacheDao::selectCacheData(mobileToolkit::BasicString const*, mobileToolkit::BasicString*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_column_bytes", referenced from:
mapEngine::ResourceCacheDao::selectCacheData(mobileToolkit::BasicString const*, mobileToolkit::BasicString*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_column_double", referenced from:
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::selectCacheEntity(mobileToolkit::BasicString const*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::selectCleaningList(int, int) in DaumMap(ResourceCacheDao.o)
"_sqlite3_column_int", referenced from:
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::selectCacheEntity(mobileToolkit::BasicString const*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_column_text", referenced from:
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::selectCacheEntity(mobileToolkit::BasicString const*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::selectCleaningList(int, int) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::selectDanglingList(mobileToolkit::BasicString*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_errmsg", referenced from:
mapCore::BaseSqliteDao::~BaseSqliteDao() in DaumMap(BaseSqliteDao.o)
mapCore::BaseSqliteDao::open() in DaumMap(BaseSqliteDao.o)
mapCore::BaseSqliteDao::_logError(char const*) in DaumMap(BaseSqliteDao.o)
mapCore::BaseSqliteDao::close() in DaumMap(BaseSqliteDao.o)
mapCore::BaseSqliteDao::_logErrorOnPrepareStatement() in DaumMap(BaseSqliteDao.o)
"_sqlite3_exec", referenced from:
mapCore::DiskCacheDao::_openOrCreateDB() in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::_attachDatabase(mobileToolkit::BasicString const*, mobileToolkit::BasicString const*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::updateEntity(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::insertEntity(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::deleteEntity(mapEngine::ResourceCacheEntity const*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_finalize", referenced from:
mapCore::DiskCacheDao::onAfterInitializeDb() in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateAccessTime(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
...
"_sqlite3_free", referenced from:
mapCore::DiskCacheDao::_openOrCreateDB() in DaumMap(DiskCacheDao.o)
"_sqlite3_last_insert_rowid", referenced from:
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapEngine::ResourceCacheDao::insertEntity(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_open", referenced from:
mapCore::DiskCacheDao::_openOrCreateDB() in DaumMap(DiskCacheDao.o)
mapCore::BaseSqliteDao::open() in DaumMap(BaseSqliteDao.o)
"_sqlite3_prepare_v2", referenced from:
mapCore::DiskCacheDao::onAfterInitializeDb() in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateAccessTime(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
...
"_sqlite3_reset", referenced from:
mapEngine::ResourceCacheDao::_insertCacheEntity(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
mapEngine::ResourceCacheDao::_insertCacheData(mapEngine::ResourceCacheEntity*) in DaumMap(ResourceCacheDao.o)
"_sqlite3_step", referenced from:
mapCore::DiskCacheDao::selectByKey(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectByType(int, bool) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::selectCleaningList(int, int) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::insertEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateEntity(mapCore::DiskCacheEntity*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::updateAccessTime(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
mapCore::DiskCacheDao::deleteEntity(mobileToolkit::BasicString const*) in DaumMap(DiskCacheDao.o)
...
ld: symbol(s) not found for architecture x86_64
clang: error: linker command failed with exit code 1 (use -v to see invocation)

Undefined symbol: _sqlite3_bind_blob

Undefined symbol: _sqlite3_bind_double

Undefined symbol: _sqlite3_bind_int

Undefined symbol: _sqlite3_bind_text

Undefined symbol: _sqlite3_close

Undefined symbol: _sqlite3_column_blob

Undefined symbol: _sqlite3_column_bytes

Undefined symbol: _sqlite3_column_double

Undefined symbol: _sqlite3_column_int

Undefined symbol: _sqlite3_column_text

Undefined symbol: _sqlite3_errmsg

Undefined symbol: _sqlite3_exec

Undefined symbol: _sqlite3_finalize

Undefined symbol: _sqlite3_free

Undefined symbol: _sqlite3_last_insert_rowid

Undefined symbol: _sqlite3_open

Undefined symbol: _sqlite3_prepare_v2

Undefined symbol: _sqlite3_reset

Undefined symbol: _sqlite3_step
```
</details>

#### 해결방법
`Build Phases > Link Binary With Libraries > libsqlite3.tbd 추가`

### 지도가 보이지 않아요
[카카오 지도 개발자 문서](https://apis.map.kakao.com/android/guide/#step2)를 참고하여 앱 키 발급 및 키 해시를 등록해주세요

<details>
<summary>안드로이드 키 해시 추출하기</summary>

```shell
keytool -exportcert -alias {key_alias} -keystore {keystore_path} -storepass {store_password} -keypass {key_password} | openssl sha1 -binary | openssl base64
keytool -exportcert -alias androiddebugkey -keystore ./android/app/debug.keystore -storepass android -keypass android | openssl sha1 -binary | openssl base64
```
</details>

#### 해결방법
발급 받은 앱 키 `strings.xml > kakao_app_key` 교체 및 `앱 설정 > 플랫폼` 키 해시 등록
