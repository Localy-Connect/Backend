# **Coderichtlinien für Localy**

---

## **Allgemeine Richtlinien**
- **Code-Stil:**
    - Halten Sie sich an die vereinbarten Code-Stilvorgaben für Java und Angular.
    - Verwenden Sie Java-Konventionen für das Backend (`camelCase` für Variablen, `PascalCase` für Klassen).
    - Für das Frontend (Angular): `kebab-case` für Dateinamen und `camelCase` für Variablen und Methoden.

- **Kommentare:**
    - Kommentieren Sie komplexe oder wichtige Codeabschnitte.
    - Verwenden Sie JavaDoc für das Backend und Inline-Kommentare (`// ...`) im Frontend.

- **Datei- und Ordnerstruktur:**
    - **Backend:** Struktur nach dem Standard-Maven-Layout (`src/main/java`, `src/test/java`).
    - **Frontend:** Struktur entsprechend den Angular-Konventionen (`components`, `services`, `models`).

---

## **Git-Workflow**
- **Branches:**
    - Verwenden Sie das Branching-Muster `feature/`, `bugfix/`, `hotfix/`.
    - **Beispiele:**
        - `feature/add-user-authentication`
        - `bugfix/fix-database-connection`

- **Commits:**
    - Verwenden Sie prägnante Commit-Nachrichten im Format:
      ```
      [Typ] Kurze Beschreibung des Commits
      ```
    - **Commit-Typen:**
        - `[Feature]` für neue Funktionen
        - `[Bugfix]` für Fehlerbehebungen
        - `[Refactor]` für Code-Verbesserungen
        - `[Docs]` für Dokumentationsaktualisierungen

  **Beispiele:**
    - `[Feature] Login-Funktion hinzugefügt`
    - `[Bugfix] Datenbankverbindung behoben`
    - `[Docs] Readme-Datei aktualisiert`

---

## **Code-Qualität**
- **Backend:**
    - Verwenden Sie den **Maven Verifier** (`mvn clean verify`), um Tests und Code-Qualitätsprüfungen durchzuführen.
    - Stellen Sie sicher, dass Unit-Tests vorhanden sind und Integrationstests bei komplexen Funktionen geschrieben werden.

- **Frontend:**
    - Verwenden Sie `ng lint`, um Code-Qualitätsprüfungen durchzuführen.

- **Automatisierung:**
    - Code-Qualitätsprüfungen sind in die CI/CD-Pipeline integriert und werden bei jedem Commit und Merge-Request ausgeführt.

---

## **Pull Requests und Code-Reviews**
- **PR-Erstellung:**
    - Erstellen Sie Pull-Requests mit einer klaren und prägnanten Beschreibung der vorgenommenen Änderungen.
    - Verweisen Sie auf relevante Issues oder User Stories.

- **Code-Reviews:**
    - Mindestens ein Teammitglied sollte den PR überprüfen, bevor er gemerged wird.
    - Verwenden Sie standardisierte Code-Review-Checklisten für Backend und Frontend.

---

**Hinweis:** Diese Coderichtlinien werden regelmäßig aktualisiert und sind verbindlich für alle Entwickler im Projekt.

# **CI/CD-Integration und Pipeline-Workflow**

---

## **Integrationspraktik und Automatisierung**

Unser Projekt **Localy** verwendet eine automatisierte CI/CD-Pipeline mit **GitLab CI/CD**. Diese führt Codeprüfungen, Tests und Deployments automatisch durch.

---

### **Pipeline-Workflow**

Die folgende Pipeline-Struktur wird verwendet:

1. **Verify:**
    - Führt Code- und Style-Prüfungen mithilfe des **Maven Verifier** durch.
    - Automatische Prüfung der Einhaltung der Coderichtlinien.
    - Lokale Ausführung:
      ```bash
      mvn clean verify
      ```

2. **Test:**
    - Führt Unit-Tests und Integrationstests im Backend aus.
    - Für das Frontend:
      ```bash
      ng test
      ```

3. **Deploy:**
    - Stellt die Anwendung in der Test- oder Produktionsumgebung bereit.
    - Deployment erfolgt nur nach erfolgreicher Ausführung aller vorherigen Stufen.

---

### **GitLab CI/CD-Konfiguration**

**Auslöser:**
- Automatische Pipeline-Ausführung bei jedem Commit oder Merge-Request in die `main`- oder `develop`-Branches.

**Konfigurationsdatei:** `.gitlab-ci.yml`

**Beispiel für die Konfiguration:**
```yaml
stages:
  - verify
  - test
  - deploy

verify:
  stage: verify
  script:
    - mvn clean verify
  only:
    - merge_requests
    - main

test:
  stage: test
  script:
    - ng test
  only:
    - merge_requests
    - main

deploy:
  stage: deploy
  script:
    - echo "Deployment erfolgreich"
  only:
    - main
