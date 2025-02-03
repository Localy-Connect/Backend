# **Projekt-Setup**

**Installationen die nötig sind um das Projekt zu starten:**
1. **Docker**
2. **npm**
1. **Maven**
1. **Fylway**
1. **Node**


## **Initialisierung**
1. **Backend-Setup:**
 - Öffnen Sie ein Terminal in Ihrer bevorzugten IDE.
 - Wechseln Sie in den Docker-Ordner mit:
   ```bash
   cd docker
   ```
 - Starten Sie den Docker-Container mit:
   ```bash
   docker-compose up -d
   ```
 - **Hinweis:** Das `-d`-Flag sorgt dafür, dass der Prozess im Hintergrund läuft.

2. **Backend-Start:**
 - Starten Sie die Hauptklasse:
   ```bash
   ch.gibb.localy.LocalyApplication.java
   ```

3. **Frontend-Setup:**
 - Öffnen Sie ein neues Terminal und wechseln Sie in das Frontend-Verzeichnis:
   ```bash
   cd frontend
   ```
 - Installieren Sie die erforderlichen Abhängigkeiten:
   ```bash
   npm install
   ```
 - Starten Sie das Frontend:
   ```bash
   ng serve
   ```
 - **Standard-URL:** `http://localhost:4200`

## **Teamrollen**
- **Collin:** Projektleiter
- **Jan:** Tester und Entwickler
- **Levin:** Qualitätsverantwortlicher und Entwickler
- **Noah:** Entwickler
- **Ilay:** Lösungsarchitekt und Entwickler

---

## **Systemvoraussetzungen**
- **Node.js:** Version >= 14.x
- **Java:** Version >= 17.x
- **Docker:** Version >= 20.x

---

## **API-Dokumentation**
- **Backend-API:** Wir haben Swagger in unserem Backend implementiert, um die API-Dokumentation zu erstellen. 
- Nach dem Start der Anwendung können Sie die Dokumentation unter folgendem Link aufrufen: http://localhost:8080/swagger-ui/index.html.


**Hinweis:** Weitere angaben, um an diesem Projekt teilzunehmem finden sie im CONTRIBUTING.md
