# Unit tesztelés

## Osztályok

Alább láthatóak a Unit tesztelés céljából létrehozott osztályok és a tesztek helyes lefutása utáni kódlefedettség:
- TodosViewModelTest

![image](https://user-images.githubusercontent.com/47916183/118395494-fd64aa00-b64a-11eb-92de-4fd474749a16.png)

- TodosPresenterTest

![image](https://user-images.githubusercontent.com/47916183/118395541-40bf1880-b64b-11eb-8732-1411eb5e82f0.png)

- TodosInteractorTest

![image](https://user-images.githubusercontent.com/47916183/118395600-87147780-b64b-11eb-9e4f-c73dfc2e6090.png)

- DiskDataSourceTest

![image](https://user-images.githubusercontent.com/47916183/118395640-bcb96080-b64b-11eb-961d-d2949c15211a.png)

- DataBaseTest

## Tanulság/észrevétel

Az adatbázis leválasztása és külön tesztelése a közvetlenül tőle függő DiskDataSource osztálytól külön odafigyelést igényelt, egyébként a többi osztály metódusainak a tesztelése nem okozott problémát a kotlinos keretek felépítése után.

