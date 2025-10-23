---
layout: default.md
title: "User Guide"
pageNav: 3
---

# User Guide

**playbook.io** is the ultimate platform that empowers sports agents to stay organized, build stronger relationships, and drive success for their athletes and partners. It simplifies the management of athletes, organizations, and contracts — allowing agents to focus on what truly matters: closing deals and maximizing opportunities.  

Optimized for use through a powerful Command Line Interface (CLI) while retaining the intuitive benefits of a Graphical User Interface (GUI), playbook.io offers the best of both worlds. If you can type fast, you can manage your athletes, contracts, and organizations faster than with traditional apps — combining speed, efficiency, and control in one seamless experience.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## About This Guide

### Target Users

This guide is designed for **sports agents and talent managers** who:
- Manage multiple athletes across various sports
- Work with sports organizations, teams, and sponsors
- Handle contract negotiations and renewals
- Need to track relationships and opportunities efficiently
- Prefer fast, keyboard-driven workflows over mouse-heavy interfaces

### What You'll Need

**Prior Knowledge:**
- Basic computer literacy (file management, running applications)
- Familiarity with command-line interfaces is helpful but not required
- Understanding of sports industry terminology (contracts, agents, organizations)

**Technical Requirements:**
- Java 17 or higher installed on your computer
- 50MB of available storage space
- Any modern operating system (Windows, macOS, Linux)

### How to Use This Guide

- **New users**: Start with [Quick Start](#quick-start) for setup and basic usage
- **Existing users**: Jump to [Command Reference](#command-reference) for syntax details
- **Power users**: Check [Tips and Best Practices](#tips-and-best-practices) for advanced workflows

--------------------------------------------------------------------------------------------------------------------

## Quick Start

### Installation

1. **Check Java Version**
   
   Ensure you have Java `17` or above installed on your computer.
   ```
   java -version
   ```
   
   **Mac users:** Follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationMac.html) to install the correct JDK version.  
   **Windows users:** Follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationWindows.html) to install the correct JDK version.  
   **Linux users:** Follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationLinux.html) to install the correct JDK version.
