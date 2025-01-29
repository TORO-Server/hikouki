# Hikouki (Aircraft)

MarumasaCarのシステムを参考にした飛行機プラグイン

## 概念

飛行機(Aircraftクラス)は機体、ランディングギア、操縦席、副操縦席で構成されていて、その他の部品はChildクラスに入れることで動作させることができます。

- SeparatedPart: PartBasicとPartMetaを一緒に扱うクラス。
- PartMeta: パーツの座標等を示すクラスです。
- PartBasic: パーツの基底クラスです。
