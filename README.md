# Авторизация

## Авторизация пользователя:
### @PostMapping("/authorisation/login")
### Формат:
### username = ...
### password = ...
### Ответ:
### usernameAnswer = (correct/incorrect)
### passwordAnswer = (correct/incorrect)
#### При входе httpsession = {username}

## Получить уже авторизированного пользователя при заходе на страницу
### @GetMapping("/authorisation/session/getUsername")
### Ответ:
### sessionUsername = {username}

# Регистрация

### @PostMapping("/registration")
### username = ...
### password = ...
### Ответ:
### usernameAnswer = (denied/success)
### denied - пользователь существует
### success - запись в БД