2. **Download playbook.io**
   
   Download the latest `.jar` file from the [playbook.io Releases](https://github.com/AY2526S1-CS2103T-F13-3/tp/releases) page.

3. **Set Up Home Folder**
   
   Copy the `.jar` file to the folder you want to use as the **home folder** for playbook.io. This is where your data files will be stored.

4. **Launch the Application**
   
   Open a terminal, navigate (`cd`) to that folder, and run:
   ```
   java -jar playbook.io.jar
   ```
   For example, if your `.jar` file is in the Downloads folder:
   ```
   cd Downloads
   java -jar playbook.io.jar
   ```

5. **First Look**
   
   The playbook.io interface should appear in a few seconds.
   
   ![Ui](images/Ui.png)

### Understanding the Interface

The playbook.io interface consists of five main areas:

1. **Menu Bar** (top): File and Help menus for application settings and documentation
2. **Command Box** (top area): Text input field where you type commands to interact with the application
3. **Main Display Area** (center): Shows your data lists and command feedback messages
4. **Navigation Tabs** (bottom): Three tabs for switching between Athletes, Organizations, and Contracts views
5. **Status Bar** (bottom): Shows current data file location and application status

**Navigation Tips:**
- Use **Cmd+1**, **Cmd+2**, **Cmd+3** (or **Ctrl+1**, **Ctrl+2**, **Ctrl+3** on Windows/Linux) to quickly switch between tabs
- Press **F1** for help or use the `help` command
- Use **Cmd+R** (or **Ctrl+R** on Windows/Linux) to refresh and clear any active search filters

### Your First Commands

Let's try some basic operations to get you started:

1. **Add an Athlete**
   ```
   add-a n/LeBron James s/Basketball a/40 p/98765432 e/james@example.com
   ```

2. **Add an Organization**
   ```
   add-o o/Nike p/98765432 e/john.doe@nike.com
   ```

3. **Search for Athletes**
   ```
   find -an LeBron
   ```

4. **Clear the Search**
   ```
   refresh // or Cmd+R (Ctrl+R on windows)
   ```

<div markdown="block" class="alert alert-info">

**💡 Tip:** All commands in playbook.io are designed to be fast to type. Use short flags like `-an` (athlete name) and `-on` (organization name) to quickly find what you need.

</div>

--------------------------------------------------------------------------------------------------------------------

## Core Features

### Managing Athletes

Athletes are the heart of your business. playbook.io makes it easy to track their information and see their contract relationships at a glance.

#### Adding Athletes

**Command:** `add-a n/NAME s/SPORT a/AGE p/PHONE e/EMAIL`

**Parameters:**
- `n/NAME`: Full name of the athlete 
- `s/SPORT`: Primary sport 
- `a/AGE`: Age in years 
- `p/PHONE`: Contact phone number 
- `e/EMAIL`: Email address 

**Examples:**
```
add-a n/Serena Williams s/Tennis a/42 p/91234567 e/serena@example.com
add-a n/Cristiano Ronaldo s/Football a/39 p/87654321 e/cr7@example.com
add-a n/Michael Jordan s/Basketball a/61 p/95551234 e/mj@example.com
```

**Expected Output:**
```
New athlete added: Serena Williams; Sport: Tennis; Age: 42; Phone: 91234567; Email: serena@example.com
```

<div markdown="block" class="alert alert-warning">

**⚠️ Important:** Athlete names and sports together must be unique. You cannot add two athletes with the exact same name and sport combination.

</div>

#### Deleting Athletes

**Command:** `delete-a n/NAME s/SPORT`

**Parameters:**
- `n/NAME`: Full name of the athlete to delete
- `s/SPORT`: Sport to identify the correct athlete

**Examples:**
```
delete-a n/Serena Williams s/Tennis
delete-a n/Cristiano Ronaldo s/Football
```

**Expected Output:**
```
Deleted Athlete: Serena Williams; Sport: Tennis; Age: 42; Phone: 91234567; Email: serena@example.com
```

<div markdown="block" class="alert alert-warning">

**⚠️ Important:** You cannot delete an athlete if they have active contracts. You must delete all associated contracts first before removing the athlete.

</div>

### Managing Organizations

Organizations include teams, sponsors, agencies, and any entity that contracts with athletes.

#### Adding Organizations

**Command:** `add-o o/ORGANIZATION_NAME p/PHONE e/EMAIL`

**Parameters:**
- `o/ORGANIZATION_NAME`: Full name of the organization 
- `p/PHONE`: Contact phone number 
- `e/EMAIL`: Contact email address 

**Examples:**
```
add-o o/Nike p/98765432 e/partnerships@nike.com
add-o o/Manchester United p/91234567 e/contracts@manutd.com
add-o o/IMG Academy p/87654321 e/talent@img.com
```

**Expected Output:**
```
New organisation added: Nike; Phone: 98765432; Email: partnerships@nike.com
```

<div markdown="block" class="alert alert-warning">

**⚠️ Important:** Each organization name must be unique. You cannot add two organizations with the same name.

</div>

<div markdown="block" class="alert alert-info">

**💡 Tip:** Use descriptive names for organizations to easily distinguish between different types (teams, sponsors, agencies, etc.).

</div>


#### Deleting Organizations

**Command:** `delete-o o/ORGANIZATION_NAME`

**Parameters:**
- `o/ORGANIZATION_NAME`: Full name of the organization to delete

**Examples:**
```
delete-o o/Nike
delete-o o/Manchester United
```

**Expected Output:**
```
Deleted Organisation: Nike; Phone: 98765432; Email: partnerships@nike.com
```

<div markdown="block" class="alert alert-warning">

**⚠️ Important:** You cannot delete an organization if it has active contracts. You must delete all associated contracts first before removing the organization.

</div>

### Managing Contracts

Contracts link athletes with organizations and track the business relationships that matter most.

#### Adding Contracts

**Command:** `add-c n/NAME s/SPORT o/ORG sd/DDMMYYYY ed/DDMMYYYY am/AMOUNT`

**Parameters:**
- `n/NAME`: Athlete's full name 
- `s/SPORT`: Athlete's sport  
- `o/ORG`: Organization name 
- `sd/DDMMYYYY`: Start date in day-month-year format
- `ed/DDMMYYYY`: End date in day-month-year format
- `am/AMOUNT`: Contract amount 

**Examples:**
```
add-c n/Lionel Messi s/Football o/Inter Miami sd/01072023 ed/31122025 am/50000000
add-c n/LeBron James s/Basketball o/Nike sd/15052022 ed/15052032 am/100000000
add-c n/Serena Williams s/Tennis o/Wilson sd/01012020 ed/31122024 am/5000000
```

**Expected Output:**
```
Contract created: Athlete: Lionel Messi; Sport: Football; Organisation: Inter Miami; Start: 01/07/2023; End: 31/12/2025; Amount: 50000000
```

<div markdown="block" class="alert alert-warning">

**⚠️ Important:** 
- Both the athlete and organization must exist before creating a contract
- Start date must be before or equal to end date
- Contract amount should be positive integers only (no currency symbols)
- Each contract must be unique. You cannot add two contracts with exactly the same details — athlete, organization, dates, and amount

</div>

#### Deleting Contracts

**Command:** `delete-c n/NAME s/SPORT o/ORG sd/DDMMYYYY ed/DDMMYYYY`

**Parameters:**
- `n/NAME`: Athlete's full name
- `s/SPORT`: Athlete's sport
- `o/ORG`: Organization name
- `sd/DDMMYYYY`: Start date (must match exactly)
- `ed/DDMMYYYY`: End date (must match exactly)

**Examples:**
```
delete-c n/Lionel Messi s/Football o/Inter Miami sd/01072023 ed/31122025
delete-c n/LeBron James s/Basketball o/Nike sd/15052022 ed/15052032
```

**Expected Output:**
```
Deleted contract: Athlete: Lionel Messi; Sport: Football; Organisation: Inter Miami; Start: 01/07/2023; End: 31/12/2025; Amount: 50000000
```

### Finding & Filtering Data

The search functionality uses **fuzzy matching** to help you find what you're looking for, even with typos or partial names.

#### Search Command

**Command:** `find -[FLAG] KEYWORD`

**Available Search Types:**

| Flag | Searches | Example |
|------|----------|---------|
| `-an` | Athlete names | `find -an Lionel` |
| `-as` | Athlete sports | `find -as Football` |
| `-on` | Organization names | `find -on Nike` |
| `-ca` | Contracts by athlete name | `find -ca LeBron` |
| `-co` | Contracts by organization | `find -co Miami` |
| `-cs` | Contracts by sport | `find -cs Basketball` |

**Examples:**
```
find -an Messi          # Find athletes with names like "Messi"
find -as Tennis         # Find all tennis athletes  
find -on Nike           # Find Nike organization
find -ca LeBron         # Find contracts with athletes named like "LeBron"
find -co Manchester     # Find contracts with organizations like "Manchester"
find -cs Football       # Find all football contracts
```

**Expected Output:**
```
Showing 1 athletes closely matching "Messi".
Use the "refresh" command (or Cmd+R) to show all data again.
```

<div markdown="block" class="alert alert-info">

**💡 Fuzzy Search Features:**
- **Exact matches**: "Nike" will find "Nike" instantly
- **Substring matches**: "Man" will find "Manchester United"  
- **Typo tolerance**: "Messy" will find "Messi" (1-2 character differences allowed)
- **Case insensitive**: "NIKE" and "nike" both work

</div>

#### Clearing Search Results

**Command:** `refresh`

Use this command to clear any active search filters and return to viewing all data.

**Keyboard Shortcut:** **Cmd+R** (macOS) or **Ctrl+R** (Windows/Linux)

**Expected Output:**
```
All filters cleared. Showing all athletes, organizations, and contracts.
```

### Application Controls

#### Getting Help

**Command:** `help`

Opens the help window with quick reference information and links to documentation.

**Keyboard Shortcut:** **F1**

**Expected Output:**
```
Opened help window.
```

#### Exiting the Application

**Command:** `exit`

Safely closes playbook.io and saves all your data.

**Expected Output:**
```
Exiting Address Book as requested ...
```

<div markdown="block" class="alert alert-info">

**💡 Tip:** Your data is automatically saved after every command, so you never have to worry about losing information.

</div>

--------------------------------------------------------------------------------------------------------------------

## Command Reference

### Quick Syntax Guide

| Command | Syntax | Description |
|---------|--------|-------------|
| `add-a` | `add-a n/NAME s/SPORT a/AGE p/PHONE e/EMAIL` | Add new athlete |
| `delete-a` | `delete-a n/NAME s/SPORT` | Delete athlete |
| `add-o` | `add-o o/ORG_NAME p/PHONE e/EMAIL` | Add new organization |
| `delete-o` | `delete-o o/ORG_NAME` | Delete organization |
| `add-c` | `add-c n/NAME s/SPORT o/ORG sd/DATE ed/DATE am/AMOUNT` | Add new contract |
| `delete-c` | `delete-c n/NAME o/ORG sd/DATE ed/DATE` | Delete contract |
| `find` | `find -[an\|as\|on\|ca\|co\|cs] KEYWORD` | Search/filter data |
| `refresh` | `refresh` | Clear all filters |
| `help` | `help` | Open help window |
| `exit` | `exit` | Close application |

### Parameter Details

**Common Parameters:**
- `NAME`: Full name (spaces allowed, case-insensitive; accepts alphabetic characters, spaces, hyphens, and apostrophes)
- `SPORT`: Sport name (spaces allowed, case-insensitive; alphabetic characters only)
- `ORG_NAME`: Organization name (spaces allowed, case-insensitive; accepts alphabetic characters, spaces, hyphens, and apostrophes)
- `PHONE`: Phone number (8-digit Singapore phone number onl)
- `EMAIL`: Email address (must contain @ symbol, case insensitive)
- `DATE`: Format DDMMYYYY (e.g., 25122024 for December 25, 2024)
- `AMOUNT`: Positive integers only (no currency symbols or commas)
- `AGE`: Positive integers only (Numeric value from 1–99)

### Keyboard Shortcuts

| Shortcut | Action | Description |
|----------|--------|-------------|
| **F1** | Help | Open help window |
| **Cmd+R** / **Ctrl+R** | Refresh | Clear all active filters |
| **Cmd+1** / **Ctrl+1** | Athletes Tab | Switch to Athletes view |
| **Cmd+2** / **Ctrl+2** | Organizations Tab | Switch to Organizations view |
| **Cmd+3** / **Ctrl+3** | Contracts Tab | Switch to Contracts view |

--------------------------------------------------------------------------------------------------------------------

## Tips and Best Practices

### Effective Workflows

**Daily Management:**
1. Start each day with `refresh` to see all your data
2. Use `find -an [name]` to quickly locate specific athletes
3. Check contract expiry dates regularly with `find -cs [sport]`
4. Keep contact information updated for all entities

**Contract Negotiation Season:**
1. Use `find -ca [athlete]` to see all contracts for an athlete
2. Cross-reference with `find -co [organization]` to see organization's other deals
3. Track contract amounts and dates systematically
4. Add new contracts immediately after signing

**Data Organization:**
- Use consistent naming conventions (e.g., "FC Barcelona" vs "Barcelona FC")
- Include full legal names for organizations
- Keep email and phone information current
- Group related contracts by using similar start/end date patterns

### Common Pitfalls & Solutions

**Problem:** "Athlete not found" when adding contracts  
**Solution:** Use `find -an [name]` first to verify the exact name spelling

**Problem:** Accidentally deleted important data  
**Solution:** Data is automatically saved after each command - restart the application to restore the previous state

**Problem:** Too many search results  
**Solution:** Use more specific keywords or combine with tab switching

**Problem:** Slow performance with large datasets  
**Solution:** Use search filters regularly instead of browsing all data

--------------------------------------------------------------------------------------------------------------------

## Troubleshooting and FAQ

### Display and Window Issues

**Problem:** Application window opens off-screen after disconnecting a monitor  
**Cause:** The application remembers its last window position from preferences.json. If the app was last open on a secondary monitor and that monitor is disconnected, the window tries to open in the same (now nonexistent) location  
**Solution:** Delete the `preferences.json` file before launching the app again to reset window position

**Problem:** Help Window remains minimized and does not reopen  
**Cause:** If the Help Window is already open and minimized, invoking the help command again does not spawn a new window — the existing window simply remains minimized  
**Solution:** Manually restore the minimized Help Window

### Common Error Messages

**"Command not recognized"**
- Check spelling of command name
- Ensure you're using the correct flags (e.g., `-an` not `-name`)
- See [Command Reference](#command-reference) for exact syntax

**"Invalid date format"**
- Use DDMMYYYY format only (e.g., 25122024)
- No spaces, dashes, or slashes in dates
- Ensure day/month values are valid (01-31 for days, 01-12 for months)

**"Athlete/Organization not found"**
- Use `find` commands to locate the exact name
- Check for typos or extra spaces
- Names are case-sensitive and must match exactly

**"Duplicate entry"**
- Athlete name + sport combinations must be unique
- Organization names must be unique
- Try slightly different name variations if needed

### Performance Issues

**Large datasets (1000+ entries):**
- Use search filters instead of browsing all data
- Close and restart the application weekly
- Consider archiving old contracts to separate files

**Slow startup:**
- Check available disk space (need 50MB minimum)
- Ensure Java version is 17 or higher
- Try moving to a different folder location

### Getting More Help

**Additional Resources:**
- [GitHub Repository](https://github.com/AY2526S1-CS2103T-F13-3/tp) for technical issues
- [Developer Guide](DeveloperGuide.md) for advanced customization
- Community forums for user tips and tricks

**Reporting Bugs:**
1. Document the exact command that caused the issue
2. Note any error messages displayed
3. Include your operating system and Java version
4. Submit via GitHub Issues with detailed description

--------------------------------------------------------------------------------------------------------------------

## Appendix

### Glossary

**Athlete**: An individual sports performer managed by the agent, with contact details and sport specialization.

**Contract**: A business agreement between an athlete and organization, including financial terms and duration.

**Fuzzy Search**: A search method that finds results even with typos or partial matches, using intelligent algorithms.

**Organization**: Any business entity that contracts with athletes - teams, sponsors, agencies, brands, etc.

**Sports Agent**: A professional who represents athletes in contract negotiations and career management.

### Technical Specifications

**System Requirements:**
- **Java Version**: 17 or higher (OpenJDK or Oracle JDK)
- **Memory**: 512MB RAM minimum, 1GB recommended
- **Storage**: 50MB available space for application and data
- **Display**: 1024x768 minimum resolution

**Supported Platforms:**
- Windows 10/11
- macOS 10.14 (Mojave) or later  
- Linux (Ubuntu 18.04+, CentOS 7+, or equivalent)

**Data Storage:**
- All data stored locally in JSON format
- No internet connection required for operation
- Data files are human-readable and portable

### Contact Information

**Development Team:**
- Project maintained by CS2103T-F13-3 team
- GitHub: [AY2526S1-CS2103T-F13-3/tp](https://github.com/AY2526S1-CS2103T-F13-3/tp)

**Support:**
- Technical issues: Submit GitHub Issues
- Feature requests: Use GitHub Discussions
- General questions: Consult the FAQ section in the documentation, otherwise contact us with GitHub Discussions!

**Version Information:**
- Current Version: 1.4
- Last Updated: November 2024
- License: MIT License

---

*Thank you for using playbook.io! We're committed to helping sports agents succeed in managing their athletes and growing their business.*
