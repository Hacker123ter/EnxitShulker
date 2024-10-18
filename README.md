## EnxitShulker

![Version](https://img.shields.io/badge/Версия-1.0.0-blue.svg)
![API](https://img.shields.io/badge/Paper%201.16.5%2B-blue.svg)

<h3 align="center">Discord: luckytsb</h3>

## 🐈 Тестировалось на:
- Paper 1.16.5-790 

## ✨ Функции:

-️ :accessibility: Плагин, ограничивающий количество шалкер-боксов в инвентаре при выходе и добавляющий лимит для хранения шалкеров в эндер-сундуке. Создано в виде тестового задания.

## 🚀 Установка:

- 😧 Скачайте <a href="https://github.com/Hacker123ter/EnxitShulker/raw/refs/heads/EnxitShulker/target/EnxitShulker-1.0.0.jar" target="_blank">EnxitShulker-1.0.0.jar</a>.
- 🐈 Переместите его в папку "plugins" вашего сервера. (Убедитесь что Ядро и версия совместимы с плагином)
- 🪄 Перезапустите сервер.
- 😸 Радуйтесь жизни!

## 🎮 Использование:

1. Ограничивает лимит шалкеров в инвентаре (выставляет в конфиге config.yml (Exit_box: 2 ### количество по дефолту)), при привышении лимита высвечивается надпись в actionbar (выставляет в конфиге config.yml (Limit_shulker:)).
2. Когда у игрока привышен лимит шалкеров и инвентаре, при выходе, из него выпадут на землю шалкеры (остануться 2 рандомных, по дефолту, можно регулировать в конфиге).
3. Ограничивает лимит шалкеров в эндер сундуке (выставляет в конфиге config.yml (Ender_box: 2 ### количество по дефолту)), при привышении лимита, лишние шалкеры будут возвращены назад в инвентарь и будут удалены из эндер сундука, при этом высветится надпись (выставляется в конфиге config.yml (Limit_ender:)).
4. Можно перезагрузить конфиг - "/enxit reload" (требуются права "enxit.reload" или op).

## 🏗️ Команды:
```
/enxit reload
```

## 🔒 Права:
`enxit.reload` - для перезагрузки конфигурации плагина.

## Лицензия

Этот код лицензирован под [Special LICENSE](LICENSE.MD).
