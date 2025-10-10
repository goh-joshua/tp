---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Playbook.io User Guide

Playbook.io is a **desktop app for managing sports contacts including athletes, organizations, and contracts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Playbook.io can get your sports management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `add-a n/LeBron James s/Basketball ag/40 p/98765432 e/james@example.com` : Adds an athlete named `LeBron James` to Playbook.io.

   * `add-org o/Nike cn/John Doe p/98765432 e/john.doe@nike.com` : Adds an organization named `Nike` to Playbook.io.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding an athlete: `add-a`

Adds an athlete to playbook.io.

Format: `add-a n/NAME s/SPORT ag/AGE p/PHONE_NUMBER e/EMAIL`

* All fields are required.
* `AGE` must be a positive integer.
* `SPORT` can be any sport type (e.g., Basketball, Football, Tennis).

Examples:
* `add-a n/LeBron James s/Basketball ag/40 p/98765432 e/james@example.com`
* `add-a n/Serena Williams s/Tennis ag/42 p/87654321 e/serena@example.com`

### Deleting an athlete: `delete-a`

Deletes the specified athlete from playbook.io.

Format: `delete-a INDEX`

* Deletes the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete-a 1` deletes the 1st athlete in the athlete list.

### Adding an organization: `add-org`

Adds an organization to the address book.

Format: `add-org o/ORGANIZATION_NAME cn/CONTACT_NAME p/PHONE_NUMBER e/EMAIL`

* All fields are required.
* `ORGANIZATION_NAME` is the name of the organization (e.g., Nike, Adidas).
* `CONTACT_NAME` is the name of the contact person at the organization.

Examples:
* `add-org o/Nike cn/John Doe p/98765432 e/john.doe@nike.com`
* `add-org o/Manchester United cn/Jane Smith p/87654321 e/jane@manutd.com`

### Deleting an organization: `delete-org`

Deletes the specified organization from the address book.

Format: `delete-org INDEX`

* Deletes the organization at the specified `INDEX`.
* The index refers to the index number shown in the displayed organization list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete-org 2` deletes the 2nd organization in the organization list.

### Adding a contract: `add-contract`

Adds a contract linking an athlete and an organization.

Format: `add-contract n/ATHLETE_NAME s/SPORT o/ORG_NAME sd/START_DATE ed/END_DATE am/AMOUNT`

* All fields are required.
* `ATHLETE_NAME` and `ORG_NAME` must correspond to existing athletes and organizations.
* `START_DATE` and `END_DATE` must be in DDMMYYYY format.
* `AMOUNT` must be a positive integer representing the contract value.

Examples:
* `add-contract n/LeBron James s/Basketball o/Lakers sd/01012024 ed/31122024 am/50000000`
* `add-contract n/Lionel Messi s/Football o/Inter Miami sd/15062023 ed/15062025 am/60000000`

### Deleting a contract: `delete-contract`

Deletes the specified contract.

Format: `delete-contract INDEX`

* Deletes the contract at the specified `INDEX`.
* The index refers to the index number shown in the displayed contract list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete-contract 1` deletes the 1st contract in the contract list.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action               | Format, Examples
---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add Person**       | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Add Athlete**      | `add-a n/NAME s/SPORT ag/AGE p/PHONE_NUMBER e/EMAIL` <br> e.g., `add-a n/LeBron James s/Basketball ag/40 p/98765432 e/james@example.com`
**Add Organization** | `add-org o/ORGANIZATION_NAME cn/CONTACT_NAME p/PHONE_NUMBER e/EMAIL` <br> e.g., `add-org o/Nike cn/John Doe p/98765432 e/john.doe@nike.com`
**Add Contract**     | `add-contract n/ATHLETE_NAME s/SPORT o/ORG_NAME sd/START_DATE ed/END_DATE am/AMOUNT` <br> e.g., `add-contract n/LeBron James s/Basketball o/Lakers sd/01012024 ed/31122024 am/50000000`
**Clear**            | `clear`
**Delete Person**    | `delete INDEX`<br> e.g., `delete 3`
**Delete Athlete**   | `delete-a INDEX`<br> e.g., `delete-a 1`
**Delete Organization** | `delete-org INDEX`<br> e.g., `delete-org 2`
**Delete Contract**  | `delete-contract INDEX`<br> e.g., `delete-contract 1`
**Edit**             | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**             | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**             | `list`
**Help**             | `help`
