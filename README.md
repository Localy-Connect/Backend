Initialisierung:

 Zu erst öffnen wir ein Terminal in der IDE unserer Wahl. Danach führen wir ```cd docker``` aus um in den Docker Ordner zu gelangen, hier wird unser Container laufen, in dem sich unsere Datenbank befindet.
 
 Um diesen zu starten führen wir ```docker-compose up-d``` aus das -d steht für detached es bewirkt das wir dieses terminal nicht während der Laufzeit unserer Db offen haben müssen.

 Danach kann man die main Klasse in ```ch.gibb.localy.LocalyApplication.java``` starten (nur beim ersten starten nötig danach sollte die IDE automatisch eine Runconfiguration machen).

 zum Schluss starten wir dann noch das Frontend. Zuerst öffnen wir ein neues Terminal in unserer IDE. Mit ```cd frontend``` gelangen wir dann in den Ordner indem wir unser Frontend entwickelt haben.
 Hier führen wir dann ```npm install```aus. wenn dieser Befehl fertig ausgeführt wurde können wir das Frontend mit ```ng serve``` starten

Ziele:


-Website Mobile freundlich machen. 


-Frontend schöner und Userfreundlicher machen. 


-Aktualisation Gemeinde nach Standort der Person. 


Anforderungen: 


-Farbkonzept erarbeiten 


-Mobile Applikationsentwicklung einlesen 


-Google maps GPS integration 


Rollen:


Collin: Projektleiter


Jan: Tester und Entwickler


Levin: Qualitätsverantwortlicher und Entwickler


Noah: Entwickler 


Ilay: Lösungsarchjtekt und Entwickler