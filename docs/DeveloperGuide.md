---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# Playbook.io Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="400" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [
`Main`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [
`MainApp`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in
charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

### How the architecture components interact with each other

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete-a n/LeBron s/Basketball`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API
  `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface.  
Other components interact with a given component through its interface rather than the concrete class (reason: to
prevent outside components from being coupled to the implementation of a component), as illustrated in the (partial)
class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [
`Ui.java`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component" width="800"/>

The UI consists of a `MainWindow` that is made up of parts e.g. `CommandBox`, `ResultDisplay`, `AthleteListPanel`,
`OrganizationListPanel`, `ContractListPanel`, `StatusBarFooter`, etc.  
All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between
classes that represent parts of the visible GUI.

The `UI` component uses the JavaFX UI framework.  
The layout of these UI parts is defined in matching `.fxml` files that are in the `src/main/resources/view` folder.  
For example, the layout of the [
`MainWindow`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in [
`MainWindow.fxml`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/resources/view/MainWindow.fxml).

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Athlete`, `Organization`, and `Contract` objects
  residing in the `Model`.

### Logic component

**API** : [
`Logic.java`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagrams below illustrate the interactions within the `Logic` component,
taking the `add-a` and `delete-a` commands as examples.

<puml src="diagrams/AddAthleteSequenceDiagram.puml" width="800" />
<puml src="diagrams/DeleteAthleteSequenceDiagram.puml" width="800" />

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it passes the user input to an `AddressBookParser` object, which
   in turn creates a parser that matches the command (e.g., `AddAthleteCommandParser` or `DeleteAthleteCommandParser`)
   and uses it to parse the command.
2. This results in a `Command` object (more precisely, an instance of one of its subclasses such as
   `AddAthleteCommand` or `DeleteAthleteCommand`) which is executed by the `LogicManager`.
3. The command communicates with the `Model` when executed (e.g., to add or delete an athlete).  
   Although this is shown as a single step in the diagrams above for simplicity, the actual implementation involves
   multiple interactions between the `Command` and the `Model`.
4. The result of the command execution is encapsulated as a `CommandResult` object, which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing user commands:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser`
  (`XYZ` is a placeholder for the specific command name, e.g., `AddAthleteCommandParser`,
  `AddOrganizationCommandParser`,
  `AddContractCommandParser`, `DeleteAthleteCommandParser`, etc.), which uses the other classes shown above to parse
  the user command and create an `XYZCommand` object (e.g., `AddAthleteCommand`).
* All `XYZCommandParser` classes (e.g., `AddAthleteCommandParser`, `DeleteAthleteCommandParser`,
  `AddOrganizationCommandParser`)
  implement the `Parser` interface so that they can be treated uniformly (e.g., during testing).

### Model component

**API** : [
`Model.java`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="550" />

The `Model` component,

* stores the application data — i.e., all `Athlete`, `Organization`, and `Contract` objects, which are contained in
  `UniqueAthleteList`, `UniqueOrganizationList`, and `UniqueContractList` objects respectively.
* stores the currently “selected” data (e.g., results of a search query) as separate _filtered_ lists, which are exposed
  to the outside as unmodifiable `ObservableList<>` objects (for example, `ObservableList<Athlete>`). This allows the UI
  to automatically update when the underlying data changes.
* stores a `UserPrefs` object that represents the user’s preferences. This is exposed to the outside as a
  `ReadOnlyUserPrefs` object.
* does not depend on any of the other three components (as the `Model` represents domain data that should make sense
  independently of other layers).

### Storage component

**API** : [
`Storage.java`](https://github.com/AY2526S1-CS2103T-F13-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save both application data (including `Athlete`, `Organization`, and `Contract` information) and user preference
  data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefsStorage`, which means it can be treated as either one if only
  the functionality of one is needed.
* depends on some classes in the `Model` component, because the `Storage` component’s job is to save and retrieve
  objects that belong to the `Model`.

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* sports agents who manage multiple athletes and their careers
* need to stay organised with many organisations (teams, sponsors, brands)
* prefer structured tools over manual spreadsheets or scattered files
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: The ultimate platform that empowers sports agents to stay organised, build stronger
relationships, and drive success for their athletes and partners.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​      | I want to …​                                                                                 | So that I can…​                                                      |
|----------|--------------|----------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| `* * *`  | new user     | see usage instructions                                                                       | refer to instructions when I forget how to use the App               |
| `* * *`  | sports agent | create a new athlete profile                                                                 | keep track of the athletes I currently represent                     |
| `* * *`  | sports agent | delete an athlete profile                                                                    | remove athletes I no longer represent                                |
| `* * *`  | sports agent | create a new organisation                                                                    | store details of teams, sponsors, or brands I work with              |
| `* * *`  | sports agent | delete an organisation                                                                       | remove organisations that are no longer relevant                     |
| `* * *`  | sports agent | upload a new contract                                                                        | track my athletes’ active agreements                                 |
| `* * *`  | sports agent | delete a contract                                                                            | keep my records up to date                                           |
| `* *`    | sports agent | search for an athlete or organisation                                                        | quickly find the information I need without scrolling                |
| `*`      | sports agent | view analytics on contracts & deals                                                          | gain insights into performance and opportunities                     |
| `*`      | sports agent | link travel plans to competition and appearance schedules                                    | coordinate logistics efficiently and avoid scheduling conflicts      |
| `*`      | sports agent | manage and sync my athletes’ competition schedules and training events in a central calendar | coordinate effectively with teams, sponsors, and travel arrangements |

### Use cases

(For all use cases below, the **System** is the `playbook.io` and the **Actor** is the `user`, unless specified
otherwise)

**Use case: Add Athlete Profile**

**MSS**

1. Agent requests to add athlete
2. playbook.io stores the new athlete profile

   Use case ends.

**Extensions**

* 1a. Missing required parameter

    * 1a1. playbook.io shows an error message

      Use case ends.

* 1b. The given parameter is invalid

    * 1b1. playbook.io shows an error message

      Use case ends.

* 2aDuplicate athlete

    * 2a1. playbook.io shows an error message

      Use case ends.

**Use case: Delete Athlete Profile**

**MSS**

1. Agent requests to delete athlete
2. playbook.io deletes the athlete

   Use case ends.

**Extensions**

* 1a. Missing required parameter

    * 1a1. playbook.io shows an error message

      Use case ends.

* 1b. The given parameter is invalid

    * 1b1. playbook.io shows an error message

      Use case ends.

* 2a. No athlete found

    * 2a1. playbook.io shows an error message

      Use case ends.

* 2b. Athlete has active contracts

    * 2b1. playbook.io shows an error message

      Use case ends.

**Use case: Add Organisation**

**MSS**

1. Agent requests to add organisation
2. playbook.io stores the new athlete profile

   Use case ends.

**Extensions**

* 1a. Missing required parameter

    * 1a1. playbook.io shows an error message

      Use case ends.

* 1b. The given parameter is invalid

    * 1b1. playbook.io shows an error message

      Use case ends.

* 2a Duplicate athlete

    * 2a1. playbook.io shows an error message

      Use case ends.

**Use case: Delete Organisation**

**MSS**

1. Agent requests to delete a contact associated with an organisation
2. If exactly one match is found, playbook.io deletes the contact

   Use case ends.

**Extensions**

* 1a. Missing required parameter

    * 1a1. playbook.io shows an error message

      Use case ends.

* 1b. The given parameter is invalid

    * 1b1. playbook.io shows an error message

      Use case ends.

* 2a. No organisation found

    * 2a1. playbook.io shows an error message

      Use case ends.

* 2b. Multiple contacts with associated organisation found

    * 2b1. playbook.io shows an error message

      Use case ends.

* 2c. Contact has active contracts

    * 2c1. playbook.io shows an error message

      Use case ends.

**Use case: Add Contract**

**MSS**

1. Agent requests to add contract
2. playbook.io stores the new contract

   Use case ends.

**Extensions**

* 1a. Missing required parameter

    * 1a1. playbook.io shows an error message

      Use case ends.

* 1b. The given parameter is invalid

    * 1b1. playbook.io shows an error message

      Use case ends.

* 2a. No athlete / organisation / contact found

    * 2a1. playbook.io shows an error message

      Use case ends.

* 2b. Duplicate contract

    * 2b1. playbook.io shows an error message

      Use case ends.

**Use case: Delete Contract**

**MSS**

1. Agent requests to delete a contract
2. playbook.io deletes the contract

   Use case ends.

**Extensions**

* 1a. Missing required parameter

    * 1a1. playbook.io shows an error message

      Use case ends.

* 1b. The given parameter is invalid

    * 1b1. playbook.io shows an error message

      Use case ends.

* 2a. No contract found

    * 2a1. playbook.io shows an error message

      Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Should validate all input data (e.g., names, emails, dates, amounts) and provide clear error messages when invalid
   input is detected.
5. Should prevent accidental data loss by requiring explicit confirmation for destructive operations such as deleting
   athletes, organisations, or contracts.
6. Should provide meaningful error handling and recovery messages if storage or system errors occur (e.g., “Unable to
   save contract. Please try again.”).
7. Should allow the application to be packaged and distributed in a portable format (e.g., JAR or Docker container) for
   ease of deployment across environments.
8. Should allow a new user to learn the system within 10 minutes by following the user guide and trying out the sample
   dataset.
9. Should provide consistent response times (<2 seconds) for retrieval commands such as searching athletes,
   organisations, or contracts under normal usage load.
10. Should prevent duplicate records by enforcing unique key constraints (e.g., same athlete name + sport, same
    organisation + contact person).

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Athlete Profile**: A record containing an athlete’s details such as name, sport, age, and contact information.
* **Organisation**: An entity such as a team, brand, or sponsor that interacts with the sports agent.
* **Contract**: A formal agreement between an athlete and an organisation, including details like duration, payment, and
  contact person.
* **Sponsor**: A company or brand that provides financial or material support to athletes, often in exchange for
  promotion.
* **Competition Schedule**: A calendar of matches, events, or appearances an athlete participates in.
* **Training Event**: A scheduled practice or preparation session for an athlete.
* **Contact Person**: The representative from an organisation who communicates with the sports agent.
* **MVP (Minimum Viable Product)**: The smallest set of features that make the app usable, such as creating and deleting
  athletes, organisations, and contracts.
* **CRUD Operations**: Basic system functions — Create, Read, Update, Delete.
* **Duplicate Handling**: Rules to prevent multiple entries of the same athlete, organisation, or contract.
* **Validation Error**: A system-generated message shown when user input does not meet required constraints (e.g.,
  invalid email format).
* **UI Mock-up**: A sample design of the interface showing how the system should look and behave.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
