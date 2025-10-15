---
layout: default.md
title: "User Guide"
pageNav: 3
---

# playbook.io User Guide

playbook.io is the ultimate platform that empowers sports agents to stay organised, build stronger relationships, and
drive success for their athletes and partners. It simplifies the management of athletes, organisations, and contracts —
allowing agents to focus on what truly matters.
<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `17` or above installed on your computer.  
   **Mac users:** Follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationMac.html) to
   install the correct JDK version.

2. Download the latest `.jar` file from
   the [playbook.io Releases](https://github.com/AY2526S1-CS2103T-F13-3/tp/releases) page.

3. Copy the file to the folder you want to use as the **home folder** for playbook.io.

4. Open a terminal, navigate (`cd`) to that folder, and run the following command:
   ```
   java -jar playbook.io.jar
   ```

5. The playbook.io interface should appear in a few seconds, preloaded with sample data for exploration.  
   ![Ui](images/Ui.png)

6. Use commands in the command box to manage your athletes, organisations, and contracts.  
   Example:
   ```
   add-a n/LeBron James s/Basketball a/40 p/98765432 e/james@example.com
   ```

---

## Features

### Add Athlete

**Command:**

```
add-a n/NAME s/SPORT a/AGE p/PHONE e/EMAIL
```

**Example:**

```
add-a n/LeBron James s/Basketball a/40 p/98765432 e/james@example.com
```

Adds a new athlete to playbook.io.

---

### Delete Athlete

**Command:**

```
delete-a n/NAME s/SPORT
```

**Example:**

```
delete-a n/LeBron James s/Basketball
```

Deletes an athlete profile based on name and sport.

---

### Add Organisation

**Command:**

```
add-o o/ORGANISATION_NAME p/PHONE e/EMAIL
```

**Example:**

```
add-o o/Nike p/98765432 e/john.doe@nike.com
```

Adds a new organisation such as a sponsor, team, or brand.

---

### Delete Organisation

**Command:**

```
delete-o o/ORGANISATION_NAME
```

**Example:**

```
delete-o o/Nike
```

Deletes an organisation identified by its name.

---

### Add Contract

**Command:**

```
add-c n/NAME s/SPORT o/ORG sd/DDMMYYYY ed/DDMMYYYY am/AMOUNT
```

**Example:**

```
add-c n/Lionel Messi s/Football o/Inter Miami sd/01012024 ed/01012025 am/50000000
```

Links an athlete and an organisation through a new contract.

---

### Delete Contract

**Command:**

```
delete-c n/NAME o/ORG sd/DDMMYYYY ed/DDMMYYYY
```

**Example:**

```
delete-c n/Lionel Messi o/Inter Miami sd/01012024 ed/01012025
```

Deletes a contract matching the athlete, organisation, and date range.

---

## Command Summary

| Command    | Description         | Example                                                                             |
|------------|---------------------|-------------------------------------------------------------------------------------|
| `add-a`    | Add athlete         | `add-a n/LeBron James s/Basketball a/40 p/98765432 e/james@example.com`             |
| `delete-a` | Delete athlete      | `delete-a n/LeBron James s/Basketball  `                                            |
| `add-o`    | Add organisation    | `add-o o/Nike p/98765432 e/john.doe@nike.com`                                       |
| `delete-o` | Delete organisation | `delete-o o/Nike `                                                                  |
| `add-c`    | Add contract        | `add-c n/Lionel Messi s/Football o/Inter Miami sd/01012024 ed/01012025 am/50000000` |
| `delete-c` | Delete contract     | `delete-c n/Lionel Messi o/Inter Miami sd/01012024 ed/01012025`                     |

---

## Contact

For issues or contributions, please visit the [GitHub repository](https://github.com/AY2526S1-CS2103T-F13-3/tp) or
contact
the playbook.io development team.
