# playbook.io

[![Java CI](https://github.com/AY2526S1-CS2103T-F13-3/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2526S1-CS2103T-F13-3/tp/actions/workflows/gradle.yml)

![Ui](docs/images/Ui.png)

- This is **a project for Software Engineering (SE) students**.<br>
- The project simulates an ongoing software project for a desktop application (called _playbook.io_) used for managing athletes and partners.
  - It is **written in OOP fashion**. It provides a **reasonably well-written** code base **bigger** (around 6 KLoC) than what students usually write in beginner-level SE modules, without being overwhelmingly big.
  - It comes with a **reasonable level of user and developer documentation**.
- For the detailed documentation of this project, see the **[playbook.io product website](https://se-education.org/addressbook-level3)**.

### Acknowledgements

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

## Features Summary

### Add Athlete Profile

**Purpose:** Allows a sports agent to add athlete profiles for tracking and management.

**Command Format:**

```
add-a n/NAME s/SPORT a/AGE p/PHONE e/EMAIL
```

**Example:**

```
add-a n/Lionel Messi s/Football a/37 p/98765432 e/messi@example.com
```

**Output:**
New athlete added: Lionel Messi (Football, Age 37)

**Validation:**

- **Name:** Alphabetic + spaces only.  
  → Error: `Invalid name: Names should only contain alphabetic characters and spaces, and it should not be blank`
- **Sport:** Alphabetic + spaces only.  
  → Error: `Invalid sport: Sport names should only contain alphabetic characters and spaces, and it should not be blank`
- **Age:** Integer 1–99.  
  → Error: `Invalid age: Ages should be a numeric value between 1 and 99 (inclusive)`
- **Phone:** 8-digit number.  
  → Error: `Invalid phone number: Phone numbers should only contain numbers, and be exactly 8 digits long`
- **Email:** Must match standard format (`^[\w\.-]+@[\w\.-]+\.\w+$`).  
  → Error: `Invalid email format.`
- **Duplicate Rule:** Duplicate = same `NAME + SPORT`.  
  → Error: `Error: Athlete already exists.`

### Delete Athlete Profile

**Purpose:** Remove athlete profiles no longer represented by the agent.

**Command Format:**

```
delete-a n/NAME s/SPORT
```

**Example:**

```
delete-a n/Lionel Messi s/Football
```

**Output:**
Athlete deleted: Lionel Messi

**Errors:**

- Missing parameter: `Error: Missing required parameter [n/NAME].`
- Invalid name/sport: validation same as Add Athlete.
- Non-existent athlete: `Error: Athlete not found.`
- Has active contracts: `Error: Cannot delete athlete with active contracts.`

### Add Organisation

**Purpose:** Add new organisations (teams, brands, sponsors) for network management.

**Command Format:**

```
add-o o/ORG p/PHONE e/EMAIL
```

**Example:**

```
add-o o/Nike p/98765432 e/john.doe@nike.com
```

**Output:**
New organisation added: Nike

**Validation:**

- **Organisation:** Letters, numbers, spaces, `'`, `&`, and `-`.  
  → Error: `Invalid organisation: Only letters, numbers, spaces, apostrophes, ampersands, and hyphens are allowed.`
- **Phone:** 8-digit number only.  
  → Error: `Invalid phone number: Must be exactly 8 digits.`
- **Email:** Must match standard format.  
  → Error: `Invalid email format.`
- **Duplicate Rule:** Duplicate = same `ORG`.  
  → Error: `Error: Organisation already exists (Nike).`

### Delete Organisation

**Purpose:** Remove outdated or irrelevant organisations.

**Command Format:**

```
delete-o o/ORG
```

**Examples:**

```
delete-o o/Nike
delete-o o/Old Sponsor Ltd
```

**Output:**
Organisation deleted: Nike

**Errors:**

- Missing ORG: `Error: Missing required parameter [o/ORG].`
- Invalid parameter: same validation as Add Organisation.
- Ambiguous (multiple contacts):  
  `Multiple contacts found under organisation 'Nike'. Please specify additional details.`
- Active contracts: `Error: Cannot delete organisations with active contracts.`

### Add Contract

**Purpose:** Link existing athletes with organisations under a contract.

**Command Format:**

```
add-c n/NAME s/SPORT o/ORG sd/START_DATE ed/END_DATE a/AMOUNT
```

**Example:**

```
add-c n/Lionel Messi s/Football o/Inter Miami sd/01012024 ed/01012025 a/50000000
```

**Output:**
Contract created: Lionel Messi(Football)-Inter Miami from 01/01/2024 to 01/01/2025 ($50000000)

**Validation:**

- Names, sports, and organisations validated as above.
- **Dates:** `DDMMYYYY` format only.  
  → Error if invalid or end date before start:  
  `Invalid date: End date must be after the start date.`
- **Amount:** Positive integer.  
  → Error: `Invalid amount: Must be a positive integer.`
- **Duplicate Rule:** Duplicate = same all fields.  
  → Error: `Error: Contract already exists.`

### Delete Contract

**Purpose:** Delete existing or expired contracts.

**Command Format:**
delete-c n/NAME s/SPORT o/ORG sd/START_DATE ed/END_DATE

**Example:**
delete-c n/Lionel Messi s/Football o/Inter Miami sd/01012024 ed/01012025

**Output:**
Contract deleted: Lionel Messi(Football)-Inter Miami from 01/01/2024 to 01/01/2025

**Errors:**

- Missing parameters: `Error: Missing required parameter [n/NAME].`
- Invalid input or format: follow same validation as Add Contract.
- Contract not found: `Error: Contract not found.`
