# integration-task

Diagram przepływu opisuję bardzo podstawowy przepływ integracji.
Z uwagi na charakter projektu (POC), projekt nie zawiera obsługi błędów. Integracja kończy się wypisaniem xmla na ekran.


Opis przepływu.
--

1. Integracja zaczyna się od "poolera", który w określonych interwałach czasowych sprawdza dany katalog w poszukiwaniu nowych plików do przetworzenia.
2. W przypadku zapisania pliku w katalogu przesyłana jest jego nazwa do komponentu (FileSplitter), który tnie plik na pojedyńcze linie.
3. Linie pliku w postaci "String" trafiają do "agregatora", który łączy je wg polityki (4 linie = jeden rekord) co jest wielkim uproszczeniem.
4. Wiadomość zawierająca rekord jest przesyłana do transformacji, która odczytuje ich zawartość i tworzy z nich obiekty POJO.
5. Obiekty POJO opisujące "Person" zamieniane są na XML 
6. Xml zawierający rekord zapisywany jest w bazie danych

Usprawnienia:
--

Z uwagi na wymagania należało by wprowadzić pewne usprawnienia w celu obsługi wielu (dużych) plików.
1. Nazwy plików gotowych do przetworzenia należy kierować do kolejki persystentnej, z której będą pobierane przez jeden lub więcej komponentów do ich przetwarzania.
   Pozwoli to na rozdzielenia wstepnego przetwazania pliku na kila splitterów, być może na różnych maszynach.
2. Naturalnie trzeba wziąć pod uwagę zmianę polityki aggregatora plików.
3. Zagregowane linni plików zawierające pełne rekordy do przetworzenia mogą być wysyłane również na kolejkę jako wiadomości.
   Napewno będzie to innego typu software niż w pkt 1, pozwalający na obsługę dużej liczby wiadomości np Apache Kafka.
4. Z Kafki (albo nie kafki) wiadomości mogłyby być pobierane przez wielu konsumentów i przetważane jednocześnie do postaci POJO a potem XML.
5. Po przetworzeniu na XML wiadomości znowu mogły byc przesyłane do kafki (inny topic) i zbierane batchowo przez dodatkowy komponent, który zapisywałby je w bazie (np po 100 000 lub 1mln) na raz.
6. Oczywiście w przypadku takiego wolumenu danych baza NoSql powinna być odpowiednio skonfigurowana, tak żeby mogła być przystosowana do dużego wolumenu danych w trybie zapisu.
7. W przypadku rozproszenia systemu należało by się zatroszczyć o moduł zbierania logów i błędów z systemów rozproszonych np. Elasticsearch, Logstash, and Kibana

